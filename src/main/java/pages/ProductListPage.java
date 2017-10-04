package pages;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.parser.Parser;
import org.jsoup.select.Elements;

import groceries.domain.Grocery;

public class ProductListPage {

	private String url;
	
	public ProductListPage(String url) {
		this.url = url;
	}
	
	public List<Grocery> getGroceries() throws Exception {
		
		Document doc = Jsoup.connect(url).get();
		Elements products = doc.select("div.product");
		
		List<Grocery> groceries = new ArrayList<>();
		for (Element product: products) {			
			Grocery grocery = getGrocery(product);
			groceries.add(grocery);
		}
		
		return groceries;
	}
	
	private Grocery getGrocery(Element product) throws Exception {
		
		Grocery grocery = new Grocery();		
		grocery.setTitle(getProductTitle(product));
		grocery.setPrice(new BigDecimal(getProductUnitPrice(product)));
		
		ProductPage productPage = new ProductPage(getProductPageUrl(product));
		grocery.setDescription(productPage.getDescription());
		grocery.setCalories(productPage.getCalories());
		
		return grocery;
	}
	
	private String getProductTitle(Element product) {
		Element link = product.select("div.productInfo").select("div.productNameAndPromotions").select("a").first();		
		String title =  link.html().replaceAll("<img.*", "").trim();		
		return Parser.unescapeEntities(title, true);
	}
	
	private String getProductPageUrl(Element product) {
		Element link = product.select("div.productInfo").select("div.productNameAndPromotions").select("a").first();
		return link.attr("abs:href");
	}
	
	private String getProductUnitPrice(Element product) {
		Element link = product.select("p.pricePerUnit").first();		
		return link.html().replaceAll("<abbr.*", "").substring(1);		
	}	
}
