package groceries;

import groceries.domain.GroceryResult;
import groceries.services.JsonService;
import groceries.services.ScraperService;

public class Scraper {

	public static void main(String[] args) {
		
		ScraperService scraperService = new ScraperService();	
		JsonService jsonService = new JsonService();
		
		try {
			GroceryResult groceryResult = scraperService.getGroceryResult();
			String json = jsonService.convertGroceryResultToJson(groceryResult);
			
			System.out.println(json);
		
		} catch (Throwable t) {
			System.out.println("An error has occurred: " + t.getMessage());
			t.printStackTrace();
		}		
	}		
}
