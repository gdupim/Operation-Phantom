package Itens;

import java.io.IOException;

import javax.imageio.ImageIO;

import Principal.Janela;

public class ITEM_Vida extends Item {

    Janela j;

    public ITEM_Vida(Janela j) {
        
        this.j = j;

        nome = "Vida";


        try {
            
            image = ImageIO.read(getClass().getResource("/Principal/UIsrc/BarraVida/UI_tres_de_vida.png"));
            image2 = ImageIO.read(getClass().getResource("/Principal/UIsrc/BarraVida/UI_tres_de_vida_dano.png"));
            image3 = ImageIO.read(getClass().getResource("/Principal/UIsrc/BarraVida/UI_dois_de_vida.png"));
            image4 = ImageIO.read(getClass().getResource("/Principal/UIsrc/BarraVida/UI_dois_de_vida_dano.png"));
            image5 = ImageIO.read(getClass().getResource("/Principal/UIsrc/BarraVida/UI_um_de_vida.png"));
            image6 = ImageIO.read(getClass().getResource("/Principal/UIsrc/BarraVida/UI_um_de_vida_dano.png"));
            image7 = ImageIO.read(getClass().getResource("/Principal/UIsrc/BarraVida/UI_vida_vazia.png"));

            image = uTool.scaleImage(image, j.tileSize+50, j.tileSize-15);
            image2 = uTool.scaleImage(image2, j.tileSize+50, j.tileSize-15);
            image3 = uTool.scaleImage(image3, j.tileSize+50, j.tileSize-15);
            image4 = uTool.scaleImage(image4, j.tileSize+50, j.tileSize-15);
            image5 = uTool.scaleImage(image5, j.tileSize+50, j.tileSize-15);
            image6 = uTool.scaleImage(image6, j.tileSize+50, j.tileSize-15);
            image7 = uTool.scaleImage(image7, j.tileSize+50, j.tileSize-15);

        } catch (IOException e) {
            System.err.println("Error loading image: " + e.getMessage());
        }
    }

    
}