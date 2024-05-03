/**
 * CMSC 22 - 1
 * Object Manager class that handles the objects in a level
 * 
 * @author KV. Celis
 * @author PK. Cordero
 * @author JJ. Peñafiel
 * 
 */

package objs;

import java.awt.Graphics;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import entities.Player;
import gamestates.Playing;
import levels.Level;
import main.Game;
import utilities.LoadSave;

public class ObjectManager {
	private Playing playing;
	private ArrayList<JumpPickup> jumpPickups;
	private ArrayList<Trap> traps;
	private BufferedImage trapImage;
	private BufferedImage jumpPickupImage;

	public ObjectManager(Playing playing) {
		this.playing = playing;
		loadObjects(playing.getLevelManager().getCurrentLevel());
		loadImages();

	}

	private void loadImages() {
		jumpPickupImage = LoadSave.getSprite(LoadSave.JUMP_PICK_UP);
		trapImage = LoadSave.getSprite(LoadSave.SPIKES);

	}

	public void draw(Graphics g) {
		if (jumpPickups != null) {
			drawPickups(g);
		}
		if (traps != null) {
			drawTraps(g);
		}
	}

	private void drawTraps(Graphics g) {
		for (Trap trap : traps) {
			BufferedImage subImg = trapImage.getSubimage(6*32, 0, 32, 32);
			g.drawImage(subImg, trap.x, trap.y, (int) Game.TILE_SIZE, (int) Game.TILE_SIZE, null);
		}
	}

	private void drawPickups(Graphics g) {
		for (JumpPickup jumpPickup : jumpPickups) {
			if (jumpPickup.isActive()) {
				BufferedImage subImg = jumpPickupImage.getSubimage(0, 0, 32, 32);
				g.drawImage(subImg, jumpPickup.x, jumpPickup.y, (int) (32 * 2), (int) (32 * 2), null);
			}
		}
	}

	/**
	 * 
	 * @param hitbox
	 */
	public void checkObjectTouched(Rectangle2D.Float hitbox) {
		for (JumpPickup jumpPickup : jumpPickups) {
			if (jumpPickup.isActive()) {
				if (hitbox.intersects(jumpPickup.getHitbox())) {
					jumpPickup.setActive(false);
					addJumpToPlayer();
				}
			}
		}

	}
 
	private void addJumpToPlayer() {
		playing.getPlayer().addJumps();
	}

	public void checkTrapTouched(Player player) {
		for (Trap trap : traps) {
			if (player.getHitbox().intersects(trap.getHitbox())) {
				player.hasDied();
			}
		}
	}

	public void resetAll() {
		loadObjects(playing.getLevelManager().getCurrentLevel());
		for (JumpPickup jumpPickup : jumpPickups) {
			jumpPickup.reset();
		}
	}

	public void loadObjects(Level newLevel) {
		jumpPickups = new ArrayList<>(newLevel.getJumpPickups());
		traps = new ArrayList<>(newLevel.getTraps());
	}
}
