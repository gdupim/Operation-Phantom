package Principal;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import Entidades.CobradeAluminio;
import Entidades.DuroDuro;
import Entidades.Entidade;
import Entidades.Player;
import Entidades.Tempestade;
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
    // n sera usado:
    // public final int worldWidth = tileSize * maxWorldCol;
    // public final int worldHeight = tileSize * maxWorldRow;

    // base do jogo
    TileManager tm = new TileManager(this);
    Audio audio = new Audio();
    Audio msc = new Audio();
    public CollisionChecker cChecker = new CollisionChecker(this);
    public AssetSetter aSetter = new AssetSetter(this);
    public KeyHandler keyH = new KeyHandler(this);
    public EventHandler eHandler = new EventHandler(this);
    public UI ui = new UI(this);
    Thread gameThread;

    // ENTIDADE E OBJETO
    public  Player  player[] = new Player[3];
    public Item item[] = new Item[10];
    public Entidade npc[] = new Entidade[10];
    public Entidade inimigo[] = new Entidade[10];
    public int  playerIndex = 2;
    
    
    
    // GAME STATE
    public int gameState;
    public final int titleState = 0;
    public final int playState = 1;	
    public final int pauseState = 2;
    public final int dialogueState = 3;
    public int selectedCharacter = 0;
    public final int creditState = 4;

    // construtor
    public Janela() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
        this.setFocusable(true);
        this.requestFocus();
        this.addKeyListener(keyH);
    }
    
    public void setupGame() {
        switch (selectedCharacter) {
            case 0:
            player[0] = new CobradeAluminio(this, keyH);
                break;

            case 1:
            player[1] = new DuroDuro(this, keyH);
                break;

            case 2:
            player[2] = new Tempestade(this, keyH);
                break;
        }
       
        aSetter.setItem();
        aSetter.setNPC();
        aSetter.setInimigo();
        iniciarMsc(6);
        gameState = titleState;
        
    }
    
    @Override
    public void run() {
        double drawInterval = 1000000000 / FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        long timer = 0;
        int drawCount = 0;
        
        // game loop
        while (gameThread != null) {
            currentTime = System.nanoTime();
            
            delta += (currentTime - lastTime) / drawInterval;
            
            timer += currentTime - lastTime;

            lastTime = currentTime;

            if (delta >= 1) {
                update();
                repaint();
                delta--;
                drawCount++;
            }
            
            if (timer >= 1000000000) {
                System.out.println("FPS: " + drawCount);
                drawCount = 0;
                timer = 0;
            }
        }
    }
    
    public void update() {
        if (gameState == playState) {
            //  PLAYER[playerIndex]
            player[playerIndex].update();
            // NPC
            for (int i = 0; i < npc.length; i++) {
                if (npc[i] != null) {
                    npc[i].update();
                }
            }
            for (int i = 0; i < inimigo.length; i++) {
                if (inimigo[i] != null) {
                    inimigo[i].update();
                }
            }
        
        if (gameState == pauseState) {
            // pause
        }
        
    }
}

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        Graphics2D g2d = (Graphics2D) g;
        
        //TELA DE LOBBY
        if (gameState == titleState) {
            ui.draw(g2d);

        }
        //TELA DE CREDITOS
        else if (gameState == creditState) {
            ui.draw(g2d);
        }

        //OUTROS
        else {
             // TILE
           tm.draw(g2d);
        
              // ITEM
            for(int i = 0; i<item.length; i++){
                if(item[i] != null){
                item[i].draw(g2d, this);
                }
            }

            // NPC
            for(int i = 0; i < npc.length; i++){
                if(npc[i] != null){
                npc[i].draw(g2d);
                }
            }

            for(int i = 0; i < inimigo.length; i++){
                if(inimigo[i] != null){
                inimigo[i].draw(g2d);
                }
            }


            //  PLAYER[playerIndex]
            player[playerIndex].draw(g2d);
        
            ui.draw(g2d);

            g2d.dispose();

        }

       
    }

    public void iniciarMsc(int i) {
        msc.setFile(i);
        msc.play();
        msc.loop();
    }

    // tocar efeitos sonoros
    public void playSE(int i) {
        audio.setFile(i);
        audio.play();
    }

    public void iniciarGameThread() {
        if (gameThread == null) {
            gameThread = new Thread(this);
            gameThread.start();
        }
    }
}