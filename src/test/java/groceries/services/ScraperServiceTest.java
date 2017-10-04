package groceries.services;

import java.io.File;

import org.junit.Before;
import org.junit.Test;
import org.skyscreamer.jsonassert.JSONAssert;

import com.fasterxml.jackson.databind.ObjectMapper;

import groceries.domain.GroceryResult;

/*
 * This is an integration test that tests the screen is scraped and the output JSON data
 */
public class ScraperServiceTest {

	private ScraperService scraperService;
	private JsonService jsonService;
	
	@Before
	public void setup() {
		scraperService = new ScraperService();
		jsonService = new JsonService();
	}
	
	@Test
	public void testScrapePage() throws Exception {		
		// GIVEN there is a product page to scrape
		// WHEN the screen is scraped
		GroceryResult groceryResult = scraperService.getGroceryResult();
		String actualJson = jsonService.convertGroceryResultToJson(groceryResult);
		
		// THEN the expected JSON will match the actual JSON
		GroceryResult expectedGroceryResult = getExpectedResult();
		String expectedJson = jsonService.convertGroceryResultToJson(expectedGroceryResult);
		
		JSONAssert.assertEquals(expectedJson, actualJson, false);
	}
	
	private GroceryResult getExpectedResult() throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		//JSON from file to Object
		ClassLoader classLoader = getClass().getClassLoader();
		return (GroceryResult) mapper.readValue(new File(classLoader.getResource("expectedProducts.json").getFile()), GroceryResult.class);
	}
}
