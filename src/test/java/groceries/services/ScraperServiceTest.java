package groceries.services;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.Before;
import org.junit.Test;

import groceries.domain.GroceryResult;

/*
 * This is an integration test that tests the screen is scraped and the output JSON data
 */
public class ScraperServiceTest {

	private ScraperService scraperService;
	
	@Before
	public void setup() {
		scraperService = new ScraperService();
	}
	
	@Test
	public void testScrapePage() throws Exception {		
		// GIVEN there is a product page to scrape
		// WHEN the screen is scraped
		GroceryResult groceryResult = scraperService.getGroceryResult();
		
		// THEN the correct grocery result will be returned
		assertThat(groceryResult.getResults().size() > 0, equalTo(true));
		assertThat(groceryResult.getTotal().toString(), equalTo("39.50"));		
	}
}
