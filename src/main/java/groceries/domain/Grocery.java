package groceries.domain;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Grocery {

	private String title;
	private Integer calories;	// kcal per 100g
	private BigDecimal price;
	private String description;

	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	@JsonInclude(JsonInclude.Include.NON_NULL)
	@JsonProperty("kcal_per_100g")
	public Integer getCalories() {
		return calories;
	}
	
	public void setCalories(Integer calories) {
		this.calories = calories;
	}
	
	@JsonProperty("unit_price")
	public BigDecimal getPrice() {
		return price;
	}
	
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	@Override
	public String toString() {
		return "title: " + getTitle() + ", price: " + getPrice() + ", description: " + getDescription() + 
			", calories: " + getCalories(); 
	}
}
