package Entidades;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import java.io.IOException;
import javax.imageio.ImageIO;

import Principal.Janela;
import Principal.KeyHandler;

/**
 *
 * @author caiom
 */
public class Player extends Entidade {
    Janela j;
    KeyHandler keyH = new KeyHandler();

    public final int screenX;
    public final int screenY;
    public int hasPeDeCabra = 0;
    public int worldX, x;
    public int worldY, y;

    public void setDefaultValues() {
        worldX = j.tileSize * 23;
        worldY = j.tileSize * 13;
        speed = 4;
        direction = "down";
    }

    @SuppressWarnings("OverridableMethodCallInConstructor")
    public Player(Janela j, KeyHandler keyH) {
        this.j = j;
        this.keyH = keyH;

        screenX = j.screenWidth / 2 - j.tileSize / 2;
        screenY = j.screenHeight / 2 - j.tileSize / 2;

        solidArea = new Rectangle(7, 10, 16, 8);
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        setDefaultValues();
    }

    public void getPlayerImage() {
        try {
            up1 = ImageIO.read(getClass().getResourceAsStream("/Sprites/Player/snake/mov/snake_up_1.png"));
            up2 = ImageIO.read(getClass().getResourceAsStream("/Sprites/Player/snake/mov/snake_up_2.png"));
            down1 = ImageIO.read(getClass().getResourceAsStream("/Sprites/Player/snake/mov/snake_down_1.png"));
            down2 = ImageIO.read(getClass().getResourceAsStream("/Sprites/Player/snake/mov/snake_down_2.png"));
            left1 = ImageIO.read(getClass().getResourceAsStream("/Sprites/Player/snake/mov/snake_left_1.png"));
            left2 = ImageIO.read(getClass().getResourceAsStream("/Sprites/Player/snake/mov/snake_left_2.png"));
            right1 = ImageIO.read(getClass().getResourceAsStream("/Sprites/Player/snake/mov/snake_right_1.png"));
            right2 = ImageIO.read(getClass().getResourceAsStream("/Sprites/Player/snake/mov/snake_right_2.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
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

            switch (j.item[i].nome) {
                case "Pe de Cabra":
                    j.item[i] = null;
                    hasPeDeCabra++;
                    j.ui.showMessage("Pe de Cabra Adquirido!");
                    if (hasPeDeCabra == 2) {
                        j.ui.gameFinished = true;
                    }
                    break;
            }
        }
    }

    public void draw(Graphics2D g2) {
        BufferedImage img = null;

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

        g2.drawImage(img, x, y, j.tileSize, j.tileSize, null);
    }
}