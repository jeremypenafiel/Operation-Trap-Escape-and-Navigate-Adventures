/**
 * @author Jeremy Jobert Peñafiel
 */

package main;

import static main.Game.GAME_HEIGHT;
import static main.Game.GAME_WIDTH;

import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;

import inputs.KeyboardInput;
import inputs.MouseInputs;

public class GamePanel extends JPanel {

	private Game game;

	public GamePanel(Game game) {
		this.game = game;
		setPanelSize();
		// setImages();
		addKeyListener(new KeyboardInput(this));
		addMouseListener(new MouseInputs(this));
	}

	/**
	 * Sets size of panel
	 */
	public void setPanelSize() {
		Dimension size = new Dimension(GAME_WIDTH, GAME_HEIGHT);
		setPreferredSize(size);
	}

	/**
	 * Paints the screen
	 */
	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		game.render(g);
	}

	public Game getGame() {
		return game;
	}

}
