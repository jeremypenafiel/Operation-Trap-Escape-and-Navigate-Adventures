/**
 * CMSC 22 - 1
 * Class that is concrete subclass of Level.java that is the second level of the game
 * 
 * @author KV. Celis
 * @author PK. Cordero
 * @author JJ. Peñafiel
 * 
 */

 package levels;

 import main.Game;
 import utilities.LoadSave;
 
 public class LevelTwo extends Level {
 
     public LevelTwo() {
        super();
        this.source = "/Level/tileMap2.txt";
        this.lvlNumber = 2;
        this.imgBackground = LoadSave.getSprite(LoadSave.LEVEL_TWO_BACKGROUND); 
        levelData = new int[Game.TILES_IN_HEIGHT][Game.TILES_IN_WIDTH];
        loadMap(); 
        loadJumpPickups("/Level/pickupsMap2.txt");
        loadTraps("/Level/trapsMap2.txt");
    }
}
 