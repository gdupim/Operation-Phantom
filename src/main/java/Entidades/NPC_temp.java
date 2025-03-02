package Entidades;

import java.util.Random;

import Principal.Janela;

public class NPC_temp extends Entidade {
    public NPC_temp(Janela j) {
        super(j);

        type = 1;

        direction = "down";
        speed = 1;

        getImage();
        setDialogue();
    }

    public void setDialogue() {
        dialogue[0] = "Olá, eu sou um\nNPC!  ";
        dialogue[1] = "Zé é um otário...";
        dialogue[2] = "...e o gabriel também!";
    }

    public void setAction() {
        actionLockCounter++;

        if (actionLockCounter == 120) {
            Random random = new Random();
            int i = random.nextInt(100) + 1;

            if (i <= 25) {
                direction = "up";
            }
            if (i > 25 && i <= 50) {
                direction = "down";
            }
            if (i > 50 && i <= 75) {
                direction = "left";
            }
            if (i > 75 && i <= 100) {
                direction = "right";
            }
            actionLockCounter = 0;
        }
    }

    public void speak() {
        super.speak();
    }

    public void getImage() {
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