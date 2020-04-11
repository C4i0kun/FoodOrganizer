package foodorganizer;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class IngredientsUnitsManagerTest {
	static IngredientsUnitsManager iUM;

	@BeforeAll
	static void preparation() {
		iUM = new IngredientsUnitsManager(true); //initialize objects in test mode
		iUM.addToIngredientsUnitsArray("leite", "litro(s)"); //add non indexed ingredient
		iUM.addToIngredientsUnitsArray("arroz integral", "g"); //add the same ingredient, array size must be 4 in the end, because it wont be added again.
	}
	
	@Test
	void testIngredient0() {
		assertEquals("arroz integral", iUM.getIngredientsList().get(0));
	}
	
	@Test
	void testIngredients1() {
		assertEquals("feijão preto", iUM.getIngredientsList().get(1));
	}
	
	@Test
	void testIngredients2() {
		assertEquals("lentilha", iUM.getIngredientsList().get(2));
	}
	
	@Test
	void testUnits0() {
		assertEquals("g", iUM.getUnitsList().get(0));
	}
	
	@Test
	void testUnits1() {
		assertEquals("g", iUM.getUnitsList().get(1));
	}
	
	@Test
	void testUnits2() {
		assertEquals("xícara(s)", iUM.getUnitsList().get(2));
	}
	
	@Test
	void testAdditionOfNonIndexedIngredient() {
		assertEquals("leite", iUM.getIngredientsList().get(3));
	}
	
	@Test
	void testAdditionOfNonIndexedUIngredientUnit() {
		assertEquals("litro(s)", iUM.getUnitsList().get(3));
	}
	
	@Test
	void testIngredientsArraySize() {
		System.out.println(iUM.getIngredientsList());
		assertEquals(4, iUM.getIngredientsList().size());
	}
	
	@Test
	void testUnitsArraySize() {
		System.out.println(iUM.getUnitsList());
		assertEquals(4, iUM.getUnitsList().size());
	}

}

/* README
 * To use this test file, create a txt file called "IngredientsUnitsTest.txt" with the following content:
 * 
 * arroz integral - g
 * feijão preto - g
 * lentilha - xícara(s)
 * 
 * (ignore the * symbols and spaces before ingredient names). If this file desn't exist, the FileNotFound exception will terminate the test
 * (this is, by itself, a way to confirm this exception is working).
 */
