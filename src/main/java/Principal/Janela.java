package Principal;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import Entidades.Player;
import Itens.Item;

public class Janela extends JPanel implements Runnable {
    // CONFIG TELA DE TAMANHO 1024x576
    final int originalTileSize = 32; // 32 x 32
    final int scale = 2; // 2x32 = 64
    
    public final int tileSize = originalTileSize * scale; // 64x64
    public final int maxScreenCol = 16;
    public final int maxScreenRow = 9; 
    public final int screenWidth = tileSize * maxScreenCol;
    public final int screenHeight = tileSize * maxScreenRow;

    
    // FPS
    int FPS = 60;
    
    // CONFIG MUNDO
    public final int maxWorldCol = 50;
    public final int maxWorldRow = 50;
    public final int worldWidth = tileSize * maxWorldCol;
    public final int worldHeight = tileSize * maxWorldRow;



    TileManager tm = new TileManager(this);
    Thread gameThread;
    public CollisionChecker cChecker = new CollisionChecker(this);
    public AssetSetter aSetter = new AssetSetter(this);
    public KeyHandler keyH = new KeyHandler();
    public Player player = new Player(this, keyH);
    public UI ui = new UI(this);
    public Item item[] = new Item[10];
    
    // construtor
    public Janela() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
        this.setFocusable(true);
        this.requestFocus();
        this.addKeyListener(keyH);


    }

    public void setupGame(){
        aSetter.setItem();
    }
    @Override
    public void run(){

        double drawInterval = 1000000000/FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        long timer = 0;
        int drawCount = 0;
        

        while(gameThread != null){  

            currentTime = System.nanoTime();
            
            delta += (currentTime - lastTime) / drawInterval;

            timer += currentTime - lastTime;

            lastTime = currentTime;

            if(delta >= 1){
                update();
                repaint();
                delta--;
                drawCount++;
            }

            if(timer >= 1000000000){
                System.out.println("FPS: " + drawCount);
                drawCount = 0;
                timer = 0;
            }

        }
    }

    public void update(){

        player.update();
    }

    @Override
    public void paintComponent(Graphics g){

        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g;
        
        // TILE
        tm.draw(g2d);
        
        // ITEM
        for(int i = 0; i < item.length; i++){
            if(item[i] != null){
                item[i].draw(g2d, this);
            }
        }

        // PLAYER
        player.draw(g2d);
        
        ui.draw(g2d);

        g2d.dispose();
    }

    public void iniciarGameThread() {
        if (gameThread == null) {
            gameThread = new Thread(this);
            gameThread.start();
        }
    }


}
