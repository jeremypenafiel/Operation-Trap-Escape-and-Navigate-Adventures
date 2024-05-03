/**
 * CMSC 22 - 1
 * Gamestate is an enum that shows the different gamestates present in the game 
 * 
 * @author KV. Celis
 * @author PK. Cordero
 * @author JJ. Peñafiel
 * 
 */

package gamestates;

public enum Gamestate {
    
    PLAYING, MENU, CREDITS, QUIT, WIN;

    public static Gamestate state = MENU; 
}