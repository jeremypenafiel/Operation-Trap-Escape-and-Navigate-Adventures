/**
 * CMSC 22 - 1
 * Class that is all objects of the game inherits from
 * 
 * @author KV. Celis
 * @author PK. Cordero
 * @author JJ. Peñafiel
 * 
 */
package objs;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.geom.Rectangle2D;

import main.Game;

public class GameObject {

	protected int x;
	protected int y;
	protected int objectType;
	protected Rectangle2D.Float hitbox;
	protected int xDrawOffset;
	protected int yDrawOffset;
	protected boolean active = true;

	public GameObject(int x, int y, int objectType) {
		this.x = x;
		this.y = y;
		this.objectType = objectType;
	}

	/**
	 * Initializes the hitbox
	 * 
	 * @param width
	 * @param height
	 */
	protected void initHitbox(int width, int height) {
		hitbox = new Rectangle2D.Float(x, y, (int) (width), (int) (height));
	}
	
	
	/**
	 * Initializes hitbox with y offset
	 * @param width
	 * @param height
	 * @param yOffset
	 */
	protected void initHitbox(int width, int height, int yOffset) {
		hitbox = new Rectangle2D.Float(x, y + yOffset, (int) (width), (int) (height));
	}

	/**
	 * Draws the hitbox
	 * 
	 * @param g           - Graphics object
	 * @param levelOffset
	 */
	protected void drawHitbox(Graphics g, int xLevelOffset) {

		g.setColor(Color.PINK);
		g.drawRect((int) hitbox.x - xLevelOffset, (int) hitbox.y, (int) hitbox.width, (int) hitbox.height);
	}

	protected boolean isActive() {
		return active;
	}

	protected void setActive(boolean active) {
		this.active = active;
	}

	protected Rectangle2D.Float getHitbox() {
		return this.hitbox;
	}

	protected void reset() {
		active = true;
	}
}
