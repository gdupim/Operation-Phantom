package Itens;

import java.io.IOException;

import javax.imageio.ImageIO;

import Principal.Janela;

public class ITEM_PeDeCabra extends Ferramenta {

    public ITEM_PeDeCabra(Janela j) {
        nome = "Pe de Cabra";

        try {
            image = ImageIO.read(getClass().getResourceAsStream("/Sprites/Itens/ITEM_PeDeCabra.png"));
            uTool.scaleImage(image, j.tileSize, j.tileSize); 
        } catch (IOException e) {
            System.err.println("Error loading image: " + e.getMessage());
        }
        collision = false;
    }
}