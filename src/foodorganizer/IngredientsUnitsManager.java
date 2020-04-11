package foodorganizer;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class IngredientsUnitsManager {
	ArrayList<ArrayList <String>> ingredientsUnits;
	
	public IngredientsUnitsManager() {
		ingredientsUnits = new ArrayList<ArrayList <String>>();
		
		readIngredientsUnitsFile();
	}
	
	private void readIngredientsUnitsFile() {
		try {
			FileReader file = new FileReader("IngredientsUnits.txt");
			BufferedReader bFile = new BufferedReader(file);
			
			String line = bFile.readLine();
			
			while (line != null) {
				addToIngredientsUnitsList(line);
				
				line = bFile.readLine();
			}
			
		} catch (IOException e) {
			if (e instanceof FileNotFoundException) {
				System.out.println("The 'IngredientsUnits.txt' does not exist!");
			} else {
				System.out.println("Error reading 'IngredientsUnits.txt' file!");
			}
			e.printStackTrace();
		}
	}
	
	private void addToIngredientsUnitsList(String lineRead) {
		String ingredient;
		String unitType;
		
		boolean readingIngredient = true;
		boolean readingUnitType = false;
		
		for (int i = 0; i < lineRead.length(); i++) {
			

		}
	}
	
}


/* DOCUMENTATION */
// O zero do ArrayList 