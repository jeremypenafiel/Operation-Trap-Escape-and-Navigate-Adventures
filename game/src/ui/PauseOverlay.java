/**
 * CMSC 22 - 1
 * PauseOverlay handles positions of buttons and key mouse events when game is paused
 * 
 * @author KV. Celis
 * @author PK. Cordero
 * @author JJ. Peñafiel
 * 
 */

package ui;

import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import gamestates.Gamestate;
import gamestates.Playing;
import main.Game;
import utilities.LoadSave;
import static utilities.Constants.UI.PauseButtons.*;
import static utilities.Constants.UI.URMButtons.*;
import static utilities.Constants.UI.VolumeButtons.*;

public class PauseOverlay {
	
	private Playing playing;
	private BufferedImage backgroundImg;
	private int  bgX, bgY, bgW, bgH;
	private SoundButton musicButton, sfxButton;
	private UrmButton menuB, replayB, unpauseB;
	private VolumeButton volumeButton;
	
	public PauseOverlay(Playing playing) {
		
		this.playing = playing;
		loadBackground();
		createSoundButtons();
		createUrmButtons();
		createVolumeButton();
	}
	
	private void createVolumeButton() {
		int vX = (int) (620);
		int vY = (int) (429);
		volumeButton = new VolumeButton(vX, vY, SLIDER_WIDTH, VOLUME_HEIGHT, playing);
		
	}

	private void createUrmButtons() {
		int menuX = (int) (623);
		int replayX = (int) (697);
		int unpauseX = (int) (772);
		int bY = (int) (480);

		menuB = new UrmButton(menuX, bY, URM_SIZE, URM_SIZE, 2);
		replayB = new UrmButton(replayX, bY, URM_SIZE, URM_SIZE, 1);
		unpauseB = new UrmButton(unpauseX, bY, URM_SIZE, URM_SIZE, 0);
		
	}

	private void createSoundButtons() {
		int soundX = (int)(770);
		int musicY = (int)(260);
		int sfxY = (int)(318);
		musicButton = new SoundButton(soundX, musicY, SOUND_SIZE, SOUND_SIZE);
		sfxButton = new SoundButton(soundX, sfxY, SOUND_SIZE, SOUND_SIZE);
	}

	private void loadBackground() {

		backgroundImg = LoadSave.getSprite(LoadSave.PAUSE_BACKGROUND);
		
		bgW = (int) (backgroundImg.getWidth() * 1.3);
		bgH= (int) (backgroundImg.getHeight() * 1.3) ;
		bgX = Game.GAME_WIDTH / 2 - bgW / 2;
		bgY = 100;
	}
	
	public void update() {
		
		musicButton.update();
		sfxButton.update();
		
		menuB.update();
		replayB.update();
		unpauseB.update();
		
		volumeButton.update();
	}
	
	public void draw(Graphics g) {
		
		// Background
		g.drawImage(backgroundImg, bgX, bgY, bgW, bgH, null);
		
		// Sound Buttons
		musicButton.draw(g);
		sfxButton.draw(g);
		
		// UrmButtons
		menuB.draw(g);
		replayB.draw(g);
		unpauseB.draw(g);
		
		// Volume Slider
		volumeButton.draw(g);
	}
	

	public void mouseDragged(MouseEvent e) {
		if (volumeButton.isMousePressed()) {
			volumeButton.changeX(e.getX());
		}
	}
  
    public void mousePressed(MouseEvent e) {
        if(isIn(e, musicButton)) {
        	musicButton.setMousePressed(true);
            playing.getGame().getAudioPlayer().toggleSongMute();
        }
        else if (isIn(e, sfxButton)) {
        	sfxButton.setMousePressed(true);
            playing.getPlayer().getAudioPlayer().toggleEffectMute();
        }
        else if (isIn(e, menuB))
			menuB.setMousePressed(true);
		else if (isIn(e, replayB))
			replayB.setMousePressed(true);
		else if (isIn(e, unpauseB))
			unpauseB.setMousePressed(true);
		else if (isIn(e, volumeButton))
			volumeButton.setMousePressed(true);
    }

    
    public void mouseReleased(MouseEvent e) {
    	if (isIn(e, musicButton)) {
			if (musicButton.isMousePressed())
				musicButton.setMuted(!musicButton.isMuted());

		} else if (isIn(e, sfxButton)) {
			if (sfxButton.isMousePressed())
				sfxButton.setMuted(!sfxButton.isMuted());
		} else if (isIn(e, menuB)) {
			if (menuB.isMousePressed()) {
				Gamestate.state = Gamestate.MENU;
				playing.unpauseGame();
			}
		} else if (isIn(e, replayB)) {
			if (replayB.isMousePressed())
				playing.getLevelManager().restartGame();
                playing.getPlayer().resetDeathCount();
                playing.unpauseGame();
		 } else if (isIn(e, unpauseB)) {
			if (unpauseB.isMousePressed())
				playing.unpauseGame();
		}

		musicButton.resetBools();
		sfxButton.resetBools();
		menuB.resetBools();
		replayB.resetBools();
		unpauseB.resetBools();
    	volumeButton.resetBools();
    }

  
    public void mouseMoved(MouseEvent e) {
    	musicButton.setMouseOver(false);
    	sfxButton.setMouseOver(false);
    	menuB.setMouseOver(false);
		replayB.setMouseOver(false);
		unpauseB.setMouseOver(false);
		volumeButton.setMouseOver(false);
        
    	if(isIn(e, musicButton))
      	musicButton.setMouseOver(true);
    	else if (isIn(e, sfxButton))
      	sfxButton.setMouseOver(true);
    	else if (isIn(e, menuB))
			menuB.setMouseOver(true);
		else if (isIn(e, replayB))
			replayB.setMouseOver(true);
		else if (isIn(e, unpauseB))
			unpauseB.setMouseOver(true);
		else if (isIn(e, volumeButton))
			volumeButton.setMouseOver(true);

    }
    
    private boolean isIn(MouseEvent e, PauseButton b) {
    	return b.getBounds().contains(e.getX(), e.getY());
    	
    }
}