package foodorganizer;

import java.io.IOException;
import java.util.Iterator;

public abstract class Controller {
	static IngredientsManager ingredientsManager;
	static RecipesManager recipesManager;
	
	/* Controller algorithm */
	public static void run() {
		ingredientsManager = new IngredientsManager("StandardIngredients.txt");
		recipesManager = new RecipesManager("Recipes/RecipesIndex.txt");
		
		calculateTotalIngredients();
		TXTWriter.write("IngredientsLists.txt", ingredientsManager.totalIngredientsString());
		
		try {
			PDFWriter.write(ingredientsManager.totalIngredientsString(), recipesManager);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
				getIngredientsManager().addIngredientAmount(currentIngredient.getName(), currentIngredient.getAmount(), currentIngredient.getUnitType(), true);
			}
		}
		
	}
}
