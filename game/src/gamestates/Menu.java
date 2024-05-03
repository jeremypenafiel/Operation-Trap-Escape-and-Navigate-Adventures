/**
 * CMSC 22 - 1
 * Menu is the homeport of the most gamestates
 * where the player can check Credits, Play the game, or even Quit
 * 
 * @author KV. Celis
 * @author PK. Cordero
 * @author JJ. Peñafiel
 * 
 */

package gamestates;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import main.Game;
import ui.MenuButton;
import static utilities.Constants.Background.*;
import utilities.LoadSave;

public class Menu extends State implements Statemethods {
    
    private MenuButton[] buttons = new MenuButton[3];
    
	private BufferedImage imgMenu;
    private final int xOffsetCenter = BACKGROUND_WIDTH/2;
    private final int yOffsetCenter = BACKGROUND_HEIGHT/2;

    public Menu(Game game) {  
        super(game);
        loadGraphics();
        loadButtons();
    }
    
    private void loadGraphics() {
        this.imgMenu = LoadSave.getSprite(LoadSave.MENU_BACKGROUND);
    }

    private void loadButtons() {
        buttons[0] = new MenuButton(Game.GAME_WIDTH/2, (int) (70 * Game.SCALE), 0, Gamestate.PLAYING, LoadSave.BUTTON_START);
        buttons[1] = new MenuButton(Game.GAME_WIDTH/2, (int) (110 * Game.SCALE), 16, Gamestate.CREDITS, LoadSave.BUTTON_CREDITS);
        buttons[2] = new MenuButton(Game.GAME_WIDTH/2, (int) (190 * Game.SCALE), 0, Gamestate.QUIT, LoadSave.BUTTON_QUIT);
    }
    
    @Override
    public void update() {
        for (MenuButton btMenu : buttons) {
            btMenu.update();
        }       
    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(imgMenu, (Game.GAME_WIDTH/2) - xOffsetCenter, (Game.GAME_HEIGHT/2) - yOffsetCenter, 
            BACKGROUND_WIDTH, BACKGROUND_HEIGHT, null);

        for (MenuButton btMenu : buttons) {
            btMenu.draw(g);
        }
        
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            Gamestate.state = Gamestate.PLAYING;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        for (MenuButton btMenu : buttons) {
            btMenu.setMouseOver(false);
        }        

        for (MenuButton btMenu : buttons) {
            if (isIn(e, btMenu)) {
                btMenu.setMouseOver(true);
                break;
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        for (MenuButton btMenu : buttons) {
            if(isIn(e, btMenu)) {
                btMenu.setMousePressed(true);
                //break;
            }
        }       
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        for (MenuButton btMenu : buttons) {

            if(isIn(e, btMenu)) {
                if(btMenu.isMousePressed()) {
                    btMenu.applyGamestate();
                    
                }
                break;
            }
            
        }    
        
        resetButtons();
    }

    private void resetButtons() {
        for (MenuButton btMenu : buttons) {
            btMenu.resetBools();
        }
    }   
}