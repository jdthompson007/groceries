package pages;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.parser.Parser;

public class ProductPage {

	private Document productDocument;
	
	public ProductPage(String url) throws Exception {
		productDocument = getProductPageDoc(url);
	}
	
	private Document getProductPageDoc(String url) throws Exception {	
		return Jsoup.connect(url).get();
	}
	
	public String getProductDescription() {		
		String description = productDocument.select("div.productText p").first().html();		
		return Parser.unescapeEntities(description, true);
	}
	
	public Integer getProductCalories() {
		Element element = productDocument.select("table.nutritionTable tbody tr td.nutritionLevel1").first();
		if (element == null) {
			return null;
		}		
		String calories = element.html().replaceAll("kcal", "");
		return new Integer(calories);
	}	
}
