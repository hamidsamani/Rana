package info.rayan.domains;

import java.math.BigDecimal;

import javax.persistence.Embeddable;

@Embeddable
public class OrderItem {
	private String name;
	private BigDecimal price = BigDecimal.ZERO;
	private String description;

	public OrderItem() {
	}

	public OrderItem(String name, BigDecimal price, String description) {
		this.name = name;
		this.price = price;
		this.description = description;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

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
		return "OrderItem [name=" + name + ", price=" + price
				+ ", description=" + description + "]";
	}

}
