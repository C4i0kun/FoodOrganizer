package foodorganizer;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class RecipesManagerTest {
	static RecipesManager rManager;

	@BeforeAll
	static void preparation() {
		rManager = new RecipesManager("RecipesIndexTest.txt");
	}

	@Test
	void testTitle() {
		assertEquals("Hambúrguer de lentilha", rManager.recipes.get(0).getTitle());
	}

	@Test
	void testTitle2() {
		assertEquals("Hambúrguer de teste2", rManager.recipes.get(1).getTitle());
	}
}
