package Principal;

import java.awt.Color;
import java.awt.Graphics2D;
import static java.lang.Math.sqrt;

import Entidades.Entidade;

public class DDARayCast {
    public Graphics2D g2;
    public TileManager tm;
    public Janela j;

    public DDARayCast(Janela j) {
        this.j = j;
    }

    public void rayCast(Entidade entidade, Graphics2D g2) {
        g2.setColor(Color.PINK);
        g2.drawLine(entidade.worldX, entidade.worldY, j.player[j.playerIndex].worldX, j.player[j.playerIndex].worldY);
        double rayStartX = j.player[j.playerIndex].worldX;
        double rayStartY = j.player[j.playerIndex].worldY;
        double rayEndX = entidade.worldY - j.player[j.playerIndex].worldY;
        double rayEndY = entidade.worldX - j.player[j.playerIndex].worldX;
        double rayUnitStepSize[] = { sqrt(1 + (rayEndY * rayEndY) / (rayEndX * rayEndX)),
                sqrt(1 + (rayEndX * rayEndX) / (rayEndY * rayEndY)) };

        int mapCheckX = (int) rayStartX;
        int mapCheckY = (int) rayStartY;

        double rayLength1D[] = { 0, 0 };

        int stepX;
        int stepY;

        if (rayEndX < 0) {
            stepX = -1;
            rayLength1D[0] = (rayStartX - mapCheckX) * rayUnitStepSize[0];

        } else {
            stepX = 1;
            rayLength1D[0] = ((mapCheckX + 1) - rayStartX) * rayUnitStepSize[0];

        }
        if (rayEndY < 0) {
            stepY = -1;
            rayLength1D[1] = (rayStartY - mapCheckY) * rayUnitStepSize[1];

        } else {
            stepY = 1;
            rayLength1D[1] = ((mapCheckY + 1) - rayStartY) * rayUnitStepSize[1];

        }

        boolean tileFound = false;
        double maxDistance = 100;
        double distance = 0;
        while (!tileFound && distance < maxDistance) {
            if (rayLength1D[0] < rayLength1D[1]) {
                mapCheckX += stepX;
                distance = rayLength1D[0];
                rayLength1D[0] += rayUnitStepSize[0];

            } else {
                mapCheckY += stepY;
                distance = rayLength1D[1];
                rayLength1D[1] += rayUnitStepSize[1];
            }
            if (mapCheckX >= 0 && mapCheckX < j.worldWidth && mapCheckY >= 0 && mapCheckY < j.worldHeight) {
                System.out.println("Tile Found");
                tileFound = true;
            }

        }

        double intersectionX;
        double intersectionY;
        if (tileFound) {
            intersectionX = rayStartX + rayEndX * distance;
            intersectionY = rayStartY + rayEndY * distance;

            g2.drawArc((int) intersectionY, (int) intersectionX, 4, 4, 0, 360);
        }
    }
}