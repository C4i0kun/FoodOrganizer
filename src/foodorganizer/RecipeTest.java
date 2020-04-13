package foodorganizer;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class RecipeTest {
	static Recipe recipe;

	@BeforeAll
	static void preparation() {
		recipe = new Recipe("hamburguerdelentilha.txt");
	}
	
	@Test
	void testTitle() {
		assertEquals("Hambúrguer de lentilha", recipe.getTitle());
	}

	@Test
	void testIngredientName0() {
		assertEquals("lentilha", recipe.getIngredients().get(0).getName());
	}

	@Test
	void testIngredientName1() {
		assertEquals("farinha de aveia", recipe.getIngredients().get(1).getName());
	}

	@Test
	void testIngredientName2() {
		assertEquals("cebola", recipe.getIngredients().get(2).getName());
	}

	@Test
	void testIngredientAmount0() {
		assertEquals(1, recipe.getIngredients().get(0).getAmount());
	}

	@Test
	void testIngredientAmount1() {
		assertEquals(3, recipe.getIngredients().get(1).getAmount());
	}

	@Test
	void testIngredientAmount2() {
		assertEquals(7, recipe.getIngredients().get(2).getAmount());
	}
	
	@Test
	void testIngredientUnit0() {
		assertEquals("quilo", recipe.getIngredients().get(0).getUnitType());
	}

	@Test
	void testIngredientUnit1() {
		assertEquals("grama", recipe.getIngredients().get(1).getUnitType());
	}

	@Test
	void testIngredientUnit2() {
		assertEquals("unidade", recipe.getIngredients().get(2).getUnitType());
	}
	
	@Test
	void testPreparationStep0() {
		assertEquals("preparo1", recipe.getPreparationSteps().get(0));
	}
	
	@Test
	void testPreparationStep1() {
		assertEquals("preparo2", recipe.getPreparationSteps().get(1));
	}

}
