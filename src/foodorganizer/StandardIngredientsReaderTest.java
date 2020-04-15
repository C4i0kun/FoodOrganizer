package foodorganizer;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class StandardIngredientsReaderTest {
	static IngredientsManager iM;

	@BeforeAll
	static void preparation() {
		iM = new IngredientsManager();
		StandardIngredientsReader.readStandardIngredientsFile(iM, "StandardIngredientsTest.txt");
	}
	
	@Test
	void testIngredient0() {
		assertEquals("arroz integral", iM.getIngredients().get(0).getName());
	}
	
	@Test
	void testIngredients1() {
		assertEquals("feijão preto", iM.getIngredients().get(1).getName());
	}
	
	@Test
	void testIngredients2() {
		assertEquals("lentilha", iM.getIngredients().get(2).getName());
	}
	
	@Test
	void testAmount0() {
		assertEquals(0, iM.getIngredients().get(0).getAmount());
	}
	
	@Test
	void testAmount1() {
		assertEquals(0, iM.getIngredients().get(1).getAmount());
	}
	
	@Test
	void testAmount2() {
		assertEquals(0, iM.getIngredients().get(2).getAmount());
	}
	
	@Test
	void testUnits0() {
		assertEquals("g", iM.getIngredients().get(0).getUnitType());
	}
	
	@Test
	void testUnits1() {
		assertEquals("g", iM.getIngredients().get(1).getUnitType());
	}
	
	@Test
	void testUnits2() {
		assertEquals("xícara(s)", iM.getIngredients().get(2).getUnitType());
	}
	
	@Test
	void testException() {
		IngredientsManager eM = new IngredientsManager();
		StandardIngredientsReader.readStandardIngredientsFile(eM, "hflakhfklaçfk.txt"); //this file doesn't exist
		assertEquals(0, eM.getIngredients().size());
	}

}
