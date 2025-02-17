package Entidades;

import Principal.Janela;
import Principal.KeyHandler;

public class Tempestade extends Player {
    public Tempestade(Janela j, KeyHandler keyH) {
        super(j, keyH);
    }

    public void setDefaultValues() {
        super.setDefaultValues();

        type = 0;

        worldX = j.tileSize * 4;
        worldY = j.tileSize * 4;

        speed = 6;

        maxLife = 1;
        life = maxLife;
    }

    public void getPlayerImage() {
        if (hasPeDeCabra >= 1) {
            up1 = setup("/Sprites/Player/snake/cabra/snake_up_cabra_1", j.tileSize, j.tileSize);
            up2 = setup("/Sprites/Player/snake/cabra/snake_up_cabra_2", j.tileSize, j.tileSize);
            down1 = setup("/Sprites/Player/snake/cabra/snake_down_cabra_1", j.tileSize, j.tileSize);
            down2 = setup("/Sprites/Player/snake/cabra/snake_down_cabra_2", j.tileSize, j.tileSize);
            left1 = setup("/Sprites/Player/snake/cabra/snake_left_cabra_1", j.tileSize, j.tileSize);
            left2 = setup("/Sprites/Player/snake/cabra/snake_left_cabra_2", j.tileSize, j.tileSize);
            right1 = setup("/Sprites/Player/snake/cabra/snake_right_cabra_1", j.tileSize, j.tileSize);
            right2 = setup("/Sprites/Player/snake/cabra/snake_right_cabra_2", j.tileSize, j.tileSize);
        } else {
            up1 = setup("/Sprites/Player/temp/mov/temp_up_1", j.tileSize, j.tileSize);
            up2 = setup("/Sprites/Player/temp/mov/temp_up_2", j.tileSize, j.tileSize);
            down1 = setup("/Sprites/Player/temp/mov/temp_down_1", j.tileSize, j.tileSize);
            down2 = setup("/Sprites/Player/temp/mov/temp_down_2", j.tileSize, j.tileSize);
            left1 = setup("/Sprites/Player/temp/mov/temp_left_1", j.tileSize, j.tileSize);
            left2 = setup("/Sprites/Player/temp/mov/temp_left_2", j.tileSize, j.tileSize);
            right1 = setup("/Sprites/Player/temp/mov/temp_right_1", j.tileSize, j.tileSize);
            right2 = setup("/Sprites/Player/temp/mov/temp_right_2", j.tileSize, j.tileSize);
        }
    }

    public void interactNPC(int i) {
        if (i != 999) {
            if (keyH.ePressed) {
                j.gameState = j.dialogueState;
                j.npc[i].speak();
                // keyH.ePressed = false;
            }
        }
    }
}
