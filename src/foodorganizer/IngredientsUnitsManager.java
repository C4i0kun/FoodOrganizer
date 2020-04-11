package foodorganizer;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

public class IngredientsUnitsManager {
	private ArrayList<String> ingredientsList;
	private ArrayList<String> unitsList;
	
	/* Constructors */
	public IngredientsUnitsManager() {
		initializeLists();
		
		readIngredientsUnitsFile(false);
	}
	
	public IngredientsUnitsManager(boolean test) {
		initializeLists();
		
		readIngredientsUnitsFile(test);
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
			if (iterator.next().contentEquals(ingredient)) {
				return index;
			}
			index++;
		}
		
		throw new IngredientNotIndexedException();
	}
	
	public boolean unitIndexed(String ingredient, String unit) throws IngredientNotIndexedException {
		int ingredientIndex = ingredientIndex(ingredient);
		
		if (unitsList.get(ingredientIndex).contentEquals(unit)) {
			return true;
		}
		
		return false;
		
	}

	/* Private functions */
	private void initializeLists() {
		ingredientsList = new ArrayList<String>();
		unitsList = new ArrayList<String>();
	}
	
	private void readIngredientsUnitsFile(boolean test) {
		try {
			
			FileReader file;
			
			if (test == true) {
				file = new FileReader("IngredientsUnitsTest.txt");
			} else {
				file = new FileReader("IngredientsUnits.txt");
			}
			
			BufferedReader bFile = new BufferedReader(file);
			
			String line = bFile.readLine();
			
			while (line != null) {
				String ingredient = identifyIngredient(line);
				String unitType = identifyUnitType(line);
				
				this.addToIngredientsUnitsArray(ingredient, unitType);
				
				line = bFile.readLine();
			}
			
			bFile.close();
			
		} catch (IOException e) {
			if (e instanceof FileNotFoundException) {
				if (test == true) {
					System.out.println("The 'IngredientsUnitsTest.txt' does not exist!");
					System.exit(0);
				} else {
					System.out.println("The 'IngredientsUnits.txt' does not exist!");
					System.exit(0);
				}
			} else {
				if (test == true) {
					System.out.println("Error reading 'IngredientsUnitsTest.txt' file!");
					System.exit(0);
				} else {
					System.out.println("Error reading 'IngredientsUnits.txt' file!");
					System.exit(0);
				}
			}
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
	
	public void addToIngredientsUnitsArray(String ingredient, String unit) {
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