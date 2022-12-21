/**
 * 
 * @author Jeremy Jobert Peñafiel
 *
 */
package main;

import java.awt.Graphics;

import entities.Player;
import levels.LevelManager;

public class Game implements Runnable {
	private GameWindow gameWindow;
	private GamePanel gamePanel;
	private Thread gameThread;
	private Player player;
	private LevelManager levelManager;
	private final int FPS_SET = 120; // Set frames per second
	private final int UPS_SET = 200; // Set updates per second
	private boolean running = false;

	public final static int TILE_DEFAULT_SIZE = 16;
	public final static float SCALE = 3.5f;
	public final static int TILES_IN_WIDTH = 26;
	public final static int TILES_IN_HEIGHT = 14;
	public final static int TILE_SIZE = (int) (TILE_DEFAULT_SIZE * SCALE);
	public final static int GAME_WIDTH = TILES_IN_WIDTH * TILE_SIZE;
	public final static int GAME_HEIGHT = TILES_IN_HEIGHT * TILE_SIZE;

	public Game() {
		initClasses();

		gamePanel = new GamePanel(this);
		gameWindow = new GameWindow(gamePanel);
		gamePanel.requestFocus();

		startGameLoop();
	}

	private void initClasses() {
		levelManager = new LevelManager(this);
		player = new Player(200, 200, (int) (32*SCALE), (int) (32*SCALE));
		player.loadLevelData(levelManager.getCurrentLevel().getLevelData());
		

	}

	/**
	 * Starts the game
	 */
	private void startGameLoop() {
		running = true;
		gameThread = new Thread(this);
		gameThread.start();
	}

	/**
	 * updates the player and the levelManager
	 */
	public void update() {
		player.update();
		levelManager.update();
	}
	
	/**
	 * Renders the player and the levels
	 * @param g -  a Graphics object
	 */
	public void render(Graphics g) {
		levelManager.draw(g);
		player.render(g);
	}

	/**
	 * Contains the game loop and FPS counter
	 */
	@Override
	public void run() {

		final double timePerFrameNanos = 1000000000.0 / FPS_SET;
		final double timePerUpdateNanos = 1000000000.0 / UPS_SET;

		long startTimeNanos = System.nanoTime();
		long frameTimerMillis = System.currentTimeMillis();
		double updateTimeDelta = 0;
		double framesTimeDelta = 0;
		int frames = 0;

		while (running) {

			long currentTimeNanos = System.nanoTime();
			updateTimeDelta += (currentTimeNanos - startTimeNanos);
			framesTimeDelta += (currentTimeNanos - startTimeNanos);
			startTimeNanos = currentTimeNanos;

			if (updateTimeDelta >= timePerUpdateNanos) {
				update();
				updateTimeDelta -= timePerUpdateNanos;
			}

			if (framesTimeDelta >= timePerFrameNanos) {
				gamePanel.repaint();
				frames++;
				framesTimeDelta -= timePerFrameNanos;
			}

			if (System.currentTimeMillis() - frameTimerMillis >= 1000) {
				//System.out.println(frames);
				frames = 0;
				frameTimerMillis += 1000;
			}
		}
	}
	
	/**
	 * Returns the player
	 * @return player - a Player object
	 */
	public Player getPlayer() {
		return player;
	}

	/**
	 * Reset
	 */
	public void windowFocusLost() {
		player.resetDirectionBooleans();

	}

}
