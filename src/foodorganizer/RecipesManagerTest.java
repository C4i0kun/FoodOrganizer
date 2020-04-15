package foodorganizer;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class RecipesManagerTest {
	static RecipesManager rManager;

	@BeforeAll
	static void preparation() {
		rManager = new RecipesManager("Recipes/RecipesIndexTest.txt");
		//Note that, if the file above doesnt exist, the program ends to avoid problems!
	}

	@Test
	void testTitle() {
		assertEquals("Hambúrguer de lentilha", rManager.recipes.get(0).getTitle());
	}

	@Test
	void testTitle2() {
		assertEquals("Hambúrguer de teste2", rManager.recipes.get(1).getTitle());
	}
	
	@Test
	void testException() {
		RecipesManager eManager = new RecipesManager("Recipes/RecipesIndexTest2.txt"); //this index has a non txt file that should be ignored!
		assertEquals("Hambúrguer de teste2", eManager.getRecipes().get(0).getTitle());
	}
}
