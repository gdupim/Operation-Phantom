package Principal;

import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.InputStream;

import javax.imageio.ImageIO;

/**
 *
 * @author caiom
 */
public class TileManager {
    Tiles tile[];
    Janela j;
    int mapTileNum[][];

    public TileManager(Janela j) {
        this.j = j;
        
        tile = new Tiles[10];
        mapTileNum = new int[j.maxWorldRow][j.maxWorldCol];

        getTileImage();
        loadMap("/Mapas/map_grid.txt");
        }
    
    public void getTileImage(){
        
        try {
            
            tile[0] = new Tiles();
            tile[0].image = ImageIO.read(getClass().getResourceAsStream("/Sprites/grama_grid.png"));


        } catch (Exception e) {
            e.printStackTrace();
            }
    
            
        }
    
    public void loadMap(String path){
        try {
            InputStream is = getClass().getResourceAsStream(path);
            BufferedReader br = new BufferedReader(new java.io.InputStreamReader(is));

            int col = 0;
            int row = 0;

            while(col< j.maxWorldCol && row< j.maxWorldRow){

                String line = br.readLine();
                
                while(col < j.maxWorldCol) {

                    String numbers[] = line.split(" ");
                    
                    int num = Integer.parseInt(numbers[col]);

                    mapTileNum[row][col] = num;
                    col++;
                }         
                if(col == j.maxWorldCol){
                    col = 0;
                    row++;
                }
                br.close();
            }

        } catch (Exception e) {
            // TODO: handle exception
        }
        
    } 
    public void draw(Graphics2D g2){
        
        int worldCol = 0;
        int worldRow = 0;
 
        while(worldCol < j.maxWorldCol && worldRow < j.maxWorldRow){
            
            int tileNum = mapTileNum[worldRow][worldCol];

            int worldX = worldCol * j.tileSize;
            int worldY = worldRow * j.tileSize;
            int screenX = worldX - j.player.worldX + j.player.screenX;
            int screenY = worldY - j.player.worldY + j.player.screenY;

            g2.drawImage(tile[tileNum].image, screenX, screenY,j.tileSize, j.tileSize, null);
            worldCol++;


            if(worldCol == j.maxWorldCol){
                worldCol = 0;
                worldRow++;

            }

        
        }


    }
}
