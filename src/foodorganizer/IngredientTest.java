package foodorganizer;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class IngredientTest {
	static ArrayList<Ingredient> arrayToSort;
	
	@BeforeEach
	static void preparation() {
		arrayToSort = new ArrayList<>();
		arrayToSort.add(new Ingredient("a", 2, "b"));
		arrayToSort.add(new Ingredient("b", 2, "d"));
		arrayToSort.add(new Ingredient("e", 2, "f"));
		arrayToSort.add(new Ingredient("c", 2, "h"));
		arrayToSort.add(new Ingredient("w", 2, "i"));
		arrayToSort.add(new Ingredient("a", 2, "a"));
		arrayToSort.add(new Ingredient("k", 2, "u"));
		arrayToSort.add(new Ingredient("b", 2, "w"));
		arrayToSort.add(new Ingredient("f", 2, "d"));
		arrayToSort.add(new Ingredient("a", 2, "b"));
	}

	@Test
	void testIngredientSorting() {
		
	}
	
	private boolean sortedArray() {
		
	}

}
