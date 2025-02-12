package Principal;

import Entidades.Entidade;

public class CollisionChecker {
    Janela j;

    public CollisionChecker(Janela j) {
        this.j = j;
    }

    public void checkTile(Entidade Entidades) {
        int entityLeftWorldX = Entidades.worldX + Entidades.solidArea.x;
        int entityRightWorldX = Entidades.worldX + Entidades.solidArea.x + Entidades.solidArea.width;
        int entityTopWorldY = Entidades.worldY + Entidades.solidArea.y;
        int entityBottomWorldY = Entidades.worldY + Entidades.solidArea.y + Entidades.solidArea.height;

        int entityLeftCol = entityLeftWorldX / j.tileSize;
        int entityRightCol = entityRightWorldX / j.tileSize;
        int entityTopRow = entityTopWorldY / j.tileSize;
        int entityBottomRow = entityBottomWorldY / j.tileSize;

        int tileNum1, tileNum2;

        switch (Entidades.direction) {
            case "up":
                entityTopRow = (entityTopWorldY - Entidades.speed) / j.tileSize;
                tileNum1 = j.tm.mapTileNum[entityLeftCol][entityTopRow];
                tileNum2 = j.tm.mapTileNum[entityRightCol][entityTopRow];

                if (j.tm.tile[tileNum1].colision == true || j.tm.tile[tileNum2].colision == true) {
                    Entidades.collisionOn = true;
                }

                break;
            case "down":
                entityBottomRow = (entityBottomWorldY + Entidades.speed) / j.tileSize;
                tileNum1 = j.tm.mapTileNum[entityLeftCol][entityBottomRow];
                tileNum2 = j.tm.mapTileNum[entityRightCol][entityBottomRow];

                if (j.tm.tile[tileNum1].colision == true || j.tm.tile[tileNum2].colision == true) {
                    Entidades.collisionOn = true;
                }

                break;
            case "left":
                entityLeftCol = (entityLeftWorldX - Entidades.speed) / j.tileSize;
                tileNum1 = j.tm.mapTileNum[entityLeftCol][entityTopRow];
                tileNum2 = j.tm.mapTileNum[entityLeftCol][entityBottomRow];

                if (j.tm.tile[tileNum1].colision == true || j.tm.tile[tileNum2].colision == true) {
                    Entidades.collisionOn = true;
                }

                break;
            case "right":
                entityRightCol = (entityRightWorldX + Entidades.speed) / j.tileSize;
                tileNum1 = j.tm.mapTileNum[entityRightCol][entityTopRow];
                tileNum2 = j.tm.mapTileNum[entityRightCol][entityBottomRow];

                if (j.tm.tile[tileNum1].colision == true || j.tm.tile[tileNum2].colision == true) {
                    Entidades.collisionOn = true;
                }

                break;
        }
    }
}
