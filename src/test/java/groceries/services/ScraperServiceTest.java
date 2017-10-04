package groceries.services;

import org.junit.Before;
import org.junit.Test;

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
		// TODO complete		
		GroceryResult groceryResult = scraperService.getGroceryResult();
		String json = jsonService.convertGroceryResultToJson(groceryResult);
		
		System.out.println("RESULT\n" + json);
	}
}
