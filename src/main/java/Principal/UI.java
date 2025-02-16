package Principal;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;

import javax.imageio.ImageIO;

import Itens.ITEM_PeDeCabra;
import Itens.ITEM_Vida;
import Itens.Item;

public class UI {

    BufferedImage backgroundTitleImage;
    BufferedImage backgroundCreditImage;

    Janela j;
    Graphics2D g2;
    Font retroGaming;
    BufferedImage peDeCabraImage, vida_cheia, vida_cheia_dano, vida_2, vida_2_dano, vida_1, vida_1_dano, vida_vazia;
    public boolean messageOn = false;
    public String message = "";
    int messageCounter = 0;
    public boolean gameFinished = false;
    public double playTime = 0;
    DecimalFormat dFormat = new DecimalFormat("#0.00");
    public String  currentDialogue = "";
    public int comandNum = 0;
    public int titleScreenState = 0; 

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

        try {
            backgroundTitleImage = ImageIO.read(new File("src/main/java/Principal/UISrc/TelasTitle/novaTelaLogginOP.png"));
        } catch (IOException e) {
            e.printStackTrace();    
        }

        try {
            backgroundCreditImage = ImageIO.read(new File("src/main/java/Principal/UISrc/TelasTitle/novaTelaCreditos.png"));
        } catch (IOException e) {
            e.printStackTrace();    
        }

        // HUD OBJECT
        Item vida = new ITEM_Vida(j);
        vida_cheia = vida.image;
        vida_cheia_dano = vida.image2;
        vida_2 = vida.image3;
        vida_2_dano = vida.image4;
        vida_1 = vida.image5;
        vida_1_dano = vida.image6;
        vida_vazia = vida.image7;
    

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

        //TITULO DO JOGO
        if(j.gameState == j.titleState){
            drawTitleScreen();
        }
        
        

        //ESTADO DO JOGO
        if(j.gameState == j.playState){
            drawVidaPlayer();
            if (gameFinished) {

                g2.setFont(g2.getFont().deriveFont(Font.BOLD, 50));
                g2.setColor(Color.RED);
                g2.drawString("Parabéns!", j.tileSize * 2 / 2, 576 / 2);
                g2.setFont(g2.getFont().deriveFont(Font.BOLD, 50));
                g2.drawString("Tempo: " + dFormat.format(playTime), j.tileSize * 2 / 2, 576 / 2 + 60);
                j.gameThread = null;

            } else {

                if (j.player[j.playerIndex].hasPeDeCabra >= 1) {
                    g2.drawImage(peDeCabraImage, j.tileSize*15, j.tileSize*8, j.tileSize, j.tileSize, null);
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
            drawVidaPlayer();
            drawPauseScreen();
        }
        if(j.gameState == j.dialogueState){
            drawVidaPlayer();
            drawDialogueScreen();
        }

    }

    public void drawVidaPlayer(){

        int x = j.tileSize/2;
        int y = j.tileSize/2;
        int i = 0;

        if(j.player[j.playerIndex].life == j.player[j.playerIndex].maxLife){
            g2.drawImage(vida_cheia, x, y, j.tileSize, j.tileSize, null);
        }
        else if(j.player[j.playerIndex].life == 2){
            g2.drawImage(vida_2, x, y, j.tileSize, j.tileSize, null);
        }
        else if(j.player[j.playerIndex].life == 1){
            g2.drawImage(vida_1, x, y, j.tileSize, j.tileSize, null);
        }
        else if(j.player[j.playerIndex].life == 0){
            g2.drawImage(vida_vazia, x, y, j.tileSize, j.tileSize, null);
        }
    }

    public void drawTitleScreen() {
        
        if(titleScreenState == 0 ){

            if (backgroundTitleImage != null) {
                g2.drawImage(backgroundTitleImage, 0, 0, j.screenWidth, j.screenHeight, null);
            }

            g2.setFont(g2.getFont().deriveFont(Font.BOLD, 50));
            String text = " ";
            int x = getXforCenteredText(text);
            int y = j.tileSize * 3;

            g2.setColor(Color.RED);
            g2.drawString(text, x, y);

            //MENU
            g2.setFont(g2.getFont().deriveFont(Font.BOLD, 25));

            text = "PLAY";
            x = getXforCenteredText(text);
            y += j.tileSize *3;
            g2.drawString(text, x, y);
            if (comandNum == 0) {
            g2.drawString(">", x-j.tileSize, y);
        }

            text = "CREDITS";
            x = getXforCenteredText(text);
            y += j.tileSize;
            g2.drawString(text, x, y);
            if (comandNum == 1) {
            g2.drawString(">", x-j.tileSize, y);
        }

            text = "EXIT";
            x = getXforCenteredText(text);
            y += j.tileSize;
            g2.drawString(text, x, y);
            if (comandNum == 2) {
                g2.drawString(">", x-j.tileSize, y);
            }
        
        }else if(titleScreenState == 1) {
            
            //SELECAO DE PERSONAGEM
            g2.setColor(Color.RED);
            g2.setFont(g2.getFont().deriveFont(Font.BOLD, 30));

            String text = "SELECIONE SEU PERSONAGEM";
            int x = getXforCenteredText(text);
            int y = j.tileSize * 2;
            g2.drawString(text, x, y);

            text = "OSVALDO PAI";
            x = getXforCenteredText(text);
            y = j.tileSize * 4;
            g2.drawString(text, x, y);
            if(comandNum == 0){
                g2.drawString(">", x-j.tileSize, y);
            }

            text = "OSVALDO JR";
            x = getXforCenteredText(text);
            y = j.tileSize * 5;
            g2.drawString(text, x, y);
            if(comandNum == 1){
                g2.drawString(">", x-j.tileSize, y);
            }

            text = "OSVALDO VALDO";
            x = getXforCenteredText(text);
            y = j.tileSize * 6;
            g2.drawString(text, x, y);
            if(comandNum == 2){
                g2.drawString(">", x-j.tileSize, y);
            }

            text = "BACK";
            x = getXforCenteredText(text);
            y = j.tileSize * 8;
            g2.drawString(text, x, y);
            if(comandNum == 3){
                g2.drawString(">", x-j.tileSize, y);
            }
        } else if(titleScreenState == 2){
            drawCreditScreen();
        }
    }

    public void drawCreditScreen() {

        if (backgroundCreditImage != null) {
            g2.drawImage(backgroundCreditImage, 0, 0, j.screenWidth, j.screenHeight, null);
        }

        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 35));
        String text = "CREDITOS:";
        int x = getXforCenteredText(text);
        int y = j.tileSize * 2;

        g2.setColor(Color.WHITE);
        g2.drawString(text, x, y);

        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 20));
        String text1 = "CAIO MACEDO";
        x = getXforCenteredText(text1);
        y = j.tileSize * 4;
        g2.drawString(text1, x, y);

        text = "JOSE CARLOS SEBEN";
        x = getXforCenteredText(text);
        y = j.tileSize * 5;
        g2.drawString(text, x, y);

        text = "GABRIEL DUPIM";
        x = getXforCenteredText(text);
        y = j.tileSize * 6;
        g2.drawString(text, x, y);

        g2.setColor(Color.RED);
        text = "BACK";
        x = getXforCenteredText(text);
        y = j.tileSize * 8;
        g2.drawString(text, x, y);
        if(comandNum == 1){
        g2.drawString(">", x-j.tileSize, y);

        //IMPRIMINDO OS RA'S
        g2.setColor(Color.WHITE);
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 12));
        String controlText1 = "RA: 2651378";
        x = getXforCenteredText(controlText1);
        y = (int) (j.tileSize * 4.30);
        g2.drawString(controlText1, x, y);
        
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 12));
        String controlText2 = "RA: 2651130";
        x = getXforCenteredText(controlText2);
        y = (int) (j.tileSize * 5.30);
        g2.drawString(controlText2, x, y);

        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 12));
        String controlText3 = "RA: 2651130";
        x = getXforCenteredText(controlText3);
        y = (int) (j.tileSize * 6.30);
        g2.drawString(controlText3, x, y);

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
