package info.rayan.service;

import info.rayan.domains.Invoice;
import info.rayan.domains.OrderItem;
import info.rayan.domains.Payment;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;

public class AmountCalculator {

	private static final BigDecimal DEFAULT_VALUE_ADDED = BigDecimal
			.valueOf(1.08f);

	private List<OrderItem> orderItems;
	private BigInteger total = BigInteger.ZERO;
	private BigInteger discount = BigInteger.ZERO;
	private BigInteger deposit = BigInteger.ZERO;

	public AmountCalculator() {
	}

	public AmountCalculator(List<OrderItem> orderItems) {
		this(orderItems, null, null);
	}

	public AmountCalculator(List<OrderItem> orderItems, BigInteger discount,
			BigInteger deposit) {
		this.orderItems = orderItems;
		this.discount = discount == null ? BigInteger.ZERO : discount;
		this.deposit = deposit == null ? BigInteger.ZERO : deposit;
	}

	public void setServices(List<OrderItem> orderItems) {
		this.orderItems = orderItems;
	}

	public void setDiscount(BigInteger discount) {
		this.discount = discount;
	}

	public void setDeposit(BigInteger deposit) {
		this.deposit = deposit;
	}

	public BigInteger calculateAmount() {
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

	public BigInteger calculateAmountWithValueAdded(BigDecimal value) {
		return BigDecimal.valueOf(calculateAmount().doubleValue())
				.multiply(value).toBigInteger();
	}

	public BigInteger calculateAmountWithDefaultValueAdded() {
		return calculateAmountWithValueAdded(DEFAULT_VALUE_ADDED);
	}

	public static BigInteger calculateAmountWithDefaultValueAdded(
			BigInteger total) {

		return calculateAmountWithValueAdded(total, DEFAULT_VALUE_ADDED);
	}

	public static BigInteger calculateAmountWithValueAdded(BigInteger total,
			BigDecimal valueAdded) {
		return BigDecimal.valueOf(total.doubleValue()).multiply(valueAdded)
				.toBigInteger();
	}

	public static BigInteger remainingAmountWithDefaultValueAdded(
			Invoice invoice) {
		BigInteger totalPayments = BigInteger.ZERO;
		for (Payment payment : invoice.getPayments()) {
			totalPayments = totalPayments.add(payment.getPrice());
		}

		BigInteger totalWithValueAdded = BigDecimal
						.valueOf(invoice.calculatePrice()
						.doubleValue())
						.multiply(DEFAULT_VALUE_ADDED)
						.toBigInteger();

		return totalWithValueAdded.subtract(totalPayments);
	}

}
