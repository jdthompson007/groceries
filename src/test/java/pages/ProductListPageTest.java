package pages;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

import java.math.BigDecimal;

import org.junit.Test;

import groceries.domain.Grocery;

public class ProductListPageTest {
	
	@Test
	public void testProductListPage() throws Exception {	
		// GIVEN we wish to scrape the product list page
		String url = "https://jsainsburyplc.github.io/serverside-test/site/www.sainsburys.co.uk/webapp/wcs/stores/servlet/gb/groceries/berries-cherries-currants6039.html";
		ProductListPage productListPage = new ProductListPage(url);
		
		// WHEN the product list page is scraped
		Grocery grocery = productListPage.getGroceries().get(0);
				
		// THEN the grocery item has the correct values
		assertThat(grocery.getTitle(), equalTo("Sainsbury's Strawberries 400g"));
		assertThat(grocery.getPrice(), equalTo(new BigDecimal(1.75)));
		assertThat(grocery.getDescription(), equalTo("by Sainsbury's strawberries"));
		assertThat(grocery.getCalories(), equalTo(33));
	}
}
