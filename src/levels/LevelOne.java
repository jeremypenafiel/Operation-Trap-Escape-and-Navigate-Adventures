package levels;

import main.Game;

public class LevelOne extends Level {
	
	public LevelOne() {
		super();
		this.source = "/Level/tileMap.txt";
		levelData = new int[Game.TILES_IN_HEIGHT][Game.TILES_IN_WIDTH];
		loadMap();
	}

}
