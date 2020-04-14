package foodorganizer;

import java.util.ArrayList;
import java.util.Iterator;

public class IngredientsManager {
	private ArrayList<Ingredient> ingredients;

	/* Constructor */
	public IngredientsManager(String standardIngredients) {
		ingredients = new ArrayList<>();
		
		StandardIngredientsReader.readStandardIngredientsFile(this, standardIngredients);
	}
	
	//this constructor is good for tests
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
			int ingredientIndex = this.ingredientIndex(ingredient, unitType);
			Ingredient ingredientToModify = this.getIngredients().get(ingredientIndex);
			ingredientToModify.addAmount(amount);
		} catch (IngredientNotIndexedException e) {
			Ingredient ingredientToAdd = new Ingredient(ingredient, amount, unitType);
			this.getIngredients().add(ingredientToAdd);
		}
	}
	
	public void printTotalIngredients() {
		this.getIngredients().sort(null);
		
		Iterator<Ingredient> ingredientsIterator = this.getIngredients().iterator();
		
		while (ingredientsIterator.hasNext()) {
			System.out.println(ingredientsIterator.next());
		}
	}
	
	/* Private Functions */
	private int ingredientIndex(String ingredient, String unitType) throws IngredientNotIndexedException {
		Iterator<Ingredient> iterator = this.getIngredients().iterator();
		int index = 0;
		
		while (iterator.hasNext()) {
			Ingredient currentIngredient = iterator.next();
			if (currentIngredient.getName().contentEquals(ingredient) && currentIngredient.getUnitType().contentEquals(unitType)) {
				return index;
			}
			index++;
		}
		
		throw new IngredientNotIndexedException();
	}
}
