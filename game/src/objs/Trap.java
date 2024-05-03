/**
 * CMSC 22 - 1
 * Class that handles the traps in the game
 * 
 * @author KV. Celis
 * @author PK. Cordero
 * @author JJ. Peñafiel
 * 
 */
package objs;

public class Trap extends GameObject {

	public Trap(int x, int y, int objectType) {
		super(x, y + 17, objectType);
		initHitbox(55, 24, 15);
	}

}
