/**
 * CMSC 22 - 1
 * HelperMethods are the methods that calculate movement and hitbox collision Physics 
 * 
 * @author KV. Celis
 * @author PK. Cordero
 * @author JJ. Peñafiel
 * 
 */

package utilities;

import java.awt.geom.Rectangle2D;

import main.Game;

public class HelperMethods {

	/**
	 * Returns true if object can move to specific coordinate, false otherwise
	 * 
	 * @param x         - float value of the x-coordinate of destination
	 * @param y         - float value of the y-coordinate of destination
	 * @param width     - integer width of the object
	 * @param height    - integer height of object
	 * @param levelData - 2D integer array of the level data
	 * @return - true if object can move to given coordinate, false otherwise
	 */
	public static boolean canMoveHere(float x, float y, float width, float height, int[][] levelData) {

		if (isSolid(x, y, levelData)) { // top left corner of hitbox
			return false;
		}
		if (isSolid(x, y + height, levelData)) { // bottom left corner of hitbox
			return false;
		}

		if (isSolid(x + width, y, levelData)) { // top right right corner of hitbox
			return false;
		}

		if (isSolid(x + width, y + height, levelData)) { // bottom right corner of hitbox
			return false;
		}

		if (isSolid(x, y + (height / 2), levelData)) { // midpoint between two left corners
			return false;
		}
		if (isSolid(x + width, y + (height / 2), levelData)) {  // midpoint between two right corners
			return false;
		}

		return true;
	}

	/**
	 * Returns true if object at x, y coordinate is solid, false otherwise
	 * 
	 * @param x         - float value of the x-coordinate of object
	 * @param y         - float value of the y-coordinate of object
	 * @param levelData - 2D integer array of the level data
	 * @return - true if object at coordinate is solid, false otherwise
	 */
	public static boolean isSolid(float x, float y, int[][] levelData) {
		if (x < 0 || x >= Game.GAME_WIDTH) {
			return true;
		}
		if (y < 0 || y >= Game.GAME_HEIGHT) {
			return true;
		}
		float xIndex = x / Game.TILE_SIZE;
		float yIndex = y / Game.TILE_SIZE;

		int value = levelData[(int) yIndex][(int) xIndex]; // value is the position of player within game window

		if (value != 0) {
			return true;
		}

		return false;
	}

	public static float getEntityYPosUnderRoofOrAboveFloor(Rectangle2D.Float hitbox, float airSpeed) {
		int currentTile = (int) (hitbox.y / Game.TILE_SIZE);

		if (airSpeed > 0) {
			// cfalling / touching floor
			int tileYPos = currentTile * Game.TILE_SIZE;
			int yOffset = (int) ((Game.TILE_SIZE * 2) - hitbox.height);
			return tileYPos + yOffset - 1;

		} else {
			// collision to left
			return currentTile * Game.TILE_SIZE;
		}
	}

	public static float getEntityXPosNextToWall(Rectangle2D.Float hitbox, float xSpeed) {
		int currentTile = (int) (hitbox.x / Game.TILE_SIZE);

		if (xSpeed > 0) {
			// collision to the right
			int tileXPos = currentTile * Game.TILE_SIZE;
			int xOffset = (int) ((Game.TILE_SIZE * 2) - hitbox.width);
			return tileXPos + xOffset - 1;

		} else {
			// collision to left
			return currentTile * Game.TILE_SIZE;
		}
	}
	
	/**
	 * Checks if hitbox is on the floor
     * 
	 * @param hitbox - Rectangle2D.Float
	 * @param lvlData - 2D integer array
	 * @return - true if hitbox is on the floor, false otherwise
	 */
	public static boolean isEntityOnFloor(Rectangle2D.Float hitbox, int[][] lvlData) {
		// checks below the bottom left corner of hitbox if solid
		if (isSolid(hitbox.x, hitbox.y + hitbox.height + 1, lvlData)) {
			return true;
		}
		
		// checks below the bottom right corner of hitbox if solid
		if (isSolid(hitbox.x + hitbox.width, hitbox.y + hitbox.height + 1, lvlData)) {
			return true;
		}

		return false;
	}

}
