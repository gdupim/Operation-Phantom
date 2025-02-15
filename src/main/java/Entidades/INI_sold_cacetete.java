package Entidades;

import java.util.Random;

import Principal.Janela;

public class INI_sold_cacetete extends Entidade {
    public INI_sold_cacetete(Janela j) {
        super(j);

        nome = "soldado de cacetete";
        speed = 3;
        maxLife = 2;
        life = maxLife;
        direction = "down";

        getImage();


    }   
    public void getImage() { 
        up1 = setup("/Sprites/Inimigos/sold_melee/sold_cacete_1");
        up2 = setup("/Sprites/Inimigos/sold_melee/sold_cacete_2");
        down1 = setup("/Sprites/Inimigos/sold_melee/sold_cacete_1");
        down2 = setup("/Sprites/Inimigos/sold_melee/sold_cacete_2");
        left1 = setup("/Sprites/Inimigos/sold_melee/sold_cacete_1");
        left2 = setup("/Sprites/Inimigos/sold_melee/sold_cacete_2");
        right1 = setup("/Sprites/Inimigos/sold_melee/sold_cacete_1");
        right2 = setup("/Sprites/Inimigos/sold_melee/sold_cacete_2");
    }
        public void setAction() {

        actionLockCounter++;

        if (actionLockCounter == 120){
            Random random = new Random();
            int i = random.nextInt(100)+1;

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
