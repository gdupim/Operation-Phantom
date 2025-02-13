package Entidades;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import Principal.Janela;
import Principal.UtilityTool;

public class Entidade {

    Janela j;
    public int worldX, worldY;
    public int speed;
    public BufferedImage up1, up2, down1, down2, right1, right2, left1, left2;
    public String direction;
    public int spriteCounter = 0;
    public int spriteNum = 1;
    public Rectangle solidArea = new Rectangle(0,0,64,64);
    public int solidAreaDefaultX, solidAreaDefaultY;
    public boolean collisionOn = true;
    public int actionLockCounter = 0;
    public String[] dialogue = new String[20]; 
    public int dialogueIndex = 0;

    public Entidade(Janela j) {
        this.j = j;
    }

    public void speak(){ 
        if(dialogue[dialogueIndex] == null){
            dialogueIndex = 0;
        }

        j.ui.currentDialogue = dialogue[dialogueIndex];
        dialogueIndex++;

        switch (j.player.direction) {
            case "up":
                direction = "down";
                break;
            case "down":
                direction = "up";
                break;
            case "left":
                direction = "right";
                break;
            case "right":
                direction = "left";
                break;
            default:
                throw new AssertionError();
        }

    }
    public void setAction() { }
    public void update(){

        setAction();

        collisionOn = false;
        j.cChecker.checkTile(this);
        j.cChecker.checkItem(this, false);
        j.cChecker.checkPlayer(this);

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
    
    
    public void draw(Graphics2D g2){

        BufferedImage img = null;

        int screenX = worldX - j.player.worldX + j.player.screenX;
        int screenY = worldY - j.player.worldY + j.player.screenY;

        if (worldX + j.tileSize > j.player.worldX - j.player.screenX &&
                worldX - j.tileSize < j.player.worldX + j.player.screenX &&
                worldY + j.tileSize > j.player.worldY - j.player.screenY &&
                worldY - j.tileSize < j.player.worldY + j.player.screenY){

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

                    g2.drawImage(img, screenX, screenY, j.tileSize, j.tileSize, null);
                }
    }
    public BufferedImage setup(String imagePath){

        UtilityTool uTool = new UtilityTool();
        BufferedImage image = null;

        try {
            image = ImageIO.read(getClass().getResourceAsStream(imagePath + ".png"));
            image = uTool.scaleImage(image, j.tileSize, j.tileSize);


        } catch (IOException e) {
            System.err.println("Error loading image: " + e.getMessage());
        }
        return image;

    }
}
