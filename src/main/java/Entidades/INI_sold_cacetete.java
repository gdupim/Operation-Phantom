package Entidades;

import java.util.Random;

import Principal.Janela;

public class INI_sold_cacetete extends Entidade {
    Janela j;

    public INI_sold_cacetete(Janela j) {
        super(j);

        this.j = j;

        type = 2;
        nome = "soldado de cacetete";
        speed = 3;
        maxLife = 2;
        life = maxLife;
        direction = "down";

        getImage();
        getAttackImage();

    }

    public void getImage() {
        up1 = setup("/Sprites/Inimigos/sold_melee/sold_cacete_1", j.tileSize, j.tileSize);
        up2 = setup("/Sprites/Inimigos/sold_melee/sold_cacete_2", j.tileSize, j.tileSize);
        down1 = setup("/Sprites/Inimigos/sold_melee/sold_cacete_1", j.tileSize, j.tileSize);
        down2 = setup("/Sprites/Inimigos/sold_melee/sold_cacete_2", j.tileSize, j.tileSize);
        left1 = setup("/Sprites/Inimigos/sold_melee/sold_cacete_1", j.tileSize, j.tileSize);
        left2 = setup("/Sprites/Inimigos/sold_melee/sold_cacete_2", j.tileSize, j.tileSize);
        right1 = setup("/Sprites/Inimigos/sold_melee/sold_cacete_1", j.tileSize, j.tileSize);
        right2 = setup("/Sprites/Inimigos/sold_melee/sold_cacete_2", j.tileSize, j.tileSize);
    }

    public void getAttackImage() {
        attackUp1 = setup("/Sprites/Inimigos/sold_melee/sold_cacete_atk", j.tileSize, j.tileSize);
        attackUp2 = setup("/Sprites/Inimigos/sold_melee/sold_cacete_atk", j.tileSize, j.tileSize);
        attackDown1 = setup("/Sprites/Inimigos/sold_melee/sold_cacete_atk", j.tileSize, j.tileSize);
        attackDown2 = setup("/Sprites/Inimigos/sold_melee/sold_cacete_atk", j.tileSize, j.tileSize);
        attackLeft1 = setup("/Sprites/Inimigos/sold_melee/sold_cacete_atk", j.tileSize, j.tileSize);
        attackLeft2 = setup("/Sprites/Inimigos/sold_melee/sold_cacete_atk", j.tileSize, j.tileSize);
        attackRight1 = setup("/Sprites/Inimigos/sold_melee/sold_cacete_atk", j.tileSize, j.tileSize);
        attackRight2 = setup("/Sprites/Inimigos/sold_melee/sold_cacete_atk", j.tileSize, j.tileSize);
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
}