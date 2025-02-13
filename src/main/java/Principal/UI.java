package Principal;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.text.DecimalFormat;
import Itens.ITEM_PeDeCabra;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class UI {

    BufferedImage backgroundTitleImage;

    Janela j;
    Graphics2D g2;
    Font arial_36, arial_20, arial_40B;
    BufferedImage peDeCabraImage;
    public boolean messageOn = false;
    public String message = "";
    int messageCounter = 0;
    public boolean gameFinished = false;
    public double playTime = 0;
    DecimalFormat dFormat = new DecimalFormat("#0.00");

    public UI(Janela j) {
        this.j = j;

        arial_40B = new Font("Arial", Font.BOLD, 40);
        arial_36 = new Font("Arial", Font.PLAIN, 36);
        arial_20 = new Font("Arial", Font.PLAIN, 20);

        ITEM_PeDeCabra peDeCabra = new ITEM_PeDeCabra(j);
        peDeCabraImage = peDeCabra.image;

        try {
            backgroundTitleImage = ImageIO.read(new File("c:\\Users\\User\\Documents\\Faculdade - 2° Sementre\\Programação orientada a objetos\\tellaLoggin_P1.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showMessage(String text) {
        message = text;
        messageOn = true;
    }

    public void draw(Graphics2D g2) {
        this.g2 = g2;

        g2.setFont(arial_36);
        g2.setColor(Color.WHITE);

        //TITULO DO JOGO
        if(j.gameState == j.titleState){
            drawTitleScreen();
        }
       
        //ESTADO DO JOGO
        if(j.gameState == j.playState){
            
            if (gameFinished) {

                g2.setFont(arial_40B);
                g2.setColor(Color.RED);
                g2.drawString("Parabéns! Você encontrou os 2 Pés de Cabra!", j.tileSize * 2 / 2, 576 / 2);
                g2.setFont(arial_36);
                g2.drawString("Tempo: " + dFormat.format(playTime), j.tileSize * 2 / 2, 576 / 2 + 60);
                j.gameThread = null;

            } else {

                g2.setFont(arial_36);
                g2.setColor(Color.WHITE);
                g2.drawImage(peDeCabraImage, j.tileSize / 2, j.tileSize / 2, j.tileSize, j.tileSize, null);
                g2.drawString("x" + j.player.hasPeDeCabra, 80, 74);

                g2.setFont(arial_20);
                g2.setColor(Color.WHITE);
                g2.drawString("Especial", j.tileSize / 2, 540);

                g2.setFont(arial_20);
                g2.setColor(Color.WHITE);
                g2.drawString("Caixa", j.tileSize * 2, 540);

                // TIME
                playTime += (double) 1 / 60;
                g2.drawString("Tempo: " + dFormat.format(playTime), j.tileSize * 13, 74);

                if (messageOn) {
                    g2.setFont(arial_20);
                    g2.setColor(Color.WHITE);
                    g2.drawString(message, j.tileSize / 2, j.tileSize * 5);

                    messageCounter++;

                    if (messageCounter > 60) {
                        messageOn = false;
                        messageCounter = 0;
                    }
                }
            }
        }
        if(j.gameState == j.pauseState){
            drawPauseScreen();
        }

    }

    public void drawTitleScreen() {
        
        if (backgroundTitleImage != null) {
            g2.drawImage(backgroundTitleImage, 0, 0, j.screenWidth, j.screenHeight, null);
        }

    }
    public void drawPauseScreen() {

        String text = "PAUSED";
        int x = getXforCenteredText(text);

        int y = j.screenHeight / 2;

        g2.drawString(text, x, y);

    }

    public int getXforCenteredText(String text){

        int length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        int x = j.screenWidth / 2 - length / 2;
        return x;

    }
}
