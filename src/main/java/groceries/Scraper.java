package groceries;

import groceries.domain.GroceryResult;
import groceries.services.JsonService;
import groceries.services.ScraperService;

public class Scraper {

	private ScraperService scraperService;	
	private JsonService jsonService;
		
	public static void main(String[] args) {
		Scraper scraper = new Scraper(new ScraperService(), new JsonService());		
		String json = scraper.run();
		System.out.println(json);
	}		
	
	public Scraper(ScraperService scraperService, JsonService jsonService) {
		this.scraperService = scraperService;
		this.jsonService = jsonService;
	}
	
	public String run() {
		try {
			GroceryResult groceryResult = scraperService.getGroceryResult();
			return jsonService.convertGroceryResultToJson(groceryResult);
		} catch (Throwable t) {
			System.out.println("An error has occurred: " + t.getMessage());
			t.printStackTrace();
		}		
		
		return "";
	}
}
