/**
 * CMSC 22 - 1
 * LoadSave is responsible for loading all graphical elements of the game 
 * 
 * @author KV. Celis
 * @author PK. Cordero
 * @author JJ. Peñafiel
 * 
 */

package utilities;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

public class LoadSave {	
	
    // Player
	public static final String PLAYER_IDLE = "Player/Idle (32x32).png";
	public static final String PLAYER_RUN = "Player/Run (32x32).png";
	public static final String PLAYER_JUMP = "Player/Jump (32x32).png";
	public static final String PLAYER_DOUBLE_JUMP = "Player/Double Jump (32x32).png";
	public static final String PLAYER_FALL = "Player/Fall (32x32).png";
	
    // Levels
    public static final String TILE_IMAGE = "Level/TileMap.png";
    public static final String LEVEL_ONE_BACKGROUND = "Level/Level 1 bg.png";
    public static final String LEVEL_TWO_BACKGROUND = "Level/Level 2 bg.png";
    public static final String LEVEL_THREE_BACKGROUND = "Level/Level 3 bg.png";

    

    // Menu
    public static final String BUTTON_START = "Menu/start_button.png";
    public static final String BUTTON_CREDITS = "Menu/credits_button.png";
    public static final String BUTTON_MENU = "Menu/menu_button.png";
    public static final String BUTTON_QUIT = "Menu/quit_button.png";
    public static final String MENU_BACKGROUND = "Menu/menu background.png";

    // Credits
    public static final String CREDITS = "Credits/CREDITS.png";

    // Win
    public static final String WIN = "Win/VICTORY.png";

    // Objects, Traps
    public static final String SPIKES = "Objects/Spikes.png";
    public static final String JUMP_PICK_UP = "Objects/arrow_button.png";
	
    // Pause Screen
    public static final String PAUSE_BACKGROUND = "Pause Screen/pause_menu.png";
    public static final String SOUND_BUTTONS = "Pause Screen/sound_button.png";
    public static final String URM_BUTTONS = "Pause Screen/urm_buttons.png";
    public static final String VOLUME_BUTTONS = "Pause Screen/volume_buttons.png";
       
	public static BufferedImage getSprite(String dir) {
		BufferedImage playerImage = null;
		String directory = "/" + dir;
		InputStream is = LoadSave.class.getResourceAsStream(directory);
		try {
			playerImage = ImageIO.read(is);
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			try {
				is.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return playerImage;
	}
}