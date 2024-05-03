/**
 * CMSC 22 - 1
 * AudioPlayer handles all audio-related events
 * 
 * @author PK. Cordero
 * @author AJ. Jaspa
 * 
 */

package sounds;

import java.io.IOException;
import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.BooleanControl;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class AudioPlayer {
	public static int DIE = 0;
	public static int JUMP = 1;

	private Clip[] effects;
    private Clip song;
	private float volume = 1f;
	private boolean songMute, effectMute;

	public AudioPlayer() {
		loadSongs();
		loadEffects();
		playSong(song);
	}

	private void loadSongs() {
        song = getClip("background");
	}

	private void loadEffects() {
		String[] effectNames = {"dead", "jump"};
		effects = new Clip[effectNames.length];
		for (int i = 0; i < effects.length; i++)
			effects[i] = getClip(effectNames[i]);
		
		updateEffectsVolume();
	}

	private Clip getClip(String name) {
		URL url = getClass().getResource("/Audio/" + name + ".wav");
		AudioInputStream audio;

		try {
			audio = AudioSystem.getAudioInputStream(url);
			Clip c = AudioSystem.getClip();
			c.open(audio);
			return c;

		} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {

			e.printStackTrace();
		}

		return null;

	}

	public void setVolume(float volume) {
		this.volume = volume;
		updateSongVolume();
		updateEffectsVolume();
	}

	public void stopSong() {
		if (song.isActive()) {
			song.stop();
        }
	}

	public void playEffect(int effect) {
		effects[effect].setMicrosecondPosition(0);
		effects[effect].start();
	}

	public void playSong(Clip song) {
		stopSong();
		updateSongVolume();
		song.setMicrosecondPosition(0);
		song.loop(Clip.LOOP_CONTINUOUSLY);
	}

	public void toggleSongMute() {
		this.songMute = !songMute;
        BooleanControl booleanControl = (BooleanControl) song.getControl(BooleanControl.Type.MUTE);
        booleanControl.setValue(songMute);
	}

	public void toggleEffectMute() {
		this.effectMute = !effectMute;
		for (Clip c : effects) {
			BooleanControl booleanControl = (BooleanControl) c.getControl(BooleanControl.Type.MUTE);
			booleanControl.setValue(effectMute);
		}
		if (!effectMute)
			playEffect(JUMP);
	}

	private void updateSongVolume() {
		FloatControl gainControl = (FloatControl) song.getControl(FloatControl.Type.MASTER_GAIN);
		float range = gainControl.getMaximum() - gainControl.getMinimum();
		float gain = (range * volume) + gainControl.getMinimum();
		gainControl.setValue(gain);

	}

	private void updateEffectsVolume() {
		for (Clip c : effects) {
			FloatControl gainControl = (FloatControl) c.getControl(FloatControl.Type.MASTER_GAIN);
			float range = gainControl.getMaximum() - gainControl.getMinimum();
			float gain = (range * volume) + gainControl.getMinimum();
			gainControl.setValue(gain);
		}
	}

}