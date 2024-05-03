/**
 * CMSC 22 - 1
 * Credits is a gamestate where the group who made this java project can be shown
 * 
 * @author KV. Celis
 * @author PK. Cordero
 * @author JJ. Peñafiel
 * 
 */

package gamestates;

import java.awt.image.BufferedImage;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import static utilities.Constants.Background.*;

import main.Game;
import utilities.LoadSave;

public class Credits extends State implements Statemethods {
    private BufferedImage imgCredits;
    private final int xOffsetCenter = BACKGROUND_WIDTH/2;
    private final int yOffsetCenter = BACKGROUND_HEIGHT/2;
    
    public Credits(Game game) { 
        super(game); 
        loadGraphics();
    }

    private void loadGraphics() {
        this.imgCredits = LoadSave.getSprite(LoadSave.CREDITS);
    }

    @Override
    public void update() {
        
    }

    @Override
    public void draw(Graphics g) {

        // MAGIC NUMBERS !!!
        g.drawImage(imgCredits, (Game.GAME_WIDTH/2) - xOffsetCenter, (Game.GAME_HEIGHT/2) - yOffsetCenter, 
            BACKGROUND_WIDTH, BACKGROUND_HEIGHT, null);
        
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        
    }

    @Override
    public void mousePressed(MouseEvent e) {
        
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        
    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_BACK_SPACE) {
            Gamestate.state = Gamestate.MENU;

        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        
    }
    
}
