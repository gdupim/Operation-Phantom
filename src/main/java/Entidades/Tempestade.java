package Entidades;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import Principal.Janela;
import Principal.KeyHandler;
import Principal.UtilityTool;

public class Tempestade extends Player {
    public Tempestade(Janela j, KeyHandler keyH) {
        super(j, keyH);
    }
    
        public void setDefaultValues() {

            super.setDefaultValues();

            worldX = j.tileSize * 23;
            worldY = j.tileSize * 13;
            speed = 6;
            direction = "down";
    
            maxLife = 1;
            life = maxLife;
        }
        public void getPlayerImage() {
        
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
        else{
            up1 = setup("/Sprites/Player/temp/mov/temp_up_1");
            up2 = setup("/Sprites/Player/temp/mov/temp_up_2");
            down1 = setup("/Sprites/Player/temp/mov/temp_down_1");
            down2 = setup("/Sprites/Player/temp/mov/temp_down_2");
            left1 = setup("/Sprites/Player/temp/mov/temp_left_1");
            left2 = setup("/Sprites/Player/temp/mov/temp_left_2");
            right1 = setup("/Sprites/Player/temp/mov/temp_right_1");
            right2 = setup("/Sprites/Player/temp/mov/temp_right_2");
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

    public void interactNPC(int i) {

        if (i != 999) {
            if (keyH.ePressed) {
                j.gameState = j.dialogueState;
                j.npc[i].speak();
                //keyH.ePressed = false;
            }
        }
    }
}


