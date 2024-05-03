/**
 * CMSC 22 - 1
 * Class that handles all the levels in the game
 * 
 * @author KV. Celis
 * @author PK. Cordero
 * @author JJ. Peñafiel
 * 
 */

package levels;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import gamestates.Gamestate;
import main.Game;
import utilities.LoadSave;

public class LevelManager {

	private Game game;
	private BufferedImage levelImage;
	private Level currentLevel;
	private LevelFactory levelFactory;
	private int[][] levelData;

	public LevelManager(Game game) {
		this.game = game;
		this.levelImage = LoadSave.getSprite(LoadSave.TILE_IMAGE);
        this.levelFactory = new LevelFactory();
		this.currentLevel = new LevelOne();
		this.levelData = currentLevel.getLevelData();
	}

	public void update() {

	}

	/**
	 * Draws the level terrain
	 * @param g - Graphics object
	 */
    public void draw(Graphics g) {
		currentLevel.draw(g);
        BufferedImage subImg = null;
        
		for (int row = 0; row < levelData.length; row++) {
			for (int col = 0; col < levelData[0].length; col++) {
				int tileNum = levelData[row][col];

                switch (tileNum) {
                    // For blocks that may be more than 1 tile in size
                    case 1:
    					subImg = levelImage.getSubimage(0 * 16, 0 * 16, 16, 16);
                        break;
                    case 2:
    					subImg = levelImage.getSubimage(1 * 16, 0 * 16, 16, 16);
                        break;
                    case 3:
    					subImg = levelImage.getSubimage(2 * 16, 0 * 16, 16, 16);
                        break;
                    case 4:
    					subImg = levelImage.getSubimage(0 * 16, 1 * 16, 16, 16);
                        break;
                    case 5:
    					subImg = levelImage.getSubimage(1 * 16, 1 * 16, 16, 16);
                        break;

                    // For whole blocks that is 1 tile in size
                    case 6: // leftmost block
    					subImg = levelImage.getSubimage(0 * 16, 3 * 16, 16, 16);
                        break;
                    case 7: // middle block
    					subImg = levelImage.getSubimage(1 * 16, 3 * 16, 16, 16);
                        break;
                    case 8: // rightmost block
    					subImg = levelImage.getSubimage(4 * 16, 3 * 16, 16, 16);
                        break;

                    default:
                        continue;
                }

				g.drawImage(subImg, col * Game.TILE_SIZE, row * Game.TILE_SIZE, (int) (Game.TILE_SIZE),
						(int) (Game.TILE_SIZE), null);
			}
		}

	}

	/**
	 * Returns current level
	 * @return current level
	 */
    public Level getCurrentLevel() {
		return currentLevel;
	}

	/**
	 * Sets current level
	 * @param currentLevel - Level object
	 */
    public void setCurrentLevel(Level currentLevel) {
		this.currentLevel = currentLevel;
	}

	/**
	 * Switches to the next level
	 */
	public void goNextLevel() {
        if (currentLevel.getLvlNumber() == 3) {
            Gamestate.state = Gamestate.WIN;
            restartGame();
        } else {
            this.currentLevel = levelFactory.createLevel(currentLevel.getLvlNumber() + 1);
            this.levelData = currentLevel.getLevelData();
        }

	}

    public void restartGame() {
        this.currentLevel = levelFactory.createLevel(1);
        this.levelData = currentLevel.getLevelData();
        this.game.getPlaying().getPlayer().loadLevelData(levelData);
        this.game.getPlaying().getPlayer().resetPlayer();
    }
}
