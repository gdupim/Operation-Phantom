package Principal;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;

import javax.swing.JPanel;

import Entidades.Player;
/**
 *
 * @author caiom
 */
public class Janela extends JPanel implements Runnable {

    // Posição padrão do player
    int playerX = 100;
    int playerY = 100;
    int playerSpeed = 5;
    
    // FPS
    int FPS = 60;

    // Tamanho e escala do tile 
    final int originalTileSize = 32; // 32 x 32
    final int scale = 2; // 2x32 = 64

    public final int tileSize = originalTileSize * scale; // 64x64

    // Obtendo a resolução da tela automaticamente
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    public final int screenWidth = (int) screenSize.getWidth();
    public final int screenHeight = (int) screenSize.getHeight();

    TileManager tm = new TileManager(this); // TileManager
    KeyHandler keyH = new KeyHandler(); // KeyHandler
    Player player = new Player(this, keyH);
    Thread gameThread; // Thread
    
    // construtor
    public Janela() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);
        this.requestFocus();
        this.addKeyListener(keyH);
        this.setFocusable(true);

    }


    @Override
    public void run(){
        double drawInterval = 1000000000/FPS;
        double nextDrawTime = System.nanoTime() + drawInterval;
        long lastTime = System.nanoTime();
        long currentTime;
        long timer = 0;
        int drawCount = 0;

        while(gameThread != null){  

            currentTime = System.nanoTime();
            drawCount++;
            timer += currentTime-lastTime;
            lastTime = currentTime;
            
            try {
                double remainingTime = nextDrawTime - System.nanoTime();
                remainingTime = remainingTime/1000000;
                
                if(remainingTime < 0){
                    remainingTime = 0;
                }
                
                Thread.sleep((long) remainingTime);
                
                nextDrawTime += drawInterval;
                if(timer >= 1000000000){
                    System.out.println("FPS: " + drawCount);
                    drawCount = 0;
                    timer = 0;
                }

                update();
    
                repaint();
                
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void update(){

        if(keyH.upPressed){
            playerY -= playerSpeed;
        }
        if(keyH.downPressed){
            playerY += playerSpeed;
        }
        if(keyH.leftPressed){
            playerX -= playerSpeed;
        }
        if(keyH.rightPressed){
            playerX += playerSpeed;
        }
    }

    public void paintComponent(Graphics g){

        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g;

        tm.draw(g2d);
        
        player.draw(g2d);
        
        g2d.setColor(Color.WHITE);

        g2d.fillRect(playerX,playerY, tileSize, tileSize);
        
        g2d.dispose();
    }

    public void iniciarGameThread() {
        if (gameThread == null) {
            gameThread = new Thread(this);
            gameThread.start();
        }
    }
}


