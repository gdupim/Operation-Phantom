package Principal;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;

import Itens.ITEM_PeDeCabra;

public class UI {
    Janela j;
    Graphics2D g2;
    Font retroGaming;
    BufferedImage peDeCabraImage;
    public boolean messageOn = false;
    public String message = "";
    int messageCounter = 0;
    public boolean gameFinished = false;
    public double playTime = 0;
    DecimalFormat dFormat = new DecimalFormat("#0.00");
    public String  currentDialogue = "";

    public UI(Janela j) {
        this.j = j;

        try {
            InputStream is = getClass().getResourceAsStream("/Principal/fontSrc/Retro Gaming.ttf");
            retroGaming = Font.createFont(Font.TRUETYPE_FONT, is);

        }
        catch (FontFormatException | IOException e) {
            System.out.println("Erro ao carregar fonte");
        }

        ITEM_PeDeCabra peDeCabra = new ITEM_PeDeCabra(j);
        peDeCabraImage = peDeCabra.image;

    }

    public void showMessage(String text) {
        message = text;
        messageOn = true;
    }

    public void draw(Graphics2D g2) {
        this.g2 = g2;

        g2.setFont(retroGaming);
        g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        g2.setColor(Color.WHITE);

        if(j.gameState == j.playState){
            
            if (gameFinished) {

                g2.setFont(g2.getFont().deriveFont(Font.BOLD, 50));
                g2.setColor(Color.RED);
                g2.drawString("Parabéns!", j.tileSize * 2 / 2, 576 / 2);
                g2.setFont(g2.getFont().deriveFont(Font.BOLD, 50));
                g2.drawString("Tempo: " + dFormat.format(playTime), j.tileSize * 2 / 2, 576 / 2 + 60);
                j.gameThread = null;

            } else {

                if (j.player.hasPeDeCabra >= 1) {
                    g2.drawImage(peDeCabraImage, j.tileSize / 2, j.tileSize / 2, j.tileSize, j.tileSize, null);
                }

                g2.setFont(g2.getFont().deriveFont(Font.BOLD, 14));
                g2.setColor(Color.WHITE);
                g2.drawString("Especial", j.tileSize / 2, 540);

                g2.setFont(g2.getFont().deriveFont(Font.BOLD, 13));
                g2.setColor(Color.WHITE);
                g2.drawString("Caixa", j.tileSize * 2, 540);

                // TIME
                g2.setFont(g2.getFont().deriveFont(Font.ITALIC, 20));
                playTime += (double) 1 / 60;
                g2.drawString("Tempo: " + dFormat.format(playTime), j.tileSize * 13, 74);

                if (messageOn) {
                    g2.setFont(g2.getFont().deriveFont(Font.BOLD, 12));
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
        if(j.gameState == j.dialogueState){
            drawDialogueScreen();
        }

    }
    public void drawPauseScreen() {

        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 50));
        String text = "PAUSED";
        int x = getXforCenteredText(text);

        int y = j.screenHeight / 2;

        g2.drawString(text, x, y);

    }

    public void drawDialogueScreen() {

        int x = j.tileSize * 2;
        int y = j.tileSize / 2;
        int width = j.screenWidth - j.tileSize * 4;
        int height = j.tileSize * 4;

        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 18));
        g2.setColor(Color.WHITE);
        drawSubWindow(x,y,width,height);
        x += j.tileSize;
        y += j.tileSize;
        for(String line: currentDialogue.split("\n")){
            g2.drawString(line, x, y);
            y += j.tileSize / 2;
        }

    }
    public void drawSubWindow(int x, int y, int width, int height) {

        Color c = new Color(0, 0, 0, 140);
        g2.setColor(c);
        g2.fillRoundRect(x, y, width, height, 45, 45);
        g2.setColor(Color.WHITE);
        g2.setStroke(new BasicStroke(4));
        g2.drawRoundRect(x+4, y+4, width-8, height-8, 35, 35);

    } 

    public int getXforCenteredText(String text){

        int length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        int x = j.screenWidth / 2 - length / 2;
        return x;

    }
}
