package groceries.services;

import com.fasterxml.jackson.databind.ObjectMapper;

import groceries.domain.GroceryResult;

public class JsonService {

	public String convertGroceryResultToJson(GroceryResult groceryResult) throws Exception {				
		ObjectMapper mapper = new ObjectMapper();
		return mapper.writeValueAsString(groceryResult);
	}
	
}
