/**
 * CMSC 22 - 1
 * MenuButton is the class that handles all buttons in the Menu gamestate
 * 
 * @author KV. Celis
 * @author PK. Cordero
 * @author JJ. Peñafiel
 * 
 */

package ui;

import java.awt.image.BufferedImage;
import java.awt.Graphics;
import java.awt.Rectangle;

import gamestates.Gamestate;
import utilities.LoadSave;
import static utilities.Constants.UI.MenuButtons.*;

public class MenuButton {
    private int xPos, yPos, extraWidth, index;
    private int xOffsetCenter = BT_WIDTH /2;
    private Boolean mouseOver = false, mousePressed = false;
    private Rectangle bounds;
    private BufferedImage[] imgs;
    private Gamestate state;
    private String dir;


    public MenuButton(int xPos, int yPos, int extraWidth, Gamestate state, String dir) {
        this.xPos = xPos;
        this.yPos = yPos;
        this.extraWidth = extraWidth;
        this.state = state;
        this.dir = dir;
        loadImgs();
        initBounds();
    }

    private void initBounds() {
        bounds = new Rectangle(xPos - xOffsetCenter, yPos, BT_WIDTH, BT_HEIGHT);
    }

    private void loadImgs() {
        imgs = new BufferedImage[3];
        BufferedImage temp = LoadSave.getSprite(dir);
        
        imgs[0] = temp.getSubimage(0*BT_WIDTH_DEFAULT, 0*BT_HEIGHT_DEFAULT, BT_WIDTH_DEFAULT + extraWidth, BT_HEIGHT_DEFAULT);
        imgs[1] = temp.getSubimage(0*BT_WIDTH_DEFAULT, 1*BT_HEIGHT_DEFAULT, BT_WIDTH_DEFAULT + extraWidth, BT_HEIGHT_DEFAULT);
        imgs[2] = temp.getSubimage(0*BT_WIDTH_DEFAULT, 2*BT_HEIGHT_DEFAULT, BT_WIDTH_DEFAULT + extraWidth, BT_HEIGHT_DEFAULT);
    }

    public void draw(Graphics g) {
        g.drawImage(imgs[index], xPos - xOffsetCenter, yPos, BT_WIDTH, BT_HEIGHT, null);
    }

    public void update() {
        index = 0;
        if (mouseOver) {
            index = 1;
        }

        if (mousePressed) {
            index = 2;
        }
    }

    public boolean isMouseOver() {
		return mouseOver;
	}

	public void setMouseOver(boolean mouseOver) {
		this.mouseOver = mouseOver;
	}

	public boolean isMousePressed() {
		return mousePressed;
	}

	public void setMousePressed(boolean mousePressed) {
		this.mousePressed = mousePressed;
	}

	public Rectangle getBounds() {
		return bounds;
	}

	public void applyGamestate() {
		Gamestate.state = state;
	}

	public void resetBools() {
		mouseOver = false;
		mousePressed = false;
	}

}