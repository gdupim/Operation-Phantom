package Itens;

import java.awt.Graphics2D;

import Principal.Janela;

public class CON_cafe extends Item {

    public CON_cafe(Janela j) {
        nome = "Café";
        image = setup("/Sprites/Itens/CON_cafe", j.tileSize, j.tileSize);
    }

    public void draw(Graphics2D g2, Janela j) {
        int screenX = worldX - j.player[j.playerIndex].worldX + j.player[j.playerIndex].screenX;
        int screenY = worldY - j.player[j.playerIndex].worldY + j.player[j.playerIndex].screenY;

        if (worldX + j.tileSize > j.player[j.playerIndex].worldX - j.player[j.playerIndex].screenX &&
                worldX - j.tileSize < j.player[j.playerIndex].worldX + j.player[j.playerIndex].screenX &&
                worldY + j.tileSize > j.player[j.playerIndex].worldY - j.player[j.playerIndex].screenY &&
                worldY - j.tileSize < j.player[j.playerIndex].worldY + j.player[j.playerIndex].screenY)
            g2.drawImage(image, screenX, screenY, 24, 24, null);
    }

}
