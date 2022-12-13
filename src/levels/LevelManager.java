package levels;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import main.Game;
import utilities.LoadSave;

public class LevelManager {

	private Game game;
	private BufferedImage levelImage;
	private Level currentLevel;
	private Level levelOne = new LevelOne();
	private int[][] levelData;

	public LevelManager(Game game) {
		this.game = game;
		this.levelImage = LoadSave.getPlayerAtlas(LoadSave.LEVEL_IMAGE);
		this.currentLevel = levelOne;
		this.levelData = currentLevel.getLevelData();
	}

	public void update() {

	}

	public void draw(Graphics g) {
		BufferedImage subImg = null;
		for (int row = 0; row < levelData.length; row++) {
			for (int col = 0; col < levelData[0].length; col++) {
				int tileNum = levelData[row][col];
				if (tileNum == 0) {
					continue;
				}
				if (tileNum == 1) {
					subImg = levelImage.getSubimage(0 * 16, 0 * 16, 16, 16);
				} else if (tileNum == 2) {
					subImg = levelImage.getSubimage(1 * 16, 0 * 16, 16, 16);
				} else if (tileNum == 3) {
					subImg = levelImage.getSubimage(2 * 16, 0 * 16, 16, 16);
				}
				g.drawImage(subImg, col * Game.TILE_SIZE, row * Game.TILE_SIZE, (int) (Game.TILE_SIZE),
						(int) (Game.TILE_SIZE), null);

			}
		}

		/*
		 * System.out.println(levelData[levelData.length-1][levelData[0].length-1]);
		 * BufferedImage subImg = levelImage.getSubimage(2*16, 0*16, 16, 16);
		 * g.drawImage(subImg, (int)((25)*Game.TILE_SIZE), (int) ((13)*Game.TILE_SIZE),
		 * (int)(32*Game.SCALE), (int) (32*Game.SCALE), null);
		 */

	}

	public Level getCurrentLevel() {
		return currentLevel;
	}
	
	public void setCurrentLevel(Level currentLevel) {
		this.currentLevel = currentLevel;
	}

}
