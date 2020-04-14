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
	
	/* Private Function */
	private void readWeeklyRecipesFile(String fileName) {
		FileReader file;
		try {
			file = new FileReader(fileName);
			BufferedReader bFile = new BufferedReader(file);
			
			String line = bFile.readLine();
			
			while (line!= null) {
				this.getRecipes().add(new Recipe(line));
				line = bFile.readLine();
			}
			
			bFile.close();
		} catch (IOException e) {
			if (e instanceof FileNotFoundException) {
				System.out.println("The file '" + fileName +"'does not exist!");
				System.exit(0);
			} else {
				System.out.println("Error reading '" + fileName + "' file");
				System.exit(0);
			}
		}
	}
}
