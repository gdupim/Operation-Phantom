package Entidades;

import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import Principal.Janela;
import Principal.KeyHandler;
import Principal.UtilityTool;

public class Player extends Entidade {
    KeyHandler keyH = new KeyHandler(j);

    public final int screenX;
    public final int screenY;
    public int hasPeDeCabra = 0;
    public boolean peDeCabraEquipped = false;

    @SuppressWarnings("OverridableMethodCallInConstructor")
    public Player(Janela j, KeyHandler keyH) {
        super(j);

        this.keyH = keyH;

        screenX = j.screenWidth / 2 - j.tileSize / 2;
        screenY = j.screenHeight / 2 - j.tileSize / 2;

        solidArea = new Rectangle(7, 10, 32, 32);
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

        attackArea.width = 36;
        attackArea.height = 36;

        setDefaultValues();
        getPlayerImage();
        getPlayerAttackImage();
    }

    public void setDefaultValues() {
        worldX = j.tileSize * 4;
        worldY = j.tileSize * 4;
        speed = 4;
        direction = "down";

        maxLife = 3;
        life = maxLife;
    }

    public void getPlayerImage() {
        up1 = setup("/Sprites/Player/snake/mov/snake_up_1");
        up2 = setup("/Sprites/Player/snake/mov/snake_up_2");
        down1 = setup("/Sprites/Player/snake/mov/snake_down_1");
        down2 = setup("/Sprites/Player/snake/mov/snake_down_2");
        left1 = setup("/Sprites/Player/snake/mov/snake_left_1");
        left2 = setup("/Sprites/Player/snake/mov/snake_left_2");
        right1 = setup("/Sprites/Player/snake/mov/snake_right_1");
        right2 = setup("/Sprites/Player/snake/mov/snake_right_2");

        if (hasPeDeCabra >= 1) {
            up1 = setup("/Sprites/Player/snake/cabra/snake_up_cabra_1");
            up2 = setup("/Sprites/Player/snake/cabra/snake_up_cabra_2");
            down1 = setup("/Sprites/Player/snake/cabra/snake_down_cabra_1");
            down2 = setup("/Sprites/Player/snake/cabra/snake_down_cabra_2");
            left1 = setup("/Sprites/Player/snake/cabra/snake_left_cabra_1");
            left2 = setup("/Sprites/Player/snake/cabra/snake_left_cabra_2");
            right1 = setup("/Sprites/Player/snake/cabra/snake_right_cabra_1");
            right2 = setup("/Sprites/Player/snake/cabra/snake_right_cabra_2");
        } else {
            up1 = setup("/Sprites/Player/snake/mov/snake_up_1", j.tileSize, j.tileSize);
            up2 = setup("/Sprites/Player/snake/mov/snake_up_2", j.tileSize, j.tileSize);
            down1 = setup("/Sprites/Player/snake/mov/snake_down_1", j.tileSize, j.tileSize);
            down2 = setup("/Sprites/Player/snake/mov/snake_down_2", j.tileSize, j.tileSize);
            left1 = setup("/Sprites/Player/snake/mov/snake_left_1", j.tileSize, j.tileSize);
            left2 = setup("/Sprites/Player/snake/mov/snake_left_2", j.tileSize, j.tileSize);
            right1 = setup("/Sprites/Player/snake/mov/snake_right_1", j.tileSize, j.tileSize);
            right2 = setup("/Sprites/Player/snake/mov/snake_right_2", j.tileSize, j.tileSize);
        }
    }

    public void getPlayerAttackImage() {
        // attackUp1 =
        // setup("/Sprites/Player/snake/attack/snake_up_attack_1",j.tileSize,
        // j.tileSize);
        // attackUp2 =
        // setup("/Sprites/Player/snake/attack/snake_up_attack_2",j.tileSize,
        // j.tileSize);
        // attackDown1 =
        // setup("/Sprites/Player/snake/attack/snake_down_attack_1,j.tileSize,
        // j.tileSize",j.tileSize, j.tileSize);
        // attackDown2 =
        // setup("/Sprites/Player/snake/attack/snake_down_attack_2,j.tileSize,
        // j.tileSize",j.tileSize, j.tileSize);
        // attackLeft1 =
        // setup("/Sprites/Player/snake/attack/snake_left_attack_1,j.tileSize,
        // j.tileSize",j.tileSize, j.tileSize);
        // attackLeft2 =
        // setup("/Sprites/Player/snake/attack/snake_left_attack_2,j.tileSize,
        // j.tileSize",j.tileSize, j.tileSize);
        // attackRight1 =
        // setup("/Sprites/Player/snake/attack/snake_right_attack_,j.tileSize,
        // j.tileSize1",j.tileSize, j.tileSize);
        // attackRight2 =
        // setup("/Sprites/Player/snake/attack/snake_right_attack_,j.tileSize,
        // j.tileSize2",j.tileSize, j.tileSize);

    }

    public BufferedImage setup(String imageName) {
        UtilityTool uTool = new UtilityTool();
        BufferedImage image = null;

        try {
            image = ImageIO.read(getClass().getResourceAsStream(imageName + ".png"));
            image = uTool.scaleImage(image, j.tileSize, j.tileSize);

        } catch (IOException e) {
            System.err.println("Error loading tile image: " + e.getMessage());
        }
        return image;

    }

    public void update() {

        if (keyH.upPressed == true || keyH.downPressed == true || keyH.leftPressed == true
                || keyH.rightPressed == true || keyH.ePressed == true || keyH.enterPressed == true) {

            if (keyH.upPressed == true) {
                direction = "up";
            } else if (keyH.downPressed == true) {
                direction = "down";
            } else if (keyH.leftPressed == true) {
                direction = "left";
            } else if (keyH.rightPressed == true) {
                direction = "right";
            }

            // CHECK TILE COLLISION
            collisionOn = false;
            j.cChecker.checkTile(this);

            // Check item collision
            int itemIndex = j.cChecker.checkItem(this, true);
            pickUpItem(itemIndex);

            // CHECK NPC COLLISION
            int npcIndex = j.cChecker.checkEntidade(this, j.npc);
            interactNPC(npcIndex);

            // CHECK EVENT
            j.eHandler.checkEvent();

            if (j.keyH.enterPressed == true) {
                attacking = true;
                j.keyH.enterPressed = false;
            }
            if (attacking == true) {
                attacking();
            }
            int inimigoIndex = j.cChecker.checkEntidade(this, j.inimigo);
            interactINI(inimigoIndex);

            j.keyH.ePressed = false;

            // SE A COLISÃO ESTIVER DESLIGADA O PLAYER NÃO SE MOVE
            if (collisionOn == false && (keyH.upPressed == true || keyH.downPressed == true || keyH.leftPressed == true
                    || keyH.rightPressed == true)) {
                switch (direction) {
                    case "up":
                        worldY -= speed;
                        break;
                    case "down":
                        worldY += speed;
                        break;
                    case "left":
                        worldX -= speed;
                        break;
                    case "right":
                        worldX += speed;
                        break;
                }
            }

            spriteCounter++;

            if (spriteCounter > 10) {
                if (spriteNum == 1) {
                    spriteNum = 2;
                } else if (spriteNum == 2) {
                    spriteNum = 1;
                }
                spriteCounter = 0;
            }
        }

        if (invicible == true) {
            invicibleCounter++;
            if (invicibleCounter > 60) {
                invicible = false;
                invicibleCounter = 0;
            }
        }
    }

    public void attacking() {
        spriteCounter++;

        if (spriteCounter <= 5) {
            spriteNum = 1;
        }

        if (spriteCounter > 5 && spriteCounter <= 25) {
            spriteNum = 2;

            int currentWorldX = worldX;
            int currentWorldY = worldY;
            int solidAreaWidth = solidArea.width;
            int solidAreaHeight = solidArea.height;

            switch (direction) {
                case "up":
                    worldY -= attackArea.width;
                    break;
                case "down":
                    worldY += attackArea.width;
                    break;
                case "left":
                    worldX -= attackArea.width;
                    break;
                case "right":
                    worldX += attackArea.width;
                    break;
            }

            solidArea.width = attackArea.width;
            solidArea.height = attackArea.height;

            int inimigoIndex = j.cChecker.checkEntidade(this, j.inimigo);
            damageINI(inimigoIndex);
            worldX = currentWorldX;
            worldY = currentWorldY;
            solidArea.width = solidAreaWidth;
            solidArea.height = solidAreaHeight;
        }

        if (spriteCounter > 25) {
            spriteNum = 1;
            spriteCounter = 0;
            attacking = false;
        }
    }

    public void pickUpItem(int i) {
        if (i != 999) {
            String itemName = j.item[i].nome;

            switch (itemName) {
                case "Pe de Cabra":
                    hasPeDeCabra++;
                    if (hasPeDeCabra > 1) {
                        j.ui.showMessage("Pe de Cabra já foi Adquirido!");
                        hasPeDeCabra = 1;
                        break;
                    }
                    j.item[i] = null;
                    // audio
                    j.playSE(1);

                    j.ui.showMessage("Pe de Cabra Adquirido!");

                    break;

                case "Café":
                    if (keyH.ePressed == true) {
                        j.ui.showMessage("Bebeu café e recuperou 1 de vida!");

                        if (life < maxLife) {
                            j.item[i] = null;
                            life++;
                            j.playSE(1);
                        } else
                            j.ui.showMessage("Vida Cheia!");

                    }
                    break;
            }
        }
    }

    public void interactNPC(int i) {
        if (i != 999) {
            if (keyH.ePressed) {
                j.gameState = j.dialogueState;
                j.npc[i].speak();
                // keyH.ePressed = false;
            }
        } else {
            if (j.keyH.enterPressed == true) {
                attacking = true;
            }
        }
    }

    public void interactINI(int i) {
        if (i != 999) {
            switch (j.inimigo[i].nome) {
                case "soldado de cacetete":

                    if (invicible == false) {
                        life -= 1;
                        invicible = true;
                        j.inimigo[i].attacking = true;
                    }
                    break;
                default:
                    throw new AssertionError();
            }
        }
    }

    public void damageINI(int i) {
        if (i != 999) {
            j.inimigo[i].life -= 1;
            j.inimigo[i].invicible = true;
            if (j.inimigo[i].life <= 0) {
                j.inimigo[i] = null;
            }
        }
    }

    @Override
    public void draw(Graphics2D g2) {
        BufferedImage img = null;

        if (hasPeDeCabra >= 1 && peDeCabraEquipped == false) {
            getPlayerImage();
            peDeCabraEquipped = true;
        }
        switch (direction) {
            case "up":
                if (attacking == false) {
                    if (spriteNum == 1)
                        img = up1;
                    if (spriteNum == 2)
                        img = up2;
                } else {
                    if (spriteNum == 1)
                        img = attackUp1;
                    if (spriteNum == 2)
                        img = attackUp2;
                }

                break;
            case "down":
                if (attacking == false) {
                    if (spriteNum == 1)
                        img = down1;
                    if (spriteNum == 2)
                        img = down2;

                } else {
                    if (spriteNum == 1)
                        img = attackDown1;
                    if (spriteNum == 2)
                        img = attackDown2;
                }

                break;
            case "left":
                if (attacking == false) {
                    if (spriteNum == 1)
                        img = left1;
                    if (spriteNum == 2)
                        img = left2;

                } else {
                    if (spriteNum == 1)
                        img = attackLeft1;
                    if (spriteNum == 2)
                        img = attackLeft2;
                }
                break;
            case "right":
                if (attacking == false) {
                    if (spriteNum == 1)
                        img = right1;
                    if (spriteNum == 2)
                        img = right2;

                } else {
                    if (spriteNum == 1)
                        img = attackRight1;
                    if (spriteNum == 2)
                        img = attackRight2;
                }
                break;
        }

        if (invicible == true) {
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.5f));
        }

        g2.drawImage(img, screenX, screenY, null);
        attacking = false;

        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0f));
    }
}