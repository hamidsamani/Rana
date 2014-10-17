package info.rayan.service;

import info.rayan.domains.OrderItem;

import java.math.BigDecimal;
import java.util.List;

public class AmountCalculator {

	private static final float DEFAULT_VALUE_ADDED = 1.08f;

	private List<OrderItem> orderItems;
	private BigDecimal total = BigDecimal.ZERO;
	private BigDecimal discount = BigDecimal.ZERO;
	private BigDecimal deposit = BigDecimal.ZERO;

	public AmountCalculator() {
	}

	public AmountCalculator(List<OrderItem> orderItems) {
		this(orderItems, null, null);
	}

	public AmountCalculator(List<OrderItem> orderItems, BigDecimal discount,
			BigDecimal deposit) {
		this.orderItems = orderItems;
		this.discount = discount == null ? BigDecimal.ZERO : discount;
		this.deposit = deposit == null ? BigDecimal.ZERO : deposit;
	}

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

	public BigDecimal calculateAmountWithValueAdded(float value) {
		return calculateAmount().multiply(BigDecimal.valueOf(value));
	}

	public String calculateAmountWithDefaultValueAdded() {
		return formatDecimal(calculateAmountWithValueAdded(DEFAULT_VALUE_ADDED));
	}

	private String formatDecimal(BigDecimal value) {
		return value.toBigInteger().toString();
	}
}
