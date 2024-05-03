/**
 * CMSC 22 - 1
 * Class that is concrete subclass of Level.java that is the third level of the game
 * 
 * @author KV. Celis
 * @author PK. Cordero
 * @author JJ. Peñafiel
 * 
 */

package levels;

import main.Game;
import utilities.LoadSave;

public class LevelThree extends Level {

	public LevelThree() {
		super();
		this.source = "/Level/tileMap3.txt";
		this.lvlNumber = 3;
        this.imgBackground = LoadSave.getSprite(LoadSave.LEVEL_THREE_BACKGROUND); 
		levelData = new int[Game.TILES_IN_HEIGHT][Game.TILES_IN_WIDTH];
		loadMap(); 
        loadJumpPickups("/Level/pickupsMap3.txt");
		loadTraps("/Level/trapsMap3.txt");

	}

}
