/*
 * CMSC 22 - 1
 * Unit Testing for HelperMethods in utilities package
 * 
 * @author KV. Celis
 * @author PK. Cordero
 * @author JJ. Peñafiel
 */

package utilitiestest;

import static org.junit.Assert.*;

import java.awt.geom.Rectangle2D;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import utilities.HelperMethods;

public class HelperMethodsTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}
	
	 
	// METHOD 1 - canMoveHere 
	@Test
	public void testCanMoveHereTopLeftCorner() {
		int[][] arr2D = {{-1},{-1}}; 
		assertFalse(HelperMethods.canMoveHere(-56, -56, 0, 0, arr2D) == true);
	}
	

	@Test
	public void testCanMoveHereBottomLeftCorner() {
		int[][] arr2D = {{14},{-1}}; 
		assertFalse(HelperMethods.canMoveHere(-56, 784, 0, 30, arr2D) == true);
	}
	
	@Test
	public void testCanMoveHereTopRightCorner() {
		int[][] arr2D = {{-1},{26}}; 
		assertFalse(HelperMethods.canMoveHere(1457, -56, 20, 0, arr2D) == true);
	}

	@Test
	public void testCanMoveHereBottomRightCorner() {
		int[][] arr2D = {{14},{26}}; 
		assertFalse(HelperMethods.canMoveHere(1456, 784, 20, 30, arr2D) == true);
	}
	
	@Test
	public void testCanMoveHereMidpointLeftCorners() {
		int[][] arr2D = {{-1},{-1}}; 
		assertFalse(HelperMethods.canMoveHere(-56, -56, 0, 30, arr2D) == true);
	} 
	
	@Test
	public void testCanMoveHereMidpointRightCorners() {
		int[][] arr2D = {{-1},{-1}}; 
		assertFalse(HelperMethods.canMoveHere(-56, -56, 20, 30, arr2D) == true);
	}
	
	@Test
	public void testCanMoveHere() {
		int[][] arr2D = {{0},{0}}; 
		assertEquals(true, HelperMethods.canMoveHere(0, 0, 0, 0, arr2D));
	}
	
	
	// METHOD 2 - isSolid
	
	@Test 
	public void testIsSolidLessThanZeroXY() {
		int[][] arr2D = {{-1},{-1}}; 
		assertTrue(HelperMethods.isSolid(-56, -56, arr2D) == true);
	}
	
	@Test 
	public void testIsSolidLessThanZeroXGreaterHeightY() {
		int[][] arr2D = {{14},{-1}}; 
		assertTrue(HelperMethods.isSolid(-56, 785, arr2D) == true);
	}
	
	@Test 
	public void testIsSolidGreaterWidthXLessThanZeroY() {
		int[][] arr2D = {{-1},{26}};  
		assertTrue(HelperMethods.isSolid(1457, -56, arr2D) == true);
	}
	
	@Test 
	public void testIsSolidGreaterWidthXGreaterHeightY() {
		int[][] arr2D = {{14},{26}}; 
		assertTrue(HelperMethods.isSolid(1457, 785, arr2D) == true);
	}
	
	@Test
	public void testIsSolidEqualWidthXEqualHeightY() {
		int[][] arr2D = {{14},{26}}; 
		assertTrue(HelperMethods.isSolid(1456, 784, arr2D) == true); 
	}
	
	@Test 
	public void testIsSolidBetweenBoundsXGreaterHeightY() {
		int[][] arr2D = {{14},{1}}; 
		assertTrue(HelperMethods.isSolid(56, 786, arr2D) == true);
	}
	
	@Test 
	public void testIsSolidLessThanZeroXBetweenBoundsY() {
		int[][] arr2D = {{14},{-1}}; 
		assertTrue(HelperMethods.isSolid(-56, 783, arr2D) == true);
	}
	
	@Test 
	public void testIsSolidXBetweenBoundsY() {
		int[][] arr2D = {{13},{-1}}; 
		assertTrue(HelperMethods.isSolid(-56, 783, arr2D) == true);
	}
	
	@Test
	public void testIsSolidZeroXZeroYArrZero() {
		int[][] arr2D = {{0},{0}}; 
		assertFalse(HelperMethods.isSolid(15, 16, arr2D) == true);
	}
 
	
	// METHOD 3 - isEntityOnFloor
	@Test
	public void testIsEntityOnFloorBottomLeft() {
		int[][] arr2D = {{10},{20}}; 
		var rec = new Rectangle2D.Float();
		assertTrue(HelperMethods.isEntityOnFloor(rec, arr2D) == true);
	}
	
	@Test
	public void testIsEntityOnFloorBottomRight() {
		int[][] arr2D = {{0},{0}}; 
		var rec = new Rectangle2D.Float();
		assertFalse(HelperMethods.isEntityOnFloor(rec, arr2D) == true);
	}
	
	@Test
	public void testIsEntityOnFloorBottomRight2() {
		int[][] arr2D = {{-16},{10}}; 
		var rec = new Rectangle2D.Float();
		assertTrue(HelperMethods.isEntityOnFloor(rec, arr2D) == true);
	}
	
}
