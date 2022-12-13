package entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.geom.Rectangle2D;

import main.Game;

public class Hitbox {
	// TODO find way to remove magic numbers

	private Rectangle2D.Float hitbox;
	private float x;
	private float y;
	private int width;
	private int height;
	// MAGIC NUMBERS!
	public static float xDrawOffset = 4 * Game.SCALE;
	public static float yDrawOffset = 6 * Game.SCALE;

	public Hitbox(float x, float y, int width, int height) {
		this.x = (int) x + xDrawOffset;
		this.y = (int) y + yDrawOffset;
		// MAGIC NUMBERS!!
		this.width = (int) (width-(9*Game.SCALE));
		this.height = (int) (height-(6.5*Game.SCALE));

		hitbox = new Rectangle2D.Float(this.x, this.y, this.width, this.height);
	}

	public void drawHitbox(Graphics g) {
		g.setColor(Color.PINK);
		g.drawRect((int) this.x, (int) this.y, (int) (width), (int) (height));
	}

	public void update(float x, float y) {
		setX(x);
		setY(y);
	}

	public void setX(float x) {
		this.x = x + xDrawOffset;
	}

	public void setY(float y) {
		this.y = y + yDrawOffset;
	}

	public float getX() {
		return x;
	}

	public float getY() {
		return y;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

}
