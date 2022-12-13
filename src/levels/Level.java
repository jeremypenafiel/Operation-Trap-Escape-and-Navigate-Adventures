package levels;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import main.Game;

public abstract class Level {

	protected int[][] levelData;
	protected String source;

	public Level() {
		/*
		 * this.source = source; levelData = new
		 * int[Game.TILES_IN_HEIGHT][Game.TILES_IN_WIDTH]; loadMap();
		 */
	}

	/**
	 * Loads the map data from .txt file
	 */
	public void loadMap() {
		try {
			InputStream is = getClass().getResourceAsStream(source);
			BufferedReader br = new BufferedReader(new InputStreamReader(is));

			for (int row = 0; row < Game.TILES_IN_HEIGHT; row++) {
				String line = br.readLine();
				String numbers[] = line.split(" ");
				for (int col = 0; col < Game.TILES_IN_WIDTH; col++) {
					int num = Integer.parseInt(numbers[col]);
					levelData[row][col] = num;
				}
			}

			br.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Returns the 2d integer array containing map data
	 * 
	 * @return levelOneData - a 2D integer array
	 */
	public int[][] getLevelData() {
		return levelData;
	}

	/**
	 * For testing purposes only
	 */
	public void printMap() {
		for (int row = 0; row < levelData.length; row++) {
			for (int col = 0; col < levelData[0].length; col++) {
				System.out.print(levelData[row][col] + ",");
				if (col == (Game.TILES_IN_WIDTH - 1)) {
					System.out.println("");
				}
			}
		}
	}

	/*
	 * For testing purposes only
	 */
	public static void main(String[] args) {
		var map = new LevelOne();
		map.loadMap();
		map.printMap();
	}

}
