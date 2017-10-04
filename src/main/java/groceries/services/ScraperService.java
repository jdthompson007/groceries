package groceries.services;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.parser.Parser;
import org.jsoup.select.Elements;

import groceries.domain.Grocery;
import groceries.domain.GroceryResult;

public class ScraperService {

	public GroceryResult getGroceryResult() throws Exception {
		
		GroceryResult groceryResult = new GroceryResult();		
		groceryResult.setGroceries(getGroceries());
		
		return groceryResult;
	}	
	
	public List<Grocery> getGroceries() throws Exception {
		
		String url = "https://jsainsburyplc.github.io/serverside-test/site/www.sainsburys.co.uk/webapp/wcs/stores/servlet/gb/groceries/berries-cherries-currants6039.html";		
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
		
		Document productPageDoc = getProductPageDoc(getProductPageUrl(product));
		grocery.setDescription(getProductDescription(productPageDoc));
		grocery.setCalories(getProductCalories(productPageDoc));
		
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
	
	private Document getProductPageDoc(String url) throws Exception {	
		return Jsoup.connect(url).get();
	}
	
	private String getProductUnitPrice(Element product) {
		Element link = product.select("p.pricePerUnit").first();		
		return link.html().replaceAll("<abbr.*", "").substring(1);		
	}	
	
	private String getProductDescription(Document productPageDoc) {		
		String description = productPageDoc.select("div.productText p").first().html();		
		return Parser.unescapeEntities(description, true);
	}
	
	private Integer getProductCalories(Document productPageDoc) {
		Element element = productPageDoc.select("table.nutritionTable tbody tr td.nutritionLevel1").first();
		if (element == null) {
			return null;
		}		
		String calories = element.html().replaceAll("kcal", "");
		return new Integer(calories);
	}
}

