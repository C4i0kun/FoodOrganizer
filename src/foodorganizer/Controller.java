package foodorganizer;

import java.util.Iterator;

public abstract class Controller {
	static IngredientsManager ingredientsManager;
	static RecipesManager recipesManager;
	
	/* Controller algorithm */
	public static void run() {
		ingredientsManager = new IngredientsManager("StandardIngredientsTest.txt");
		recipesManager = new RecipesManager("RecipesIndexTest.txt");
		
		calculateTotalIngredients();
		ingredientsManager.printTotalIngredients();
	}
	
	/* Getters */
	public static IngredientsManager getIngredientsManager() {
		return ingredientsManager;
	}


	public static RecipesManager getRecipesManager() {
		return recipesManager;
	}

	/* Private Functions */
	private static void calculateTotalIngredients() {
		Iterator<Recipe> recipeIterator = getRecipesManager().recipes.iterator();
		while (recipeIterator.hasNext()) {
			Recipe currentRecipe = recipeIterator.next();
			
			Iterator<Ingredient> ingredientIterator = currentRecipe.getIngredients().iterator();
			
			while (ingredientIterator.hasNext()) {
				Ingredient currentIngredient = ingredientIterator.next();
				getIngredientsManager().addIngredientAmount(currentIngredient.getName(), currentIngredient.getAmount(), currentIngredient.getUnitType());
			}
		}
		
	}
}
