package Itens;

import Principal.Janela;

public class ITEM_Vida extends Item {

    Janela j;

    public ITEM_Vida(Janela j) {
        this.j = j;

        nome = "Vida";
        image = setup("/Principal/UIsrc/BarraVida/UI_tres_de_vida", j.tileSize, j.tileSize);
        image2 = setup("/Principal/UIsrc/BarraVida/UI_tres_de_vida_dano", j.tileSize, j.tileSize);
        image3 = setup("/Principal/UIsrc/BarraVida/UI_dois_de_vida", j.tileSize, j.tileSize);
        image4 = setup("/Principal/UIsrc/BarraVida/UI_dois_de_vida_dano", j.tileSize, j.tileSize);
        image5 = setup("/Principal/UIsrc/BarraVida/UI_um_de_vida", j.tileSize, j.tileSize);
        image6 = setup("/Principal/UIsrc/BarraVida/UI_um_de_vida_dano", j.tileSize, j.tileSize);
        image7 = setup("/Principal/UIsrc/BarraVida/UI_vida_vazia", j.tileSize, j.tileSize);
    }
}