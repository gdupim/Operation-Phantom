package Entidades;

import java.awt.Rectangle;
import Principal.Janela;
import Principal.KeyHandler;

public class Player extends Entidade{
    Janela j;
    KeyHandler keyH;
    
    public final int screenX;
    public final int screenY;

    public Player(Janela j, KeyHandler keyH) {
        this.j = j;
        this.keyH = keyH;
        screenX = j.getScreenWidth()/2;
        screenY = j.getScreenHeight()/2;

        solidArea = new Rectangle(0, 0, 64, 64);
    }

}
