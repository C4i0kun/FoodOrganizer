package foodorganizer;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public abstract class StandardIngredientsReader {
	
	/* Private functions */
	public static void readStandardIngredientsFile(IngredientsManager ingredientsManager, String fileName) {
		try {
			
			FileReader file = new FileReader(fileName);
			
			BufferedReader bFile = new BufferedReader(file);
			
			String line = bFile.readLine();
			
			while (line != null) {
				String ingredient = identifyIngredient(line);
				String unitType = identifyUnitType(line);
				
				ingredientsManager.addIngredientAmount(ingredient, 0, unitType);
				
				line = bFile.readLine();
			}
			
			bFile.close();
			
		} catch (IOException e) {
			if (e instanceof FileNotFoundException) {
				System.out.println("The file '" + fileName +"'does not exist!");
			} else {
				System.out.println("Error reading '" + fileName + "' file");
			}
		}
	}
	
	private static String identifyIngredient(String lineRead) {
		int separatorIndex = StringManager.findIndexOfChar(lineRead, '-');
		return StringManager.reduceString(lineRead, 0, separatorIndex - 2);
	}
	
	private static String identifyUnitType(String lineRead) {
		int separatorIndex = StringManager.findIndexOfChar(lineRead, '-');
		return StringManager.reduceString(lineRead, separatorIndex + 2, lineRead.length() - 1);
	}
	
}