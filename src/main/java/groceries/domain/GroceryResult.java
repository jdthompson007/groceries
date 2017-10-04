package groceries.domain;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class GroceryResult {

	private List<Grocery> results = new ArrayList<>();	
	
	public List<Grocery> getResults() {
		return results;
	}
	
	public void setGroceries(List<Grocery> groceries) {
		this.results = groceries;
	}
	
	public BigDecimal getTotal() {
		
		BigDecimal total = new BigDecimal(0);
		
		for (Grocery grocery: results) {
			total = total.add(grocery.getPrice());
		}
		
		return total;
	}
}
