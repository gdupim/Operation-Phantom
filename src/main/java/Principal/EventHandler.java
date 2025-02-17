package Principal;

public class EventHandler {

    Janela j;
    EventRect eventRect[][];

    public EventHandler(Janela j) {
        this.j = j;

        eventRect = new EventRect[j.maxWorldCol][j.maxWorldRow];

        int col = 0;
        int row = 0;

        while (col < j.maxWorldCol && row < j.maxWorldRow) {
            eventRect[col][row] = new EventRect();
            eventRect[col][row].x = 0;
            eventRect[col][row].y = 0;
            eventRect[col][row].width = j.tileSize;
            eventRect[col][row].height = j.tileSize;
            eventRect[col][row].eventRectDefaultX = eventRect[col][row].x;
            eventRect[col][row].eventRectDefaultY = eventRect[col][row].y;

            col++;
            if (col == j.maxWorldCol) {
                col = 0;
                row++;
            }

        }

    }

    public void checkEvent() {
        if (hit(2, 2, "right") == true) {
            damagePit(2, 2, j.dialogueState);
        }
        if (hit(7, 2, "any") == true) {
            healingRefri(7, 2, j.dialogueState);
        }
    }

    public boolean hit(int col, int row, String reqDirection) {
        boolean hit = false;

        j.player[j.playerIndex].solidArea.x = j.player[j.playerIndex].worldX + j.player[j.playerIndex].solidArea.x;
        j.player[j.playerIndex].solidArea.y = j.player[j.playerIndex].worldY + j.player[j.playerIndex].solidArea.y;
        eventRect[col][row].x = col * j.tileSize + eventRect[col][row].x;
        eventRect[col][row].y = row * j.tileSize + eventRect[col][row].y;

        if (j.player[j.playerIndex].solidArea.intersects(eventRect[col][row])) {
            if (j.player[j.playerIndex].direction.equals(reqDirection) || reqDirection.equals("any")) {
                hit = true;
            }
        }

        j.player[j.playerIndex].solidArea.x = j.player[j.playerIndex].solidAreaDefaultX;
        j.player[j.playerIndex].solidArea.y = j.player[j.playerIndex].solidAreaDefaultY;
        eventRect[col][row].x = eventRect[col][row].eventRectDefaultX;
        eventRect[col][row].y = eventRect[col][row].eventRectDefaultY;

        return hit;
    }

    public void damagePit(int col, int row, int gameState) {
        j.gameState = gameState;
        j.ui.currentDialogue = "você caiu no buraco";
        j.player[j.playerIndex].life = 0;
    }

    public void healingRefri(int col, int row, int gameState) {
        if (j.keyH.ePressed == true) {
            j.gameState = gameState;
            j.ui.currentDialogue = "você bebeu o refri\nSua vida foi restaurada por completo.";
            j.player[j.playerIndex].life = j.player[j.playerIndex].maxLife;
        }
    }
}