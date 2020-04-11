package foodorganizer;

import java.util.ArrayList;
import java.util.Iterator;

public class IngredientsManager {
	private ArrayList<Ingredient> ingredients;

	/* Constructor */
	public IngredientsManager() {
		ingredients = new ArrayList<>();
	}
	
	/* Getters */
	public ArrayList<Ingredient> getIngredients() {
		return ingredients;
	}
	
	/* Public functions */
	public void addIngredientAmount(String ingredient, int amount, String unitType) {
		try { /* Ainda não checamos tipos diferentes, por enquanto */
			int ingredientIndex = this.ingredientIndex(ingredient);
			Ingredient ingredientToModify = this.getIngredients().get(ingredientIndex);
			ingredientToModify.addAmount(amount);
		} catch (IngredientNotIndexedException e) {
			Ingredient ingredientToAdd = new Ingredient(ingredient, amount, unitType);
			this.getIngredients().add(ingredientToAdd);
		}
	}
	
	/* Private Functions */
	private int ingredientIndex(String ingredient) throws IngredientNotIndexedException {
		Iterator<Ingredient> iterator = this.getIngredients().iterator();
		int index = 0;
		
		while (iterator.hasNext()) {
			if (iterator.next().getName().contentEquals(ingredient)) {
				return index;
			}
			index++;
		}
		
		throw new IngredientNotIndexedException();
	}
}
