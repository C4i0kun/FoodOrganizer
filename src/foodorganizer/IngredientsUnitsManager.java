package foodorganizer;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

public class IngredientsUnitsManager {
	ArrayList<ArrayList <String>> ingredientsUnits;
	
	/* Constructor */
	public IngredientsUnitsManager() {
		ingredientsUnits = new ArrayList<ArrayList <String>>();
		
		readIngredientsUnitsFile();
	}
	
	/* Getters */
	public ArrayList<ArrayList<String>> getIngredientsUnits() {
		return ingredientsUnits;
	}
	
	
	/* Public Functions */
	//REMAKE THIS FUNCTION
	public boolean ingredientIndexed(String ingredient) {
		Iterator<ArrayList <String>> iterator = this.getIngredientsUnits().iterator();
		
		while (iterator.hasNext()) {
			if (iterator.next().get(0) == ingredient) {
				return true;
			}
		}
		
		return false;
	}
	
	//REMAKE THIS FUNCTION
	public boolean unitIndexed(String ingredient, String unit) {
		Iterator<ArrayList <String>> iterator = this.getIngredientsUnits().iterator();
		
		while (iterator.hasNext()) {
			if (iterator.next().get(0) == ingredient) {
				Iterator<String> unitIterator = iterator.next().iterator();
				
				while (unitIterator.hasNext()) {
					if (unitIterator.next() == unit) {
						return true;
					}
				}
			}
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
		
	}
	
}


/* DOCUMENTATION */
// O zero do ArrayList 