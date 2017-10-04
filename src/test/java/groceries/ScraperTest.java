package groceries;

import java.io.File;

import org.junit.Before;
import org.junit.Test;
import org.skyscreamer.jsonassert.JSONAssert;

import com.fasterxml.jackson.databind.ObjectMapper;

import groceries.domain.GroceryResult;
import groceries.services.JsonService;
import groceries.services.ScraperService;

public class ScraperTest {

	private Scraper scraper;

	private JsonService jsonService;
	
	@Before
	public void setup() {
		jsonService = new JsonService();
		scraper = new Scraper(new ScraperService(), jsonService);
	}
	
	@Test
	public void testScraper() throws Exception {		
		// GIVEN there is a product page to scrape
		
		// WHEN the screen is scraped
		String actualJson = scraper.run();
		
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
