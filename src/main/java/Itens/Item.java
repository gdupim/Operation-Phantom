package Itens;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import Principal.Janela;
import Principal.UtilityTool;

public class Item {
    public BufferedImage image, image2, image3, image4, image5, image6, image7;
    public String nome;
    public boolean collision = false;
    public int worldX, worldY;
    public Rectangle solidArea = new Rectangle(0,0,64,64);
    public int solidAreaDefaultX = 0;
    public int solidAreaDefaultY = 0;
    UtilityTool uTool = new UtilityTool();

    public void draw(Graphics2D g2, Janela j) {
        int screenX = worldX - j.player[j.playerIndex].worldX + j.player[j.playerIndex].screenX;
        int screenY = worldY - j.player[j.playerIndex].worldY + j.player[j.playerIndex].screenY;

        if (worldX + j.tileSize > j.player[j.playerIndex].worldX - j.player[j.playerIndex].screenX &&
                worldX - j.tileSize < j.player[j.playerIndex].worldX + j.player[j.playerIndex].screenX &&
                worldY + j.tileSize > j.player[j.playerIndex].worldY - j.player[j.playerIndex].screenY &&
                worldY - j.tileSize < j.player[j.playerIndex].worldY + j.player[j.playerIndex].screenY)
            g2.drawImage(image, screenX, screenY, j.tileSize, j.tileSize, null);
    }
}
