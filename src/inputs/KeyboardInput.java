/**
 * 
 * @author Jeremy Jobert Peñafiel
 *
 */

package inputs;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

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
		// TODO Auto-generated method stub
		
	}
	
	/**
	 * Changes player movement based on user key input
	 */
	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		switch (e.getKeyCode()) {
			case KeyEvent.VK_SPACE:
			case KeyEvent.VK_W:
				//this.gamePanel.getGame().getPlayer().setMovingUp(true);
				this.gamePanel.getGame().getPlayer().setIsJumping(true);
				break;
			case KeyEvent.VK_A:
				this.gamePanel.getGame().getPlayer().setMovingLeft(true);
				break;
			case KeyEvent.VK_S:
				this.gamePanel.getGame().getPlayer().setMovingDown(true);
				break;
			case KeyEvent.VK_D:
				this.gamePanel.getGame().getPlayer().setMovingRight(true);
				break;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		switch (e.getKeyCode()) {
			case KeyEvent.VK_SPACE:
			case KeyEvent.VK_W:
				//gamePanel.getGame().getPlayer().setMovingUp(false);
				gamePanel.getGame().getPlayer().setIsJumping(false);
				break;
			case KeyEvent.VK_A:
				gamePanel.getGame().getPlayer().setMovingLeft(false);
				break;
			case KeyEvent.VK_S:
				gamePanel.getGame().getPlayer().setMovingDown(false);
				break;
			case KeyEvent.VK_D:
				gamePanel.getGame().getPlayer().setMovingRight(false);
				break;
		}
	}
}
