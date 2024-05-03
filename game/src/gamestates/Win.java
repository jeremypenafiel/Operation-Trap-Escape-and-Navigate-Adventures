/**
 * CMSC 22 - 1
 * Win is the gamestate when the player finishes the final level 
 * 
 * @author KV. Celis
 * @author PK. Cordero
 * @author JJ. Peñafiel
 * 
 */

package gamestates;

import java.awt.image.BufferedImage;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import static utilities.Constants.Background.*;

import main.Game;
import utilities.LoadSave;

public class Win extends State implements Statemethods {
    private BufferedImage imgWin;
    private final int xOffsetCenter = BACKGROUND_WIDTH/2;
    private final int yOffsetCenter = BACKGROUND_HEIGHT/2;
    private Font font = new Font("Impact", Font.PLAIN, 40);


    public Win(Game game) {
        super(game); 
        loadGraphics();
    }

    private void loadGraphics() {        
        this.imgWin = LoadSave.getSprite(LoadSave.WIN);
    }

    @Override
    public void update() {
        
    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(imgWin, (Game.GAME_WIDTH/2) - xOffsetCenter, (Game.GAME_HEIGHT/2) - yOffsetCenter, 
            BACKGROUND_WIDTH, BACKGROUND_HEIGHT, null);
        
		g.setColor(Color.BLACK);
		g.setFont(font);
		g.drawString("" + game.getPlaying().getPlayer().getDeathCount(), (int) (Game.GAME_WIDTH/2 + 50), (int) (Game.GAME_HEIGHT/2 + 72));
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
            this.game.getPlaying().getPlayer().resetDeathCount();
            Gamestate.state = Gamestate.MENU;

        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        
    }
    
}
