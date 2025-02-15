package Principal;

import java.awt.Rectangle;

public class EventHandler {

    Janela j;
    Rectangle eventRect;
    int eventRectDefaultX, eventRectDefaultY;

    public EventHandler(Janela j) {
        this.j = j;

        eventRect = new Rectangle(0, 0, 0, 0);
        eventRect.x = 0;
        eventRect.y = 0;
        eventRect.width = j.tileSize;
        eventRect.height = j.tileSize;
        eventRectDefaultX = eventRect.x;
        eventRectDefaultY = eventRect.y;

        
    }

    public void checkEvent(){

        if(hit(2,2 , "right") == true){damagePit(j.dialogueState);}
        if(hit(7,2 , "any") == true){healingRefri(j.dialogueState);}

    }

    public boolean hit(int eventCol, int eventRow, String reqDirection){

        boolean hit = false;

        j.player[j.playerIndex].solidArea.x = j.player[j.playerIndex].worldX + j.player[j.playerIndex].solidArea.x;
        j.player[j.playerIndex].solidArea.y = j.player[j.playerIndex].worldY + j.player[j.playerIndex].solidArea.y;
        eventRect.x = eventCol * j.tileSize + eventRect.x;
        eventRect.y = eventRow * j.tileSize + eventRect.y;

        if(j.player[j.playerIndex].solidArea.intersects(eventRect)){
            if(j.player[j.playerIndex].direction.equals(reqDirection) || reqDirection.equals("any")){
                hit = true;
            }
        }

        j.player[j.playerIndex].solidArea.x = j.player[j.playerIndex].solidAreaDefaultX;
        j.player[j.playerIndex].solidArea.y = j.player[j.playerIndex].solidAreaDefaultY;
        eventRect.x = eventRectDefaultX;    
        eventRect.y = eventRectDefaultY;

        return hit;
    }
    public void damagePit(int gameState){
        j.gameState = gameState;
        j.ui.currentDialogue = "você caiu no buraco";
        j.player[j.playerIndex].life = 0;
    }

    public void healingRefri(int gameState){

        if(j.keyH.ePressed == true){
            j.gameState = gameState;
            j.ui.currentDialogue = "você bebeu o refri\nSua vida foi restaurada por completo.";
            j.player[j.playerIndex].life = j.player[j.playerIndex].maxLife;
        }
    }
}