package pages;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.equalTo;

public class ProductPageTest {

	@Test
	public void testProductPage() throws Exception {		
		// GIVEN we have a product page
		String url = "https://jsainsburyplc.github.io/serverside-test/site/www.sainsburys.co.uk/shop/gb/groceries/berries-cherries-currants/sainsburys-british-strawberries-400g.html";
		
		// WHEN the product page is scraped
		ProductPage productPage = new ProductPage(url);
		
		// THEN the correct values will have been taken
		assertThat(productPage.getDescription(), equalTo("by Sainsbury's strawberries"));
		assertThat(productPage.getCalories(), equalTo(33));
	}
}
