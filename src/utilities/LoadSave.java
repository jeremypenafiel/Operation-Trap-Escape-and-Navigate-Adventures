package utilities;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

public class LoadSave {	
	
	public static final String NINJA_FROG_FOLDER_NAME = "Ninja Frog";
	public static final String LEVEL_IMAGE = "Level/TileMap.png";
	public static final String PLAYER_IDLE = "Idle (32x32).png";
	public static final String PLAYER_RUN = "Run (32x32).png";
	public static final String PLAYER_JUMP = "Jump (32x32).png";
	public static final String PLAYER_DOUBLE_JUMP = "Double Jump (32x32).png";
	public static final String PLAYER_FALL= "Fall (32x32).png";

	public static BufferedImage getPlayerAtlas(String folderName, String imageFileName) {
		BufferedImage playerImage = null;
		String directory = "/" + folderName + "/" + imageFileName;
		InputStream is = LoadSave.class.getResourceAsStream(directory);
		try {
			playerImage = ImageIO.read(is);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				is.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return playerImage;

	}
	
	public static BufferedImage getPlayerAtlas(String dir) {
		BufferedImage playerImage = null;
		String directory = "/" + dir;
		InputStream is = LoadSave.class.getResourceAsStream(directory);
		try {
			playerImage = ImageIO.read(is);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				is.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return playerImage;

	}

}
