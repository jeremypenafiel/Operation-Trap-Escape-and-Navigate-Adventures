/**
 * CMSC 22 - 1
 * Class that returns a new Level object based on given level number
 * 
 * @author KV. Celis
 * @author PK. Cordero
 * @author JJ. Peñafiel
 * 
 */

package levels;

public class LevelFactory {
	
	/**
	 * Returns a level based on level numebr
	 * @param levelNumber - integer 
	 * @return Level object indicated by level number
	 */
	public Level createLevel(int levelNumber) {
		Level level = null;
		
		switch (levelNumber) {
			case 1:
				level = new LevelOne();
				break;
			case 2:
				level = new LevelTwo();
				break;
			case 3:
				level = new LevelThree();
				break;
			default:
				break;
		}
		return level;
	}

}