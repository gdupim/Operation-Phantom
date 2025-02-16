package Entidades;

import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import Principal.Janela;
import Principal.UtilityTool;

public class Entidade {
    
    // STATE
    Janela j;
    public int worldX, worldY;
    public String direction;
    public int spriteNum = 1;
    public Rectangle solidArea = new Rectangle(0,0,64,64);
    public Rectangle attackArea = new Rectangle(0,0,64,64);
    public int solidAreaDefaultX, solidAreaDefaultY;
    public boolean collisionOn = true;
    public boolean invicible = false;
    public String[] dialogue = new String[20]; 
    public int dialogueIndex = 0;
    boolean attacking = false;

    // SPRITES IMAGES
    public BufferedImage up1, up2, down1, down2, right1, right2, left1, left2;
    public BufferedImage attackUp1, attackUp2, attackDown1, attackDown2, attackRight1, attackRight2, attackLeft1, attackLeft2;
    
    // CHAR STATUS
    public int type;
    public String nome;
    public int speed;
    public int maxLife;
    public int life;
    
    // COUNTER
    public int spriteCounter = 0;
    public int invicibleCounter = 0;
    public int actionLockCounter = 0;
    
    public Entidade(Janela j) {
        this.j = j;
    }

    public void speak(){ 
        if(dialogue[dialogueIndex] == null){
            dialogueIndex = 0;
        }

        j.ui.currentDialogue = dialogue[dialogueIndex];
        dialogueIndex++;

        switch (j.player[j.playerIndex].direction) {
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
        j.cChecker.checkEntidade(this, j.npc);
        j.cChecker.checkEntidade(this, j.inimigo);
        boolean contactPlayer = j.cChecker.checkPlayer(this);

        if (this.type == 2 && contactPlayer == true) {
            if(j.player[j.playerIndex].invicible == false) {
                
            }
        }

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
        if (invicible == true) {
            invicibleCounter++;
            if (invicibleCounter > 30) {
                invicible = false;
                invicibleCounter = 0;
            }
        }
    }
    
    
    public void draw(Graphics2D g2){
        BufferedImage img = null;


        int screenX = worldX - j.player[j.playerIndex].worldX + j.player[j.playerIndex].screenX;
        int screenY = worldY - j.player[j.playerIndex].worldY + j.player[j.playerIndex].screenY;

        if (worldX + j.tileSize > j.player[j.playerIndex].worldX - j.player[j.playerIndex].screenX &&
                worldX - j.tileSize < j.player[j.playerIndex].worldX + j.player[j.playerIndex].screenX &&
                worldY + j.tileSize > j.player[j.playerIndex].worldY - j.player[j.playerIndex].screenY &&
                worldY - j.tileSize < j.player[j.playerIndex].worldY + j.player[j.playerIndex].screenY){

                    switch (direction) {
                        case "up":
                            if(attacking == false){ 
                                if (spriteNum == 1) img = up1;
                                if (spriteNum == 2) img = up2;
                            } else{
                                if (spriteNum == 1) img = attackUp1;
                                if (spriteNum == 2) img = attackUp2;
                            }
            
                            break;
                        case "down":
                            if(attacking == false){ 
                                if (spriteNum == 1) img = down1;
                                if (spriteNum == 2) img = down2;
                                
                            } else {
                                if (spriteNum == 1) img = attackDown1;
                                if (spriteNum == 2) img = attackDown2;
                            }
            
                            break;
                        case "left":
                            if(attacking == false){ 
                                if (spriteNum == 1) img = left1;
                                if (spriteNum == 2) img = left2;
                                
                            } else {
                                if (spriteNum == 1) img = attackLeft1;
                                if (spriteNum == 2) img = attackLeft2;
                            }
                            break;
                        case "right":
                            if(attacking == false){ 
                                if (spriteNum == 1) img = right1;
                                if (spriteNum == 2) img = right2;
                                
                        } else {
                            if (spriteNum == 1) img = attackRight1;
                            if (spriteNum == 2) img = attackRight2;
                        }
                            break;
                        }
                    }
                    if(invicible == true){
                        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.5f));                
                    }
                    if(j.cChecker.checkPlayer(this) == false){
                        attacking = false;
                    }
                    g2.drawImage(img, screenX, screenY, j.tileSize, j.tileSize, null);
                    g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0f));
    }
    public BufferedImage setup(String imagePath, int width, int height) {

        UtilityTool uTool = new UtilityTool();
        BufferedImage image = null;

        try {
            image = ImageIO.read(getClass().getResourceAsStream(imagePath + ".png"));
            image = uTool.scaleImage(image, width, height);


        } catch (IOException e) {
            System.err.println("Error loading image: " + e.getMessage());
        }
        return image;

    }
}
