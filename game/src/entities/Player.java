/**
 * CMSC 22 - 1
 * Player Class which is the character that user controls
 * 
 * @author KV. Celis
 * @author PK. Cordero
 * @author JJ. Peñafiel
 * 
 */

package entities;

import static utilities.Constants.PlayerConstants.DOUBLE_JUMP;
import static utilities.Constants.PlayerConstants.GetNumberOfImages;
import static utilities.Constants.PlayerConstants.IDLE;
import static utilities.Constants.PlayerConstants.JUMP;
import static utilities.Constants.PlayerConstants.RUN;
import static utilities.Constants.PlayerConstants.FALL;
import static utilities.HelperMethods.*;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import gamestates.Playing;
import main.Game;
import utilities.LoadSave;
import sounds.AudioPlayer;

public class Player extends Entity {

	private BufferedImage[] playerAnimations = new BufferedImage[5]; // array of all animations of the player
	private int playerAction = IDLE;// sprite images of player
	private int animationTicker = 0; // keeps count of how many frames have
	private int animationIndex = 0;
	private int animationSpeedFrames = 15; // number of frames that passes until a new animation image is shown
	private float playerSpeed = 1.0f * Game.SCALE; // changed from 2.0f (in video) to 1.0f * Game.SCALE bc different
													// scales resulted to different player speeds (bigger scales slower,
													// smaller scales faster)
	private boolean isPlayerMoving = false;
	private boolean isMovingLeft;
	private boolean isMovingRight;
	private boolean isJumping;
    private float xDrawOffset = 4 * Game.SCALE;
	private float yDrawOffset = 6 * Game.SCALE;
	private int[][] lvlData;
    private int availableJumps = 2;

    private AudioPlayer audioPlayer;
    private Playing playing;
	
    private int deathCount = 0;

	// Jumping / Gravity
	private float airSpeed = 0f;
	private float gravity = 0.04f * Game.SCALE;
	private float jumpSpeed = -2.25f * Game.SCALE;
	private float fallSpeedAfterCollision = 0.5f * Game.SCALE;
	private boolean inAir = false;

	public Player(float x, float y, int width, int height, Playing playing) {
		super(x, y, width, height);
		this.playing = playing;
		setImages();
		initHitbox(x, y, (int) (width - (9 * Game.SCALE)), (int) (height - (6.5 * Game.SCALE)));
        audioPlayer = new AudioPlayer();
        audioPlayer.toggleSongMute();
	}
    
	public void update() {
        updatePos();

        if (isPlayerMoving) {
			checkJumpPickupTouched();
			checkSpikesTouched();

		}
		updateAnimationTicker();
		setPlayerAction();
    }

        private void checkJumpPickupTouched() {
            playing.checkJumpPickupTouched(this.hitbox);
    
        }
    
        private void checkSpikesTouched() {
            playing.checkTrapTouched(this);
    
        }

	public void render(Graphics g) {

		BufferedImage subImg = playerAnimations[playerAction].getSubimage(animationIndex * 32, 0, 32, 32);
		g.drawImage(subImg, (int) (hitbox.x - xDrawOffset), (int) (hitbox.y - yDrawOffset), (int) (width),
				(int) (height), null);
	}
	
	public void loadLevelData(int[][] lvlData) {
        this.lvlData = lvlData;
		if (!isEntityOnFloor(hitbox, lvlData)) {
			inAir = true;
		}
	}

	/**
	 * Updates position of player
	 */
	public void updatePos() {
		isPlayerMoving = false;

		if (isJumping & availableJumps > 0) {
			jump();
		}

		if (!isMovingLeft && !isMovingRight && !inAir) {
			return;
		}

		float xSpeed = 0;

		if (isMovingLeft) {
			xSpeed -= playerSpeed;
		}

        if (isMovingRight) {
			xSpeed += playerSpeed;
		}
		
		if (!inAir && !isEntityOnFloor(hitbox, lvlData)) {
			inAir = true;
		}

		if (inAir) {

            if (canMoveHere(hitbox.x, hitbox.y + airSpeed, hitbox.width, hitbox.height, lvlData)) {
				hitbox.y += airSpeed;
				airSpeed += gravity;
				updateXPos(xSpeed);

			} else {
				hitbox.y = getEntityYPosUnderRoofOrAboveFloor(hitbox, airSpeed);
				if (airSpeed > 0) {
					resetInAir();
				} else {
					airSpeed = fallSpeedAfterCollision;
				}
				updateXPos(xSpeed);
			}

		} else {
			updateXPos(xSpeed);
		}

		isPlayerMoving = true;
	}

	/**
	 * Makes player jump
	 */
    private void jump() {

		if (inAir) {
			return;
		}
        audioPlayer.playEffect(AudioPlayer.JUMP);
		inAir = true;
		airSpeed = jumpSpeed;
		availableJumps--;
	}

	private void resetInAir() {
		inAir = false;
		airSpeed = 0;
	}

	/**
	 * Updates horizontal position of player
	 * @param xSpeed - float
	 */
    private void updateXPos(float xSpeed) {

		if (canMoveHere(hitbox.x + xSpeed, hitbox.y, hitbox.width, hitbox.height, lvlData)) {
			hitbox.x += xSpeed;
		} else {
			hitbox.x = getEntityXPosNextToWall(hitbox, xSpeed);
		}

	}

	/**
	 * updates the animation index
	 * 
	 * @param numberOfAnimationImages - number of images a certain animation ahs
	 */
	public void updateAnimationTicker() {
		animationTicker++;
		if (animationTicker >= animationSpeedFrames) {
			animationTicker = 0;
			animationIndex++;
			if (animationIndex >= GetNumberOfImages(playerAction)) {
				animationIndex = 0;
			}
		}
	}

	/**
	 * Sets the player action
	 */
	public void setPlayerAction() {
		int startPlayerAction = playerAction;

		if (isPlayerMoving) {
			playerAction = RUN;
		} else {
			playerAction = IDLE;
		}
		if (inAir) {
			if (airSpeed < 0) {
				playerAction = JUMP;
			} else {
				playerAction = FALL;
			}
		}

		// When there is a new player action
		boolean playerActionChanged = startPlayerAction != playerAction;
		if (playerActionChanged) {
			resetAnimationTicker();
		}
	}

    /**
     * 
    */
    public void hasDied() {
		playing.resetAll();
        audioPlayer.playEffect(AudioPlayer.DIE);
        deathCount++;
        availableJumps = 2;
    }

	/**
	 * Resets the animation ticker and index to 0
	 */
	private void resetAnimationTicker() {
		animationTicker = 0;
		animationIndex = 0;
	}

	/**
	 * Sets image of player
	 */
	public void setImages() {
		playerAnimations[IDLE] = LoadSave.getSprite(LoadSave.PLAYER_IDLE);
		playerAnimations[RUN] = LoadSave.getSprite(LoadSave.PLAYER_RUN);
		playerAnimations[JUMP] = LoadSave.getSprite(LoadSave.PLAYER_JUMP);
		playerAnimations[DOUBLE_JUMP] = LoadSave.getSprite(LoadSave.PLAYER_DOUBLE_JUMP);
		playerAnimations[FALL] = LoadSave.getSprite(LoadSave.PLAYER_FALL);
	}

	/**
	 * Stops the movement of player by setting movement booleans of player to false
	 */
	public void resetDirectionBooleans() {
		isJumping = false;
		isMovingLeft = false;
		isMovingRight = false;
	}

    public void resetPlayer() {
		resetDirectionBooleans();
        resetInAir();

		inAir = false;
		playerAction = IDLE;

        this.availableJumps = 2;
        this.x = 200;
        this.y = 200;
        
		hitbox.x = x;
		hitbox.y = y;

		if (!isEntityOnFloor(hitbox, lvlData))
			inAir = true;
	}

	public boolean isMovingLeft() {
		return isMovingLeft;
	}

	public void setMovingLeft(boolean isMovingLeft) {
		this.isMovingLeft = isMovingLeft;
	}

	public boolean isMovingRight() {
		return isMovingRight;
	}

	public void setMovingRight(boolean isMovingRight) {
		this.isMovingRight = isMovingRight;
	}

	public void setIsJumping(boolean isJumping) {
		this.isJumping = isJumping;
	}

    public int getDeathCount() {
		return this.deathCount;
	}

	public void addJumps() {
		this.availableJumps++;
	}
	
	public int getAvailableJumps() {
		return this.availableJumps;
	}

    public void resetDeathCount() {
        this.deathCount = 0;
    }

    public AudioPlayer getAudioPlayer() {
        return audioPlayer;
    }

}