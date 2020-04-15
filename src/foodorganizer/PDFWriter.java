package foodorganizer;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Iterator;

import org.apache.pdfbox.pdmodel.font.PDType1Font;

import rst.pdfbox.layout.elements.Document;
import rst.pdfbox.layout.elements.Paragraph;
import rst.pdfbox.layout.text.Alignment;

public abstract class PDFWriter {
	
	public static void write(String ingredientsList, RecipesManager rManager) throws IOException {
		Document document = new Document(40, 60, 40, 60);
		
		writeIngredientsList(document, ingredientsList);
		skipLine(document, 20, 1);
		writeRecipes(document, rManager);
		
		final OutputStream outputStream = new FileOutputStream("teste.pdf");
		document.save(outputStream);
	}
	
	/* Private Functions */
	private static void writeIngredientsList(Document document, String ingredientsList) throws IOException {
		/* Título */
		Paragraph title = new Paragraph();
		title.addText("Ingredientes Semanais Necessários:", 20, PDType1Font.HELVETICA_BOLD);
		title.setAlignment(Alignment.Center);
		document.add(title);
		
		ingredientsList = StringManager.reduceString(ingredientsList, 23, ingredientsList.length() - 1);
		Paragraph ingredients = new Paragraph();
		ingredients.addText(ingredientsList, 12, PDType1Font.HELVETICA);
		ingredients.setAlignment(Alignment.Center);
		document.add(ingredients);
	}
	
	private static void skipLine(Document document, int size, int lines) throws IOException {
		for (int i = 0; i < lines; i++) {
			Paragraph emptyLine = new Paragraph();
			emptyLine.addText(" ", size, PDType1Font.HELVETICA_BOLD);
			document.add(emptyLine);
		}
	}
	
	private static void writeRecipes(Document document, RecipesManager rManager) throws IOException {
		Paragraph title = new Paragraph();
		title.addText("Receitas da Semana", 20, PDType1Font.HELVETICA_BOLD);
		title.setAlignment(Alignment.Center);
		document.add(title);
		
		Iterator<Recipe> recipeIterator = rManager.getRecipes().iterator();
		while (recipeIterator.hasNext()) {
			Recipe currentRecipe = recipeIterator.next();
			
			/* Recipe Title */
			Paragraph recipeTitle = new Paragraph();
			recipeTitle.addText(currentRecipe.getTitle(), 18, PDType1Font.HELVETICA);
			recipeTitle.setAlignment(Alignment.Center);
			document.add(recipeTitle);
			
			skipLine(document, 12, 1);
			
			/* Ingredients Title */
			Paragraph ingredientsTitle = new Paragraph();
			ingredientsTitle.addText("Ingredientes:", 16, PDType1Font.HELVETICA_OBLIQUE);
			document.add(ingredientsTitle);
			
			/* Ingredients Body */
			Iterator<Ingredient> ingredientsIterator = currentRecipe.getIngredients().iterator();
			while (ingredientsIterator.hasNext()) {
				Ingredient currentIngredient = ingredientsIterator.next();
				
				Paragraph ingredients = new Paragraph();
				
				if (currentIngredient.getUnitType().contentEquals("a gosto")) {
					ingredients.addText(currentIngredient.getName() + " - " + currentIngredient.getUnitType(), 12, PDType1Font.HELVETICA);
				} else {
					ingredients.addText(currentIngredient.getName() + " - " + currentIngredient.getAmount() + " " + currentIngredient.getUnitType(), 12, PDType1Font.HELVETICA);
				}
				document.add(ingredients);
			}
			
			skipLine(document, 12, 1);
			
			/* Preparation Steps Title */
			Paragraph preparationStepsTitle = new Paragraph();
			preparationStepsTitle.addText("Modo de Preparo:", 16, PDType1Font.HELVETICA_OBLIQUE);
			document.add(preparationStepsTitle);
			
			/* Preparation Steps Body */
			Iterator<String> preparationIterator = currentRecipe.getPreparationSteps().iterator();
			while (preparationIterator.hasNext()) {
				String currentPrepatationStep = preparationIterator.next();
				
				Paragraph ingredients = new Paragraph();
				ingredients.addText(currentPrepatationStep, 12, PDType1Font.HELVETICA);
				document.add(ingredients);
			}
			
			skipLine(document, 12, 1);
		}

	}
	
}
