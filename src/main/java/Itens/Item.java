package Itens;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import Principal.Janela;

public class Item {

    public BufferedImage image;
    public String nome;
    public boolean collision = false;
    public int worldX, worldY;

    public void draw(Graphics2D g2, Janela j) {

        int screenX = worldX - j.player.worldX + j.player.screenX;
        int screenY = worldY - j.player.worldY + j.player.screenY;

        if (worldX + j.tileSize > j.player.worldX - j.player.screenX &&
                worldX - j.tileSize < j.player.worldX + j.player.screenX &&
                worldY + j.tileSize > j.player.worldY - j.player.screenY &&
                worldY - j.tileSize < j.player.worldY + j.player.screenY)
            g2.drawImage(image, screenX, screenY, j.tileSize, j.tileSize, null);

    }

}
