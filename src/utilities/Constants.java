package utilities;

public class Constants {
	
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
