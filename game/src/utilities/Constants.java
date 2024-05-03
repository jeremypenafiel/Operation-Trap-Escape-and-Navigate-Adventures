/**
 * CMSC 22 - 1
 * Constants handle all constants that are found within the game
 * this is to avoid magic numbers wherever 
 * 
 * @author KV. Celis
 * @author PK. Cordero
 * @author JJ. Peñafiel
 * 
 */

package utilities;

import main.Game;

public class Constants {
	
    public static class ObjectConstants {
        public static final int JUMP_PICKUP = 0;
        public static final int SPIKES = 1;

        public static final int JUMP_PICKUP_WIDTH_DEFAULT = 32;
        public static final int JUMP_PICKUP_HEIGHT_DEFAULT = 16;
        public static final int JUMP_PICKUP_WIDTH = (int) (JUMP_PICKUP_WIDTH_DEFAULT * Game.SCALE);
        public static final int JUMP_PICKUP_HEIGHT = (int) (JUMP_PICKUP_HEIGHT_DEFAULT * Game.SCALE);
        
        public static final int SPIKES_WIDTH_DEFAULT = 32;
        public static final int SPIKES_HEIGHT_DEFAULT = 16;
        public static final int SPIKES_WIDTH = (int) (SPIKES_WIDTH_DEFAULT * Game.SCALE);
        public static final int SPIKES_HEIGHT = (int) (SPIKES_HEIGHT_DEFAULT * Game.SCALE);
     
        public static int GetSpriteAmount(int objType) {
            switch(objType) {
                case JUMP_PICKUP:
                    return 0; //temp
                case SPIKES:
                    return 0; // temp
                default:
                    return 0;
            }
        }
    }

    public static class UI {
        public static class MenuButtons{
            public static final int BT_WIDTH_DEFAULT = 64; 
            public static final int BT_HEIGHT_DEFAULT = 32;

            public static final int BT_WIDTH = (int) (BT_WIDTH_DEFAULT * Game.SCALE);
            public static final int BT_HEIGHT = (int) (BT_HEIGHT_DEFAULT * Game.SCALE);
        }

        public static class PauseButtons {
        	public static final int SOUND_SIZE_DEFAULT = 42;
        	public static final int SOUND_SIZE = (int) (SOUND_SIZE_DEFAULT);
        }
        
        public static class URMButtons {
        	public static final int URM_DEFAULT_SIZE = 56;
        	public static final int URM_SIZE = (int) (URM_DEFAULT_SIZE);
        }
        
        public static class VolumeButtons {
        	public static final int VOLUME_DEFAULT_WIDTH = 28;  
        	public static final int VOLUME_DEFAULT_HEIGHT = 44;
        	public static final int SLIDER_DEFAULT_WIDTH = 215;
        	
        	
        	public static final int VOLUME_WIDTH = (int) (VOLUME_DEFAULT_WIDTH);
        	public static final int VOLUME_HEIGHT = (int) (VOLUME_DEFAULT_HEIGHT);
        	public static final int SLIDER_WIDTH = (int) (SLIDER_DEFAULT_WIDTH);
        }
    }

    public static class Background {
        public static final int BACKGROUND_WIDTH_DEFAULT = 288;
        public static final int BACKGROUND_HEIGHT_DEFAULT = 144;

        // MAGIC NUMBERS!!!
        public static final int BACKGROUND_WIDTH = Game.GAME_WIDTH;
        public static final int BACKGROUND_HEIGHT = Game.GAME_HEIGHT;
    
    }

	public static class Directions	{
		public static final int UP = 0;
		public static final int RIGHT = 1;
		public static final int LEFT = 2;
		public static final int DOWN = 3;
	}
	
	public static class PlayerConstants	{
	
        public static final int IDLE = 0;
        public static final int RUN = 1;
        public static final int JUMP = 2;
        public static final int DOUBLE_JUMP = 3;
    	public static final int FALL = 4;

	
		public static int GetNumberOfImages(int playerAction) {
	
			switch (playerAction) {
				case IDLE:
					return 11;
				case RUN:
					return 12;
				case JUMP:
				case FALL:
					return 1;
				case DOUBLE_JUMP:
					return 6;
		
				default:
					return 1;
			}
		}
	}

}
