package foodorganizer;

public class Ingredient implements Comparable<Ingredient>{
	private String name;
	private int amount;
	private String unitType;
	
	/* Constructor */
	public Ingredient(String name, int amount, String unitType) {
		this.name = name;
		this.amount = amount;
		this.unitType = unitType;
	}
	
	/* Getters */
	public String getName() {
		return this.name;
	}

	public int getAmount() {
		return this.amount;
	}

	public String getUnitType() {
		return this.unitType;
	}
	
	/* Public Functions */
	public void addAmount(int amount) {
		this.amount += amount;
	}
	
	@Override
	public int compareTo(Ingredient anotherIngredient) {
		if (this.getName().compareToIgnoreCase(anotherIngredient.getName()) < 0) {
			return -1;
		} else if (this.getName().compareToIgnoreCase(anotherIngredient.getName()) > 0) {
			return 1;
		} else {
			if (this.getUnitType().compareToIgnoreCase(anotherIngredient.getUnitType()) < 0) {
				return -1;
			} else if (this.getUnitType().compareToIgnoreCase(anotherIngredient.getUnitType()) > 0) {
				return 1;
			} else {
				return 0;
			}
		}
	}
	
	@Override
	public String toString() {
		return "Ingredient " + name + " - amount: " + amount + " " + unitType;
		
	}
}
