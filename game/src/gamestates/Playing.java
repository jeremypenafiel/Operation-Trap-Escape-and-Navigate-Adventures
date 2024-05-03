/**
 * CMSC 22 - 1
 * Playing is the gamestate where the game loop executes
 * this is where the players play the game 
 * 
 * @author KV. Celis
 * @author PK. Cordero
 * @author JJ. Peñafiel
 * 
 */

package gamestates;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.geom.Rectangle2D;


import entities.Player;
import levels.LevelManager;
import main.Game;
import objs.ObjectManager;
import ui.PauseOverlay;

public class Playing extends State implements Statemethods {
    private Player player;
	private LevelManager levelManager;
	private ObjectManager objectManager;
    private boolean lvlCompleted = false;
    private PauseOverlay pauseOverlay; // TINA'S - FOR PAUSE SCREEN
    private boolean paused = false; // TINA'S - FOR PAUSE SCREEN
    private Font font = new Font("Impact", Font.PLAIN, 48);
 
    public Playing(Game game) {
        super(game); 
        initClasses();
    }

    private void initClasses() {
		levelManager = new LevelManager(game);
		objectManager = new ObjectManager(this);
		player = new Player(200, 200, (int) (32 * Game.SCALE), (int) (32 * Game.SCALE), this);
		player.loadLevelData(levelManager.getCurrentLevel().getLevelData());
		pauseOverlay = new PauseOverlay(this);	// TINA'S - FOR PAUSE SCREEN
	}

    /**
	 * Returns the player
	 * @return
	 */
    public Player getPlayer() {
		return player;
	}

	public void windowFocusLost() {
		player.resetDirectionBooleans();
    }

    public void resetAll() {
        lvlCompleted = false;
        player.resetPlayer(); 
        objectManager.resetAll();

    }

    @Override
    public void update() {
        
		// if player reached right side of screen
        if ((player.getHitbox().x + player.getWidth()) > Game.GAME_WIDTH - 1) {
            lvlCompleted = true;
		}
        
		// if player fell to bottom of screen
        if ((player.getHitbox().y + player.getHitbox().height) >= Game.GAME_HEIGHT - 1) {
            player.hasDied();
        } 

    	if (lvlCompleted) {
            levelManager.goNextLevel();
			player.loadLevelData(levelManager.getCurrentLevel().getLevelData());
			objectManager.loadObjects(levelManager.getCurrentLevel());
            resetAll();
            
        } else if (!paused) {
        	levelManager.update();
        	player.update();
        	
        } else {
        	pauseOverlay.update();
        }
    } 
    
    @Override
    public void draw(Graphics g) {
        levelManager.draw(g);
        player.render(g);
		objectManager.draw(g);

		g.setColor(Color.DARK_GRAY);
		g.setFont(font);
		g.drawString("" + player.getDeathCount(), 240, 135);
		g.drawString("Available Jumps: " + player.getAvailableJumps(), 1000, 50);
        
        if (paused)
        	pauseOverlay.draw(g);
    }

    public void mouseDragged(MouseEvent e) {
    	if (paused)
    		pauseOverlay.mouseDragged(e);
    }
    
    @Override
    public void mouseClicked(MouseEvent e) {
        
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (paused)
        	pauseOverlay.mousePressed(e);
        
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (paused)
        	pauseOverlay.mouseReleased(e);
        
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        if (paused)
        	pauseOverlay.mouseMoved(e);
        
    }
    
    public void unpauseGame() {
    	paused = false;
    }

    public void checkJumpPickupTouched(Rectangle2D.Float hitbox) {
		objectManager.checkObjectTouched(hitbox);
	}

	public void checkTrapTouched(Player player) {
		objectManager.checkTrapTouched(player);
	}

    @Override   
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
			case KeyEvent.VK_SPACE:
			case KeyEvent.VK_W:
				player.setIsJumping(true);
				break;

			case KeyEvent.VK_A:
				player.setMovingLeft(true);
				break;

			case KeyEvent.VK_D:
				player.setMovingRight(true);
				break;

			case KeyEvent.VK_ESCAPE:
				paused = !paused;
				break;

		}
    }

    @Override
    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()) {
			case KeyEvent.VK_SPACE:
			case KeyEvent.VK_W:
				player.setIsJumping(false);
				break;
			
            case KeyEvent.VK_A:
				player.setMovingLeft(false);
				break;
			
            case KeyEvent.VK_D:
				player.setMovingRight(false);
				break;
		}
    }

    public LevelManager getLevelManager() {
        return this.levelManager;
    }
}