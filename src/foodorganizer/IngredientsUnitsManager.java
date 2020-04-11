package foodorganizer;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

public class IngredientsUnitsManager {
	ArrayList<String> ingredientsList;
	ArrayList<String> unitsList;
	
	/* Constructor */
	public IngredientsUnitsManager() {
		ingredientsList = new ArrayList<String>();
		unitsList = new ArrayList<String>();
		
		readIngredientsUnitsFile();
	}
	
	/* Getters */
	public ArrayList<String> getIngredientsList() {
		return ingredientsList;
	}

	public ArrayList<String> getUnitsList() {
		return unitsList;
	}
	
	/* Public Functions */
	public int ingredientIndex(String ingredient) throws IngredientNotIndexedException {
		Iterator<String> iterator = this.getIngredientsList().iterator();
		int index = 0;
		
		while (iterator.hasNext()) {
			if (iterator.next() == ingredient) {
				return index;
			}
			index++;
		}
		
		throw new IngredientNotIndexedException();
	}
	
	public boolean unitIndexed(String ingredient, String unit) throws IngredientNotIndexedException {
		int ingredientIndex = ingredientIndex(ingredient);
		
		if (unitsList.get(ingredientIndex) == unit) {
			return true;
		}
		
		return false;
		
	}

	/* Private functions */
	private void readIngredientsUnitsFile() {
		try {
			FileReader file = new FileReader("IngredientsUnits.txt");
			BufferedReader bFile = new BufferedReader(file);
			
			String line = bFile.readLine();
			
			while (line != null) {
				String ingredient = identifyIngredient(line);
				String unitType = identifyUnitType(line);
				
				addToIngredientsUnitsArray(ingredient, unitType);
				
				line = bFile.readLine();
			}
			
			bFile.close();
			
		} catch (IOException e) {
			if (e instanceof FileNotFoundException) {
				System.out.println("The 'IngredientsUnits.txt' does not exist!");
			} else {
				System.out.println("Error reading 'IngredientsUnits.txt' file!");
			}
			e.printStackTrace();
		}
	}
	
	private String identifyIngredient(String lineRead) {
		int separatorIndex = StringManager.findIndexOfChar(lineRead, '-');
		return StringManager.reduceString(lineRead, 0, separatorIndex - 2);
	}
	
	private String identifyUnitType(String lineRead) {
		int separatorIndex = StringManager.findIndexOfChar(lineRead, '-');
		return StringManager.reduceString(lineRead, separatorIndex + 2, lineRead.length() - 1);
	}
	
	private void addToIngredientsUnitsArray(String ingredient, String unit) {
		try {
			int ingredientIndex = this.ingredientIndex(ingredient);
			
			if (this.unitIndexed(ingredient, unit) == true) {
				System.out.println("Ingredient " + ingredient + "'s unit '" + unit +"' was already indexed. Ingredient index: " + ingredientIndex);
			} else {
				System.out.println("Ingredient unit type invalid!");
			}
		} catch (IngredientNotIndexedException e) {
			this.getIngredientsList().add(ingredient);
			this.getUnitsList().add(unit);
			
			System.out.println("Ingredient " + ingredient + " and unit '" + unit +"' indexed!");
		}
	}
	
}


/* DOCUMENTATION */
// O zero do ArrayList interno é o ingrediente! Os outros elementos são as possíveis unidades!