package foodorganizer;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class IngredientTest {
	static ArrayList<Ingredient> arrayToSort;
	
	@BeforeEach
	void preparation() {
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
		arrayToSort.add(new Ingredient("a", 2, "a"));
		arrayToSort.add(new Ingredient("a", 2, "d"));
		arrayToSort.add(new Ingredient("b", 2, "b"));
		arrayToSort.add(new Ingredient("b", 2, "a"));
		arrayToSort.add(new Ingredient("a", 2, "x"));
	}

	@Test
	void testIngredientSorting() {
		arrayToSort.sort(null);
		
		for (int i = 0; i < arrayToSort.size(); i++) {
			System.out.println(arrayToSort.get(i));
		}
		
		assertEquals(true, sortedArray());
		
	}
	
	private boolean sortedArray() {
		for (int i = 1; i < arrayToSort.size(); i++) {
			if (arrayToSort.get(i-1).compareTo(arrayToSort.get(i)) == 1) {
				return false;
			}
		}
		return true;
	}

}
