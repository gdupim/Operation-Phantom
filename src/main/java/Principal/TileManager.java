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

        tile = new Tiles[10];
        mapTileNum = new int[j.maxWorldRow][j.maxWorldCol];

        getTileImage();
        loadMap("/Mapas/grid_map.txt");
    }

    public void getTileImage() {

        setup(0, "grama_grid", false);

    }

    public void setup(int index, String imageName, boolean collision) {

        UtilityTool uTool = new UtilityTool();

        try 
        {
            tile[index] = new Tiles();
            tile[index].image = ImageIO.read(getClass().getResourceAsStream("/Principal/tilesSrc/"+imageName + ".png"));
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
                        mapTileNum[row][col] = num;
                    } else {
                        System.err.println("Error: Not enough numbers in line for column " + col);
                    }

                    mapTileNum[row][col] = num;
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
            int tileNum = mapTileNum[worldRow][worldCol];

            int worldX = worldCol * j.tileSize;
            int worldY = worldRow * j.tileSize;
            int screenX = worldX - j.player.worldX + j.player.screenX;
            int screenY = worldY - j.player.worldY + j.player.screenY;

            if (worldX + j.tileSize > j.player.worldX - j.player.screenX &&
                    worldX - j.tileSize < j.player.worldX + j.player.screenX &&
                    worldY + j.tileSize > j.player.worldY - j.player.screenY &&
                    worldY - j.tileSize < j.player.worldY + j.player.screenY)
                g2.drawImage(tile[tileNum].image, screenX, screenY, null);

            worldCol++;

            if (worldCol == j.maxWorldCol) {
                worldCol = 0;
                worldRow++;
            }
        }
    }
}