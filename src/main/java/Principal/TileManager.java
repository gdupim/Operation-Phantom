package Principal;

import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

public class TileManager {
    public Tiles tile[];
    Janela j;
    public int mapTileNum[][];

    public TileManager(Janela j) {
        this.j = j;

        tile = new Tiles[30];
        mapTileNum = new int[j.maxWorldRow][j.maxWorldCol];

        loadMap("/Mapas/test_map.txt");
        getTileImage();
    }

    public void getTileImage() {
        setup(0, "misc/grama_grid", false);
        setup(1, "chao/tile_aslfalto", false);
        setup(2, "chao/tile_azulejo", false);
        setup(3, "chao/tile_chao_metal", false);
        setup(4, "parede/metal/tile_canto_inf_dir", true);
        setup(5, "parede/metal/tile_canto_inf_esq", true);
        setup(6, "parede/metal/tile_canto_sup_dir", true);
        setup(7, "parede/metal/tile_canto_sup_esq", true);
        setup(8, "parede/metal/tile_lateral", true);
        setup(9, "parede/metal/tile_vertical", true);
        setup(10, "misc/tile_box", false);
        setup(11, "misc/tile_box2", false);
        setup(12, "Maquina_refri", true);
    }

    public void setup(int index, String imageName, boolean collision) {
        UtilityTool uTool = new UtilityTool();

        try {
            tile[index] = new Tiles();
            tile[index].image = ImageIO
                    .read(getClass().getResourceAsStream("/Principal/tilesSrc/" + imageName + ".png"));
            tile[index].image = uTool.scaleImage(tile[index].image, j.tileSize, j.tileSize);
            tile[index].collision = collision;

        } catch (IOException e) {
            System.err.println("Error loading tile image: " + e.getMessage());
        }
    }

    public void loadMap(String path) {
        try {
            InputStream is = getClass().getResourceAsStream(path);
            BufferedReader br = new BufferedReader(new java.io.InputStreamReader(is));

            int col = 0;
            int row = 0;

            int num = 0;
            while (col < j.maxWorldCol && row < j.maxWorldRow) {
                String line = br.readLine();

                while (col < j.maxWorldCol) {
                    String numbers[] = line.split(" ");
                    if (col < numbers.length) {
                        num = Integer.parseInt(numbers[col]);
                        mapTileNum[col][row] = num;
                    } else {
                        System.err.println("Error: Not enough numbers in line for column " + col);
                    }

                    mapTileNum[col][row] = num;
                    col++;
                }
                if (col == j.maxWorldCol) {
                    col = 0;
                    row++;
                }
            }
            br.close();

        } catch (IOException | NumberFormatException e) {
            System.err.println("Error loading map: " + e.getMessage());
        }
    }

    public void draw(Graphics2D g2) {
        int worldCol = 0;
        int worldRow = 0;

        while (worldCol < j.maxWorldCol && worldRow < j.maxWorldRow) {
            int tileNum = mapTileNum[worldCol][worldRow];

            int worldX = worldCol * j.tileSize;
            int worldY = worldRow * j.tileSize;
            int screenX = worldX - j.player[j.playerIndex].worldX + j.player[j.playerIndex].screenX;
            int screenY = worldY - j.player[j.playerIndex].worldY + j.player[j.playerIndex].screenY;

            if (worldX + j.tileSize > j.player[j.playerIndex].worldX - j.player[j.playerIndex].screenX &&
                    worldX - j.tileSize < j.player[j.playerIndex].worldX + j.player[j.playerIndex].screenX &&
                    worldY + j.tileSize > j.player[j.playerIndex].worldY - j.player[j.playerIndex].screenY &&
                    worldY - j.tileSize < j.player[j.playerIndex].worldY + j.player[j.playerIndex].screenY)
                g2.drawImage(tile[tileNum].image, screenX, screenY, null);

            worldCol++;

            if (worldCol == j.maxWorldCol) {
                worldCol = 0;
                worldRow++;
            }
        }
    }
}