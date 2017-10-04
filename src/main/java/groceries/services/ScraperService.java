package groceries.services;

import groceries.domain.GroceryResult;
import pages.ProductListPage;

public class ScraperService {

	public GroceryResult getGroceryResult() throws Exception {
		
		GroceryResult groceryResult = new GroceryResult();		

		String url = "https://jsainsburyplc.github.io/serverside-test/site/www.sainsburys.co.uk/webapp/wcs/stores/servlet/gb/groceries/berries-cherries-currants6039.html";
		ProductListPage productListPage = new ProductListPage(url);
		
		groceryResult.setGroceries(productListPage.getGroceries());
		
		return groceryResult;
	}	
	
}

