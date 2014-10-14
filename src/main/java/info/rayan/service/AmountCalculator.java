package info.rayan.service;

import info.rayan.domains.OrderItem;

import java.math.BigDecimal;
import java.util.List;

public class AmountCalculator {
	private List<OrderItem> orderItems;
	private BigDecimal total = BigDecimal.ZERO;
	private BigDecimal discount = BigDecimal.ZERO;
	private BigDecimal deposit = BigDecimal.ZERO;

	public void setServices(List<OrderItem> orderItems) {
		this.orderItems = orderItems;
	}

	public void setDiscount(BigDecimal discount) {
		this.discount = discount;
	}

	public void setDeposit(BigDecimal deposit) {
		this.deposit = deposit;
	}

	public BigDecimal calculateAmount() {
		for (OrderItem service : orderItems) {
			total = total.add(service.getPrice());
		}
		if (!discount.equals(BigDecimal.ZERO)) {
			total = total.subtract(discount);
		}
		if (!deposit.equals(BigDecimal.ZERO)) {
			total = total.subtract(deposit);
		}
		return total;
	}
}
