package foodorganizer;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class RecipesManager {
	ArrayList<Recipe> recipes;
	
	/* Constructor */
	public RecipesManager(String weeklyRecipes) {
		recipes = new ArrayList<>();
		
		readWeeklyRecipesFile(weeklyRecipes);
	}
	
	/* Getters */
	public ArrayList<Recipe> getRecipes() {
		return recipes;
	}
	
	/* Private Functions */
	private void readWeeklyRecipesFile(String fileName) {
		FileReader file;
		try {
			file = new FileReader(fileName);
			BufferedReader bFile = new BufferedReader(file);
			
			String line = bFile.readLine();
			
			while (line!= null) {
				
				try {
					if (!StringManager.reduceString(line, line.length() - 4, line.length() - 1).contentEquals(".txt")) {
						throw new FileNotTXTException(fileName, line);
					}
					
					try {
						this.getRecipes().add(new Recipe("Recipes/" + line));
					} catch (IOException e1) {
						if (e1 instanceof FileNotFoundException) {
							System.out.println("The file '" + line +"' does not exist!");
							System.out.println("Ignoring '" + line + "'...");
						} else {
							System.out.println("Error reading '" + line + "' file");
						}
					}
						
				} catch (FileNotTXTException e) {
					System.out.println("Ignoring '" + line + "'...");
				}
				line = bFile.readLine();
			}
			
			bFile.close();
		} catch (IOException e) {
			if (e instanceof FileNotFoundException) {
				System.out.println("The file '" + fileName +"' does not exist!");
				System.exit(0);
			} else {
				System.out.println("Error reading '" + fileName + "' file");
				System.exit(0);
			}
		}
	}
}
