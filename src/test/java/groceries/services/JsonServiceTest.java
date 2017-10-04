package groceries.services;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.skyscreamer.jsonassert.JSONAssert;

import groceries.domain.Grocery;
import groceries.domain.GroceryResult;

public class JsonServiceTest {

	private JsonService jsonService;
	
	@Before
	public void setup() {
		jsonService = new JsonService();
	}
	
	@Test
	public void testConvertGroceriesToJson() throws Exception {
		// GIVEN we have two grocery items
		List<Grocery> groceries = new ArrayList<Grocery>();
		
		Grocery grocery = new Grocery();
		grocery.setTitle("Strawberry");
		grocery.setCalories(200);
		grocery.setPrice(new BigDecimal(1.50));
		grocery.setDescription("As found at wimbledon");
		groceries.add(grocery);

		grocery = new Grocery();
		grocery.setTitle("Blueberry");
		grocery.setCalories(100);
		grocery.setPrice(new BigDecimal(2.50));
		grocery.setDescription("berries");
		groceries.add(grocery);
		
		GroceryResult groceryResult = new GroceryResult();
		groceryResult.setGroceries(groceries);
		
		// WHEN the grocery items are converted to JSON
		String json = jsonService.convertGroceryResultToJson(groceryResult);
		
		// THEN the JSON result has the correct items with the correct total value
		JSONAssert.assertEquals("{\"results\":[{\"title\":\"Strawberry\",\"description\":\"As found at wimbledon\",\"kcal_per_100g\":200,\"unit_price\":1.5},{\"title\":\"Blueberry\",\"description\":\"berries\",\"kcal_per_100g\":100,\"unit_price\":2.5}],\"total\":4.0}", json, false);
	}	
	
	@Test
	public void testConvertGroceriesToJsonMissingCalories() throws Exception {
		// GIVEN we have a grocery item with no calorie value
		List<Grocery> groceries = new ArrayList<Grocery>();
		
		Grocery grocery = new Grocery();
		grocery.setTitle("Strawberry");
		grocery.setPrice(new BigDecimal(1.50));
		grocery.setDescription("As found at wimbledon");
		groceries.add(grocery);
		
		GroceryResult groceryResult = new GroceryResult();
		groceryResult.setGroceries(groceries);
		
		// WHEN the grocery item is converted to JSON
		String json = jsonService.convertGroceryResultToJson(groceryResult);
		
		// THEN the JSON result has the correct item with the correct total value
		JSONAssert.assertEquals("{\"results\":[{\"title\":\"Strawberry\",\"description\":\"As found at wimbledon\",\"unit_price\":1.5}],\"total\":1.50}", json, false);
	}	
}
