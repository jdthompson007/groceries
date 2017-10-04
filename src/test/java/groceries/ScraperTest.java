package groceries;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;

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
	
	@Test
	public void testScraperExceptionHandling() throws Exception {
		// GIVEN there is a problem with the products page (web site down)
		JsonService mockJsonService = mock(JsonService.class);
		ScraperService mockScraperService = mock(ScraperService.class);
		
		Scraper scraper = new Scraper(mockScraperService, mockJsonService);
		
		Exception mockException = mock(Exception.class);	
		when(mockException.getMessage()).thenReturn("Oh no!");
		when(mockScraperService.getGroceryResult()).thenThrow(mockException);
		
		// WHEN the scraper is run
		scraper.run();
			
		// THEN the stack trace will be printed
		verify(mockException).printStackTrace();
	}
	
	private GroceryResult getExpectedResult() throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		//JSON from file to Object
		ClassLoader classLoader = getClass().getClassLoader();
		return (GroceryResult) mapper.readValue(new File(classLoader.getResource("expectedProducts.json").getFile()), GroceryResult.class);
	}	
}
