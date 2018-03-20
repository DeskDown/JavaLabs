package diet;

/**
 * Represents a complete menu.
 * It consist of packaged products and servings of recipes.
 *
 */
public class Menu implements NutritionalElement {

	/**
	 * Menu constructor.
	 * The reference {@code food} of type {@link Food} must be used to
	 * retrieve the information about recipes and products 
	 * 
	 * @param nome unique name of the menu
	 * @param food object containing the information about products and recipes
	 */
	public Menu(String name, Food food){
	}
	
	/**
	 * Adds a given serving size of a recipe.
	 * The recipe is a name of a recipe defined in the {@code food}
	 * argument of the constructor.
	 * 
	 * @param recipe the name of the recipe to be used as ingredient
	 * @param quantity the amount in grams of the recipe to be used
	 */
	public void addRecipe(String recipe, double quantity) {
	}

	/**
	 * Adds a unit of a packaged product.
	 * The product is a name of a product defined in the {@code food}
	 * argument of the constructor.
	 * 
	 * @param product the name of the product to be used as ingredient
	 */
	public void addProduct(String product) {
	}

	public String getName() {
		return null;
	}

	public double getCalories() {
		return 0.0;
	}

	public double getProteins() {
		return 0.0;
	}

	public double getCarbs() {
		return 0.0;
	}

	public double getFat() {
		return 0.0;
	}

	public boolean per100g() {
		// nutritional values are provided for the whole menu.
		return false;
	}
}
