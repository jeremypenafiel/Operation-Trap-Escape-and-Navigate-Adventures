package utilities;

import java.awt.geom.Rectangle2D;

import entities.Hitbox;
import main.Game;

public class HelperMethods {
	
	/**
	 * Returns true if object can move to specific coordinate, false otherwise
	 * @param x - float value of the x-coordinate of destination
	 * @param y - float value of the y-coordinate of destination
	 * @param width - integer width of the object
	 * @param height - integer height of object 
	 * @param levelData - 2D integer array of the level data
	 * @return - true if object can move to given coordinate, false otherwise
	 */
	public static boolean canMoveHere(float x, float y, float width, float height, int[][] levelData) {
		
		if (isSolid(x, y, levelData)) { // top left corner of hitbox
			return false;
		}
		if(isSolid(x, y + height, levelData)) {  // bottom left corner of hitbox
			return false;
		}
		
		if (isSolid(x + width, y, levelData)) {  // top right right corner of hitbox
			return false;
		}
		
		if (isSolid(x + width, y + height, levelData)) {  // bottom right corner of hitbox
			return false;
		}
		
		return true;
	}
	
	
	/**
	 * Returns true if object at x, y coordinate is solid, false otherwise
	 * @param x - float value of the x-coordinate of object
	 * @param y - float value of the y-coordinate of object
	 * @param levelData - 2D integer array of the level data 
	 * @return -  true if object at coordinate is solid, false otherwise
	 */
	private static boolean isSolid(float x, float y, int[][] levelData) {
		if (x < 0 || x >= Game.GAME_WIDTH) {
			return true;
		}
		if (y < 0 || y >= Game.GAME_HEIGHT) {
			return true;
		}	
		float xIndex = x / Game.TILE_SIZE;
		float yIndex = y / Game.TILE_SIZE;

		int value = levelData[(int) yIndex][(int) xIndex];  // value is the position of player within game window

		if (value != 0) {
			return true;
		}

		return false;
	}
	
	public static float GetEntityYPosUnderRoofOrAboveFloor(Rectangle2D.Float hitbox, float airSpeed) {
		int currentTile = (int) (hitbox.y/Game.TILE_SIZE);
		
		if (airSpeed > 0) {
			// cfalling / touching floor
			int tileYPos = currentTile * Game.TILE_SIZE;
			int yOffset = (int) ((Game.TILE_SIZE*2) - hitbox.height);
			return tileYPos + yOffset - 1;
			
		}else {
			//collision to left
			return currentTile * Game.TILE_SIZE;
		}
	}
	public static float GetEntityXPosNextToWall(Rectangle2D.Float hitbox, float xSpeed) {
int currentTile = (int) (hitbox.x/Game.TILE_SIZE);
		
		if (xSpeed > 0) {
			// collision to the right
			int tileXPos = currentTile * Game.TILE_SIZE;
			int xOffset = (int) ((Game.TILE_SIZE*2) - hitbox.width);
			return tileXPos + xOffset - 1;
			
		}else {
			//collision to left
			return currentTile * Game.TILE_SIZE;
		}
	}

}
