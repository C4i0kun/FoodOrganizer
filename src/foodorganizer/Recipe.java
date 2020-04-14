package foodorganizer;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Recipe {
	private String title;
	private ArrayList<Ingredient> ingredients;
	private ArrayList<String> preparationSteps;
	
	/* Constructor */
	public Recipe(String fileName) {
		ingredients = new ArrayList<>();
		preparationSteps = new ArrayList<>();
		
		try {
			this.readRecipeFile(fileName);
		} catch (IOException e) {
			if (e instanceof FileNotFoundException) {
				System.out.println("The file '" + fileName +"'does not exist!");
			} else {
				System.out.println("Error reading '" + fileName + "' file");
			}
		}
	}

	/* Getters */
	public ArrayList<Ingredient> getIngredients() {
		return ingredients;
	}

	public ArrayList<String> getPreparationSteps() {
		return preparationSteps;
	}
	
	public String getTitle() {
		return title;
	}

	/* Setters */
	public void setTitle(String title) {
		this.title = title;
	}
	
	/* Private Functions */
	private void readRecipeFile(String fileName) throws IOException {
		FileReader file = new FileReader(fileName);
		BufferedReader bFile = new BufferedReader(file);
		
		String line = bFile.readLine();
		
		boolean readingIngredients = false;
		boolean readingPreparationSteps = false;
		
		while (line != null) {
			if (StringManager.stringStartsWith(line, "TÍTULO:")) {
				this.autoSetTitle(line);
			} else if (StringManager.stringStartsWith(line, "INGREDIENTES:")) {
				readingIngredients = true;
				readingPreparationSteps = false;
				line = bFile.readLine();
			} else if (StringManager.stringStartsWith(line, "MODO DE PREPARO:")) {
				readingPreparationSteps = true;
				readingIngredients = false;
				line = bFile.readLine();
			}
			
			if (readingIngredients) {
				this.autoSetIngredient(line);
			}
			
			if (readingPreparationSteps) {
				this.autoSetPreparationSteps(line);
			}
			
 			line = bFile.readLine();
		}
		
		
		bFile.close();
	}
	
	private void autoSetTitle(String line) {
		int separatorIndex = StringManager.findIndexOfChar(line, ':');
		if (line.charAt(separatorIndex) == ' ') {
			separatorIndex++;
		}
		
		this.setTitle(StringManager.reduceString(line, separatorIndex + 2, line.length() - 1));
	}
	
	private void autoSetIngredient(String line) {
		int separatorIndex = StringManager.findIndexOfChar(line, '-');
		
		int unitTypeIndex = separatorIndex;
		int spacesRead = 0;
		while (spacesRead < 2) {
			if (line.charAt(unitTypeIndex) == ' ') {
				spacesRead++;
			}
			unitTypeIndex++;
		}
		
		//unitTypeIndex--;
		
		String ingredientName = StringManager.reduceString(line, 0, separatorIndex - 2);
		int amount = Integer.parseInt(StringManager.reduceString(line, separatorIndex + 2, unitTypeIndex - 2));
		String unitType = StringManager.reduceString(line, unitTypeIndex, line.length() - 1);
		
		this.getIngredients().add(new Ingredient(ingredientName, amount, unitType));
	}
	
	private void autoSetPreparationSteps(String line) {
		int separatorIndex = StringManager.findIndexOfChar(line, '-');
		String preparationStep = StringManager.reduceString(line, separatorIndex + 2, line.length() - 1);
		
		this.getPreparationSteps().add(preparationStep);
	}
}
