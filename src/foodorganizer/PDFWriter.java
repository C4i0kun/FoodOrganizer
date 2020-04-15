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
		try {
			Document document = new Document(40, 60, 40, 60);
			
			writeRecipes(document, rManager);
			skipLine(document, 20, 1);
			writeIngredientsList(document, ingredientsList);
			
			final OutputStream outputStream = new FileOutputStream("teste.pdf");
			document.save(outputStream);
		} catch (NullPointerException e) {
			System.out.println("Error generating PDF.");
		}
	}
	
	/* Private Functions */
	private static void writeIngredientsList(Document document, String ingredientsList) throws IOException {
		/* Título */
		Paragraph title = new Paragraph();
		title.addText("Ingredientes Necessários", 24, PDType1Font.COURIER_BOLD);
		title.setAlignment(Alignment.Center);
		document.add(title);
		
		Paragraph ingredients = new Paragraph();
		ingredients.addText(ingredientsList, 12, PDType1Font.COURIER);
		//ingredients.setAlignment(Alignment.Center);
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
		title.addText("Receitas da Semana", 24, PDType1Font.COURIER_BOLD);
		title.setAlignment(Alignment.Center);
		document.add(title);
		
		Iterator<Recipe> recipeIterator = rManager.getRecipes().iterator();
		while (recipeIterator.hasNext()) {
			Recipe currentRecipe = recipeIterator.next();
			
			/* Recipe Title */
			Paragraph recipeTitle = new Paragraph();
			recipeTitle.addText(currentRecipe.getTitle(), 18, PDType1Font.COURIER_BOLD);
			recipeTitle.setAlignment(Alignment.Center);
			document.add(recipeTitle);
			
			skipLine(document, 12, 1);
			
			/* Ingredients Title */
			Paragraph ingredientsTitle = new Paragraph();
			ingredientsTitle.addText("Ingredientes:", 16, PDType1Font.COURIER_BOLD_OBLIQUE);
			document.add(ingredientsTitle);
			
			/* Ingredients Body */
			Iterator<Ingredient> ingredientsIterator = currentRecipe.getIngredients().iterator();
			while (ingredientsIterator.hasNext()) {
				Ingredient currentIngredient = ingredientsIterator.next();
				
				Paragraph ingredients = new Paragraph();
				
				
				if (currentIngredient.getUnitType().contentEquals("a gosto")) {
					ingredients.addText("-> " + currentIngredient.getName() + " - " + currentIngredient.getUnitType(), 12, PDType1Font.COURIER);
				} else {
					ingredients.addText("-> " + currentIngredient.getName() + " - " + currentIngredient.getAmount() + " " + currentIngredient.getUnitType(), 12, PDType1Font.COURIER);
				}
				document.add(ingredients);
			}
			
			skipLine(document, 12, 1);
			
			/* Preparation Steps Title */
			Paragraph preparationStepsTitle = new Paragraph();
			preparationStepsTitle.addText("Modo de Preparo:", 16, PDType1Font.COURIER_BOLD_OBLIQUE);
			document.add(preparationStepsTitle);
			
			/* Preparation Steps Body */
			Iterator<String> preparationIterator = currentRecipe.getPreparationSteps().iterator();
			int iteratorIndex = 1;
			while (preparationIterator.hasNext()) {
				String currentPrepatationStep = preparationIterator.next();
				
				Paragraph ingredients = new Paragraph();
				ingredients.addText(iteratorIndex + ". " + currentPrepatationStep, 12, PDType1Font.COURIER);
				document.add(ingredients);
				iteratorIndex++;
			}
			
			skipLine(document, 12, 1);
		}

	}
	
}
