package groceries.domain;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.equalTo;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class GroceryResultTest {

	private GroceryResult groceryResult;
	
	@Before
	public void setup() {
		groceryResult = new GroceryResult();
	}
	
	@Test
	public void testGetTotal() {
		// GIVEN there are two grocery items 
		List<Grocery> groceries = new ArrayList<>();

		Grocery grocery = new Grocery();		
		grocery.setPrice(new BigDecimal(1.50));
		groceries.add(grocery);
		
		grocery = new Grocery();		
		grocery.setPrice(new BigDecimal(2.00));
		groceries.add(grocery);
		
		groceryResult.setGroceries(groceries);

		// WHEN the total is calculated
		BigDecimal total = groceryResult.getTotal(); 
		// THEN the total is the correct value
		assertThat(total, equalTo(new BigDecimal(3.50)));
	}	
}
