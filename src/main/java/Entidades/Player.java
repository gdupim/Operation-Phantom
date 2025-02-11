package Entidades;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import Principal.Janela;
import Principal.KeyHandler;

public class Player extends Entidade{
    Janela j;
    KeyHandler keyH = new KeyHandler();
    
    public final int screenX;
    public final int screenY;
    
    public void setDefaultValues(){
        x = 100;
        y = 100;
        speed = 4;
        direction = "down";
    }
    public Player(Janela j, KeyHandler keyH) {
        this.j = j;
        this.keyH = keyH;
        screenX = j.screenWidth/2;
        screenY = j.screenHeight/2;

        solidArea = new Rectangle(0, 0, 64, 64);

        setDefaultValues();
    }


    public void getPlayerImage(){
        try {
            up1 = ImageIO.read(getClass().getResourceAsStream("/Player/snake/snake_up_1.png"));
            up2 = ImageIO.read(getClass().getResourceAsStream("/Player/snake/snake_up_2.png"));
            down1 = ImageIO.read(getClass().getResourceAsStream("/Player/snake/snake_down_1.png"));
            down2 = ImageIO.read(getClass().getResourceAsStream("/Player/snake/snake_down_2.png"));
            left1 = ImageIO.read(getClass().getResourceAsStream("/Player/snake/snake_left_1.png"));
            left2 = ImageIO.read(getClass().getResourceAsStream("/Player/snake/snake_left_2.png"));
            right1 = ImageIO.read(getClass().getResourceAsStream("/Player/snake/snake_right_1.png"));
            right2 = ImageIO.read(getClass().getResourceAsStream("/Player/snake/snake_right_2.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void update(){
        if(keyH.upPressed == true || keyH.downPressed == true || keyH.leftPressed == true || keyH.rightPressed == true){
            if(keyH.upPressed){
                y -= speed;
                direction = "up";
            }
            if(keyH.downPressed){
                y += speed;
                direction = "down";
            }
            if(keyH.leftPressed){
                x -= speed;
                direction = "left";
            }
            if(keyH.rightPressed){
                x += speed;
                direction = "right";
            }
        }
        spriteCounter++;
        if (spriteCounter > 10) {
            if(spriteNum == 1){
                spriteNum = 2;
            }
            else if(spriteNum == 2){
                spriteNum = 1;
            }
            spriteCounter = 0;
        }
    
    }

    public void draw(Graphics2D g2){
        BufferedImage img = null;
        
        switch(direction){
            case "up" -> {
                if(spriteCounter == 1)
                    img = up1;
                if(spriteCounter == 2)
                    img = up2;
            }
            case "down" -> {
                if(spriteCounter == 1)
                    img = down1;
                if(spriteCounter == 2)
                    img = down2;
            }
            case "left" -> {
                if(spriteCounter == 1)
                    img = left1;
                if(spriteCounter == 2)
                    img = left2;
            }
            case "right" -> {
                if(spriteCounter == 1)
                    img = right1;
                if(spriteCounter == 2)
                    img = right2;
            }
        }

        g2.drawImage(img, x, y, j.tileSize, j.tileSize, null);
    }

}
