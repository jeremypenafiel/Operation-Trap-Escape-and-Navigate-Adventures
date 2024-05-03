/**
 * CMSC 22 - 1
 * State is the abstract class where all gamestates extend to
 * 
 * @author KV. Celis
 * @author PK. Cordero
 * @author JJ. Peñafiel
 * 
 */

package gamestates;

import java.awt.event.MouseEvent;

import main.Game;
import ui.MenuButton;

public abstract class State {
    protected Game game;
    
    public State(Game game) {
        this.game = game;
    }

    public boolean isIn(MouseEvent e, MenuButton btMenu) {
        return btMenu.getBounds().contains(e.getX(), e.getY());
    } 

    public Game getGame() {
        return game;
    }
}