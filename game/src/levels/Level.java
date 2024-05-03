/**
 * CMSC 22 - 1
 * Abstract class that all levels in game inherits from
 * 
 * @author KV. Celis
 * @author PK. Cordero
 * @author JJ. Peñafiel
 * 
 */

 package levels;

 import java.io.BufferedReader;
 import java.io.InputStream;
 import java.io.InputStreamReader;
 import java.util.ArrayList;
 
 
import java.awt.image.BufferedImage;
import java.awt.Graphics;
import static utilities.Constants.Background.*;

import main.Game;
import objs.JumpPickup;
import objs.Trap;

public abstract class Level {

	protected int[][] levelData;
	protected String source;
    protected int lvlNumber;

    protected ArrayList<JumpPickup> jumpPickups;
	protected ArrayList<Trap> traps;
    
    protected BufferedImage imgBackground;

    private final int xOffsetCenter = BACKGROUND_WIDTH/2;
    private final int yOffsetCenter = BACKGROUND_HEIGHT/2;

	public Level() {
		this.jumpPickups = new ArrayList<>();
		this.traps = new ArrayList<>();
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

	public void loadJumpPickups(String source) {
		try {
			InputStream is = getClass().getResourceAsStream(source);
			BufferedReader br = new BufferedReader(new InputStreamReader(is));

			for (int row = 0; row < Game.TILES_IN_HEIGHT; row++) {
				String line = br.readLine();
				String numbers[] = line.split(" ");
				for (int col = 0; col < Game.TILES_IN_WIDTH; col++) {
					int num = Integer.parseInt(numbers[col]);
					if (num == 1) {
						jumpPickups.add(new JumpPickup(col * Game.TILE_SIZE, row * Game.TILE_SIZE, 0));
					}
				}
			}
			br.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void loadTraps(String source) {
		try {
			InputStream is = getClass().getResourceAsStream(source);
			BufferedReader br = new BufferedReader(new InputStreamReader(is));

			for (int row = 0; row < Game.TILES_IN_HEIGHT; row++) {
				String line = br.readLine();
				String numbers[] = line.split(" ");
				for (int col = 0; col < Game.TILES_IN_WIDTH; col++) {
					int num = Integer.parseInt(numbers[col]);
					if (num == 1) {
						traps.add(new Trap(((col * Game.TILE_SIZE)), ((row * Game.TILE_SIZE)), 1));
					}
				}
			}
			br.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

    public void draw(Graphics g) {
        g.drawImage(imgBackground, (Game.GAME_WIDTH/2) - xOffsetCenter, (Game.GAME_HEIGHT/2) - yOffsetCenter, 
            BACKGROUND_WIDTH, BACKGROUND_HEIGHT, null);
    }

	/**
	 * Returns the 2d integer array containing map data
	 * 
	 * @return levelOneData - a 2D integer array
	 */
	public int[][] getLevelData() {
		return levelData;
	}

    public int getLvlNumber() {
        return this.lvlNumber;
    }

    public ArrayList<JumpPickup> getJumpPickups() {
		return jumpPickups;
	}

	public ArrayList<Trap> getTraps() {
		return traps;
	}
}
