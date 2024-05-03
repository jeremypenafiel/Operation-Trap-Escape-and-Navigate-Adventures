/**
 * CMSC 22 - 1
 * Class that is concrete subclass of Level.java that is the first level of the game
 * 
 * @author KV. Celis
 * @author PK. Cordero
 * @author JJ. Peñafiel
 * 
 */


package levels;

import main.Game;
import utilities.LoadSave;

public class LevelOne extends Level {
	
	public LevelOne() {
		super();
		this.source = "/Level/tileMap1.txt"; 
        this.lvlNumber = 1;
        this.imgBackground = LoadSave.getSprite(LoadSave.LEVEL_ONE_BACKGROUND); 
		levelData = new int[Game.TILES_IN_HEIGHT][Game.TILES_IN_WIDTH];
		loadMap();
        loadJumpPickups("/Level/pickupsMap1.txt");
		loadTraps("/Level/trapsMap1.txt");
	}

}