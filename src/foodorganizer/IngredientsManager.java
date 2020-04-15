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
	public void addIngredientAmount(String ingredient, int amount, String unitType, boolean verbalException) {
		try {
			int ingredientIndex = this.ingredientIndex(ingredient, unitType);
			Ingredient ingredientToModify = this.getIngredients().get(ingredientIndex);
			ingredientToModify.addAmount(amount);
		} catch (IngredientNotIndexedException e) {
			Ingredient ingredientToAdd = new Ingredient(ingredient, amount, unitType);
			this.getIngredients().add(ingredientToAdd);
			if (verbalException) {
				System.out.println("Ingredient '" + ingredient + "(" + unitType + ")' not in standard ingredients list, adding manually...");
			}
		}
	}
	
	public String totalIngredientsString() {
		this.getIngredients().sort(null);
		String totalIngredientsString = "";
		
		for (int i = 0; i < this.getIngredients().size(); i++) {
			Ingredient currentIngredient = this.getIngredients().get(i);
			
			if (currentIngredient.getAmount() == 0) {
				continue;
			}
			
			String toAdd = "";
			if (i > 0 && currentIngredient.getName().contentEquals(this.getIngredients().get(i-1).getName())) {
				toAdd += ", " + currentIngredient.getAmount() + " " + currentIngredient.getUnitType();
			} else {
				if (i != 0) {
					toAdd += System.lineSeparator();
				}
				toAdd += currentIngredient.getName() + " - " + currentIngredient.getAmount() + " " + currentIngredient.getUnitType();
			}
			
			totalIngredientsString += toAdd;
		}
		
		return totalIngredientsString;
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
	
	/* Auxiliary test functions */
	//this function exists to help jUnit test the exception directly
	public boolean testIngredientIndex(String ingredient, String unitType) {
		try {
			ingredientIndex(ingredient, unitType);
			return true;
		} catch (IngredientNotIndexedException e){
			return false;
		}
	}
}
