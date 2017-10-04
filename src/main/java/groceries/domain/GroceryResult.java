package groceries.domain;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class GroceryResult {

	private List<Grocery> results = new ArrayList<>();	
	private BigDecimal total;
	
	public List<Grocery> getResults() {
		return results;
	}
	
	public void setGroceries(List<Grocery> groceries) {
		this.results = groceries;
	}
	
	public BigDecimal getTotal() {
		
		if (total != null) {
			return total;
		}
		
		total = new BigDecimal(0);
		
		for (Grocery grocery: results) {
			total = total.add(grocery.getPrice());
		}
		
		return total;
	}
	
	public void setTotal(BigDecimal total) {
		this.total = total;
	}
}
