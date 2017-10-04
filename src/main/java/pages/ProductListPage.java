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
		
		// System.out.println("HTML\n\n" + doc.html());
		
		Elements products = doc.select("div.product");
		
		List<Grocery> groceries = new ArrayList<>();
		// System.out.println("PRODUCTS");
		for (Element product: products) {			
			// System.out.println(product.html());
			Grocery grocery = getGrocery(product);
			groceries.add(grocery);
		}

		// TODO remove
//		for (Grocery grocery: groceries) {
//			System.out.println(grocery.toString());			
//		}
		
		return groceries;
	}
	
	private Grocery getGrocery(Element product) throws Exception {
		
		Grocery grocery = new Grocery();		
		grocery.setTitle(getProductTitle(product));
		grocery.setPrice(new BigDecimal(getProductUnitPrice(product)));
		
		ProductPage productPage = new ProductPage(getProductPageUrl(product));
		grocery.setDescription(productPage.getProductDescription());
		grocery.setCalories(productPage.getProductCalories());
		
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
