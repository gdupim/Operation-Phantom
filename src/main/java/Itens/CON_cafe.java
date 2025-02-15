package Itens;

import java.io.IOException;

import javax.imageio.ImageIO;

import Principal.Janela;

public class CON_cafe extends Item {



    public CON_cafe(Janela j) {
         nome = "Café";

        try {
            image = ImageIO.read(getClass().getResourceAsStream("/Sprites/Itens/CON_cafe.png"));
            uTool.scaleImage(image, j.tileSize, j.tileSize); 
        } catch (IOException e) {
            System.err.println("Error loading image: " + e.getMessage());
        }
        collision = false;
    }
}
