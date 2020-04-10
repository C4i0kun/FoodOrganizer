package foodorganizer;

public class Ingredient implements Comparable<Ingredient>{
	private String name;
	private int amount;
	private String unitType;
	
	public Ingredient(String name, int amount, String unitType) {
		this.name = name;
		this.amount = amount;
		this.unitType = unitType;
	}
	
	/* Getters */
	public String getName() {
		return name;
	}

	public int getAmount() {
		return amount;
	}

	public String getUnitType() {
		return unitType;
	}
	
	/* Other Public Functions */
	@Override
	public int compareTo(Ingredient anotherIngredient) {
		if (this.getName().compareTo(anotherIngredient.getName()) == -1) {
			return -1;
		} else if (this.getName().compareTo(anotherIngredient.getName()) == 1) {
			return 1;
		} else {
			if (this.getUnitType().compareTo(anotherIngredient.getUnitType()) == -1) {
				return -1;
			} else if (this.getUnitType().compareTo(anotherIngredient.getUnitType()) == 1) {
				return 1;
			} else {
				return 0;
			}
		}
	}
}
