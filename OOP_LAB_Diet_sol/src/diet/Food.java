package diet;

import java.util.Collection;
import java.util.TreeMap;


public class Food {

	private TreeMap<String, NutritionalElement> rawMaterials = new TreeMap<String, NutritionalElement>();
	private TreeMap<String, NutritionalElement> products = new TreeMap<String, NutritionalElement>();
	private TreeMap<String, NutritionalElement> recipes = new TreeMap<String, NutritionalElement>();

	public void defineRawMaterial(String name, double calories, double proteins, double carbs, double fat) {
		RawMaterial mp = new RawMaterial(name, calories, proteins, carbs, fat);
		rawMaterials.put(name, mp);
	}

	public Collection<NutritionalElement> rawMaterials() {
		return rawMaterials.values();
	}

	public NutritionalElement getRawMaterial(String name) {
		return (NutritionalElement) rawMaterials.get(name);
	}

	public void defineProduct(String name, double calories, double proteins, double carbs, double fat) {
		Product mp = new Product(name, calories, proteins, carbs, fat);
		products.put(name, mp);
	}

	public Collection<NutritionalElement> products() {
		return products.values();
	}

	public NutritionalElement getProduct(String name) {
		return (NutritionalElement) products.get(name);
	}

	public Collection<NutritionalElement> recipes() {
		return recipes.values();
	}

	public NutritionalElement getRecipe(String name) {
		return (NutritionalElement) recipes.get(name);
	}

	// used by class Recipe to add itself to this Food container
	void addRecipe(Recipe recipe) {
		recipes.put(recipe.getName(), recipe);
	}

}
