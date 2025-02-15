package Entidades;

import Principal.Janela;


public class NPC_temp extends Entidade {

    public NPC_temp(Janela j) {
        super(j);

        direction = "down";
        speed = 1;

        getImage();
    }
    
        public void getImage() {

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
