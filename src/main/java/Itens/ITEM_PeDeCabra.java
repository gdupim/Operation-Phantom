package Itens;


import java.io.IOException;

import javax.imageio.ImageIO;

public class ITEM_PeDeCabra extends Ferramenta{

    
    public ITEM_PeDeCabra(){
        
        nome = "Pe de Cabra";
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/Sprites/Itens/ITEM_PeDeCabra.png"));

        } catch (IOException e) {
        }
    }
}
