package Entidades;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import Principal.Janela;
import Principal.KeyHandler;
import Principal.UtilityTool;


public class Player extends Entidade {
    

    KeyHandler keyH = new KeyHandler(j);

    public final int screenX;
    public final int screenY;
    public int hasPeDeCabra = 0;
    public boolean peDeCabraEquipped = false;

    
    @SuppressWarnings("OverridableMethodCallInConstructor")
    public Player(Janela j, KeyHandler keyH) {
        super(j);
        
        this.keyH = keyH;

        screenX = j.screenWidth / 2 - j.tileSize / 2;
        screenY = j.screenHeight / 2 - j.tileSize / 2;

        solidArea = new Rectangle(7, 10, 16, 8);
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        setDefaultValues();
        getPlayerImage();
    }

    public void setDefaultValues() {
        worldX = j.tileSize * 23;
        worldY = j.tileSize * 13;
        speed = 4;
        direction = "down";
    }
    public void getPlayerImage() {

        up1 = setup("/Sprites/Player/snake/mov/snake_up_1");
        up2 = setup("/Sprites/Player/snake/mov/snake_up_2");
        down1 = setup("/Sprites/Player/snake/mov/snake_down_1");
        down2 = setup("/Sprites/Player/snake/mov/snake_down_2");
        left1 = setup("/Sprites/Player/snake/mov/snake_left_1");
        left2 = setup("/Sprites/Player/snake/mov/snake_left_2");
        right1 = setup("/Sprites/Player/snake/mov/snake_right_1");
        right2 = setup("/Sprites/Player/snake/mov/snake_right_2");
        if(hasPeDeCabra >= 1){
            up1 = setup("/Sprites/Player/snake/cabra/snake_up_cabra_1");
            up2 = setup("/Sprites/Player/snake/cabra/snake_up_cabra_2");
            down1 = setup("/Sprites/Player/snake/cabra/snake_down_cabra_1");
            down2 = setup("/Sprites/Player/snake/cabra/snake_down_cabra_2");
            left1 = setup("/Sprites/Player/snake/cabra/snake_left_cabra_1");
            left2 = setup("/Sprites/Player/snake/cabra/snake_left_cabra_2");
            right1 = setup("/Sprites/Player/snake/cabra/snake_right_cabra_1");
            right2 = setup("/Sprites/Player/snake/cabra/snake_right_cabra_2");
        }

    }
    @Override
    public BufferedImage setup(String imageName){

        UtilityTool uTool = new UtilityTool();
        BufferedImage image = null;

        try {
            image = ImageIO.read(getClass().getResourceAsStream(imageName + ".png"));
            image = uTool.scaleImage(image, j.tileSize, j.tileSize);


        } catch (IOException e) {
            System.err.println("Error loading tile image: " + e.getMessage());
        }
        return image;

    }
    public void update() {
        if (keyH.upPressed == true || keyH.downPressed == true || keyH.leftPressed == true
                || keyH.rightPressed == true) {

            if (keyH.upPressed == true) {
                direction = "up";
            } else if (keyH.downPressed == true) {
                direction = "down";
            } else if (keyH.leftPressed == true) {
                direction = "left";
            } else if (keyH.rightPressed == true) {
                direction = "right";
            }

            // CHECK TILE COLLISION
            collisionOn = false;
            j.cChecker.checkTile(this);

            // SE A COLISÃO ESTIVER DESLIGADA O PLAYER NÃO SE MOVE
            if (collisionOn == false) {
                switch (direction) {
                    case "up":
                        worldY -= speed;
                        break;
                    case "down":
                        worldY += speed;
                        break;
                    case "left":
                        worldX -= speed;
                        break;
                    case "right":
                        worldX += speed;
                        break;
                }
            }

            spriteCounter++;
            if (spriteCounter > 10) {
                if (spriteNum == 1) {
                    spriteNum = 2;
                } else if (spriteNum == 2) {
                    spriteNum = 1;
                }
                spriteCounter = 0;
            }
        }
        if (keyH.ePressed) {
            int itemIndex = j.cChecker.checkItem(this, true);
            pickUpItem(itemIndex);
            keyH.ePressed = false;
        }
    }

    public void pickUpItem(int i) {
        if (i != 999) {
            String itemName = j.item[i].nome;

            switch (itemName) {
                case "Pe de Cabra":
<<<<<<< HEAD
                    hasPeDeCabra++;
                    if (hasPeDeCabra > 1) {
                        j.ui.showMessage("Pe de Cabra já foi Adquirido!");
                        hasPeDeCabra = 1;
                        break;
=======
                    j.item[i] = null;

                    // audio
                    j.playSE(1);

                    hasPeDeCabra++;

                    j.ui.showMessage("Pe de Cabra Adquirido!");

                    if (hasPeDeCabra == 2) {
                        j.ui.gameFinished = true;

>>>>>>> 18a9c3b58c326ace25cf712346ef4f7997a3b143
                    }
                    j.item[i] = null;
                    j.ui.showMessage("Pe de Cabra Adquirido!");
                    break;
            }
        }
    }

    @Override
    public void draw(Graphics2D g2) {
        BufferedImage img = null;
        
        if(hasPeDeCabra >= 1 && peDeCabraEquipped == false){
            getPlayerImage();
            peDeCabraEquipped = true;
        }
        switch (direction) {
            case "up":
                if (spriteNum == 1)
                    img = up1;
                if (spriteNum == 2)
                    img = up2;
                break;
            case "down":
                if (spriteNum == 1)
                    img = down1;
                if (spriteNum == 2)
                    img = down2;
                break;
            case "left":
                if (spriteNum == 1)
                    img = left1;
                if (spriteNum == 2)
                    img = left2;
                break;
            case "right":
                if (spriteNum == 1)
                    img = right1;
                if (spriteNum == 2)
                    img = right2;
                break;
        }

        g2.drawImage(img, screenX, screenY, null);
    }
}