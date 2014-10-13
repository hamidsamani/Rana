package info.rayan.domains;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Invoice {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private long id;

	@Embedded
	private Customer customer;

	@ElementCollection(fetch = FetchType.EAGER)
	private List<OrderItem> orderItems = new ArrayList<>();

	@Temporal(value = TemporalType.TIMESTAMP)
	private Date createdDate;

	public Invoice() {
	}

	public Invoice(InvoiceBuilder builder) {
		this.customer = builder.customer;
		this.orderItems = builder.orderItems;
		this.createdDate = builder.createdDate;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public List<OrderItem> getOrderItems() {
		return orderItems;
	}

	public void setOrderItems(List<OrderItem> orderItems) {
		this.orderItems = orderItems;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	@Override
	public String toString() {
		return "Invoice [id=" + id + ", customer=" + customer + ", orderItems="
				+ orderItems + "]";
	}

	public BigDecimal calculatePrice() {
		BigDecimal total = BigDecimal.ZERO;
		for (OrderItem orderItem : orderItems) {
			total = total.add(orderItem.getPrice());
		}
		return total;
	}

	public static class InvoiceBuilder {
		private Customer customer = new Customer();
		private Date createdDate = new Date();
		private List<OrderItem> orderItems;

		public static InvoiceBuilder create() {
			return new InvoiceBuilder();
		}

		public InvoiceBuilder customerName(String name) {
			this.customer.setName(name);
			return this;
		}

		public InvoiceBuilder customerTell(String tell) {
			this.customer.setTell(tell);
			return this;
		}

		public InvoiceBuilder orderItems(List<OrderItem> orderItems) {
			this.orderItems = orderItems;
			return this;
		}

		public InvoiceBuilder createdDate(Date createdDate) {
			this.createdDate = createdDate;
			return this;
		}

		public Invoice build() {
			return new Invoice(this);
		}
	}

}
