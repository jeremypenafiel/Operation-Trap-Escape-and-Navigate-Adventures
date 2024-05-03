/*
 * CMSC 22 - 1
 * Unit Testing for Constants in utilities package
 * 
 * @author KV. Celis
 * @author PK. Cordero
 * @author JJ. Peñafiel
 */

package utilitiestest;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import utilities.Constants.PlayerConstants;

public class ConstantsTest {   

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void idleConstantsTest() {
		assertEquals(11, PlayerConstants.GetNumberOfImages(0));
	}
	
	@Test
	public void runConstantsTest() {
		assertEquals(12, PlayerConstants.GetNumberOfImages(1));
	}
	
	@Test
	public void jumpConstantsTest() {
		assertEquals(1, PlayerConstants.GetNumberOfImages(2));
	}
	
	
	@Test
	public void doubleJumpConstantsTest() {
		assertEquals(6, PlayerConstants.GetNumberOfImages(3));
	}
	
	@Test
	public void fallConstantsTest() {
		assertEquals(1, PlayerConstants.GetNumberOfImages(4));
	}
	
	@Test
	public void defaultConstantsTest() {
		assertEquals(1, PlayerConstants.GetNumberOfImages(-1));
	}

}
