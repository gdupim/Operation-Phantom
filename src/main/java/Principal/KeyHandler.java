package Principal;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


/**
 *
 * @author caiom
 */
public class KeyHandler implements KeyListener {

    public boolean upPressed, downPressed, leftPressed, rightPressed, escPressed, ePressed, enterPressed;
    Janela j;


    public KeyHandler(Janela j){
        this.j = j;
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        // Play State
        if(j.gameState == j.playState){

            if (code == KeyEvent.VK_W) {
                upPressed = true;
            }

            if (code == KeyEvent.VK_S) {
                downPressed = true;
            }

            if (code == KeyEvent.VK_A) {
                leftPressed = true;
            }

            if (code == KeyEvent.VK_D) {
                rightPressed = true;
            }
            if (code == KeyEvent.VK_E) {
                ePressed = true;
            }
            if (code == KeyEvent.VK_ESCAPE) {
                    j.gameState = j.pauseState; 
                
        }
    }   
        // Pause State
        else if (j.gameState == j.pauseState) {
            if (code == KeyEvent.VK_ESCAPE) 
                j.gameState = j.playState; 
         }

        // Dialogue State
        else if (j.gameState == j.dialogueState){
            if(code == KeyEvent.VK_ENTER){
                j.gameState = j.playState;
            }
         }
    
}

    @Override
    public void keyReleased(KeyEvent e) {

        int code = e.getKeyCode();

        if (code == KeyEvent.VK_W) {
            upPressed = false;
        }

        if (code == KeyEvent.VK_S) {
            downPressed = false;
        }

        if (code == KeyEvent.VK_A) {
            leftPressed = false;
        }

        if (code == KeyEvent.VK_D) {
            rightPressed = false;
        }
        if (code == KeyEvent.VK_E) {
            ePressed = false;
        }
        
    }
}