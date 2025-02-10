package Principal;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JPanel;
/**
 *
 * @author caiom
 */
public class Janela extends JPanel implements Runnable {
    final int originalTileSize = 32; // 32 x 32
    final int scale = 2; // 2x32 = 64
    final int tileSize = originalTileSize * scale; // 64x64

    // Obtendo a resolução da tela automaticamente
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    final int screenWidth = (int) screenSize.getWidth();
    final int screenHeight = (int) screenSize.getHeight();

    Thread gameThread;
    
    // construtor
    public Janela() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.GREEN);
        this.setDoubleBuffered(true);
        this.setFocusable(true);
        this.requestFocus();
    }


    @Override
    public void run(){

    }

    public void iniciarGameThread() {
        if (gameThread == null) {
            gameThread = new Thread(this);
            gameThread.start();
        }
    }

    // getters
    public int getScreenWidth() {
        return screenWidth;
    }

    public int getScreenHeight() {
        return screenHeight;
    }
}
