package Principal;

import java.awt.Graphics2D;

/**
 *
 * @author caiom
 */
public class TileManager {
    Tiles tile[];
    Janela j;

    public TileManager(Janela j) {
        this.j = j;
        
        tile = new Tiles[10];
        
        getTileImage();
    }
    public void getTileImage(){
        
        try {
            
            tile[0] = new Tiles();
            


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void draw(Graphics2D g2){
        g2.drawImage(tile[0].image, 0, 0,j.tileSize, j.tileSize, null);
        g2.fillRect(0, 0, j.tileSize,j.tileSize);
        g2.fillRect(0, 64, j.tileSize,j.tileSize);
        g2.fillRect(0, 64*2, j.tileSize,j.tileSize);

    }
    
}