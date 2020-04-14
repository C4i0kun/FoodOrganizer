package foodorganizer;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class IngredientsManagerTest {
	IngredientsManager iM;
	
	@BeforeEach
	void preparation() {
		iM = new IngredientsManager();
		iM.addIngredientAmount("Porco", 1, "Unidade(s)");
		iM.addIngredientAmount("Arroz", 2, "Quilo(s)");
		iM.addIngredientAmount("Arroz", 2, "Saco(s)");
	}

	@Test
	void testIngredient0() {
		assertEquals("Porco", iM.getIngredients().get(0).getName());
	}
	
	@Test
	void testIngredients1() {
		assertEquals("Arroz", iM.getIngredients().get(1).getName());
	}
	
	@Test
	void testIngredients2() {
		assertEquals("Arroz", iM.getIngredients().get(2).getName());
	}

	@Test
	void testAmount0() {
		assertEquals(1, iM.getIngredients().get(0).getAmount());
	}
	
	@Test
	void testAmount1() {
		assertEquals(2, iM.getIngredients().get(1).getAmount());
	}
	
	@Test
	void testAmount2() {
		assertEquals(2, iM.getIngredients().get(2).getAmount());
	}
	
	@Test
	void testUnits0() {
		assertEquals("Unidade(s)", iM.getIngredients().get(0).getUnitType());
	}
	
	@Test
	void testUnits1() {
		assertEquals("Quilo(s)", iM.getIngredients().get(1).getUnitType());
	}
	
	@Test
	void testUnits2() {
		assertEquals("Saco(s)", iM.getIngredients().get(2).getUnitType());
	}
	
	@Test
	void testAddExistingIngredient() {
		iM.addIngredientAmount("Arroz", 6, "Quilo(s)");
		assertEquals(8, iM.getIngredients().get(1).getAmount());
	}
}
