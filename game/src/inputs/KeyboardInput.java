/**
 * CMSC 22 - 1
 * Class that accepts user keyboard inputs
 * 
 * @author KV. Celis
 * @author PK. Cordero
 * @author JJ. Peñafiel
 * 
 */

package inputs;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import gamestates.Gamestate;
import main.GamePanel;

/**
 * @author Jeremy
 *
 */
public class KeyboardInput implements KeyListener {
	
	private GamePanel gamePanel;
	
	public KeyboardInput(GamePanel gamePanel) {
		this.gamePanel = gamePanel;
	}

	@Override
	public void keyTyped(KeyEvent e) {
		
	}
	
	/**
	 * Changes player movement based on user key input
	 */
	@Override
	public void keyPressed(KeyEvent e) {
        switch(Gamestate.state) {
            case MENU:
                gamePanel.getGame().getMenu().keyPressed(e);
                break;

            case PLAYING:
                gamePanel.getGame().getPlaying().keyPressed(e);
                break;

            case CREDITS:
                gamePanel.getGame().getCredits().keyPressed(e);
                break;

            case WIN:
                gamePanel.getGame().getWin().keyPressed(e);
                break;

            default:
                break;
        }	
    }

	@Override
	public void keyReleased(KeyEvent e) {
        switch(Gamestate.state) {
            case MENU:
                gamePanel.getGame().getMenu().keyReleased(e);
                break;

            case PLAYING:
                gamePanel.getGame().getPlaying().keyReleased(e);
                break;
            
            default:
                break;
        }
	}
}