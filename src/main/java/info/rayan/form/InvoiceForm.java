package info.rayan.form;

import info.rayan.domains.Invoice;
import info.rayan.domains.Invoice.InvoiceBuilder;
import info.rayan.domains.util.ServiceToOrderConverter;
import info.rayan.domains.OrderItem;
import info.rayan.domains.Service;
import info.rayan.service.InvoiceService;
import info.rayan.service.ServicesService;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;

import org.omnifaces.cdi.ViewScoped;

@Named
@ViewScoped
public class InvoiceForm implements Serializable {
	private static final long serialVersionUID = 1L;

	@Inject
	private InvoiceService invoiceService;

	@Inject
	private ServicesService servicesService;
	private List<Service> services;
	private String selectedServiceAsString;
	private Service selectedService;
	// user infos
	private String name;
	private String tell;

	private String description;

	// monetary infos
	private BigDecimal total = BigDecimal.ZERO;
	private BigDecimal deposit = BigDecimal.ZERO;
	private BigDecimal residual;

	private List<OrderItem> selectedServices = new ArrayList<>();

	@PostConstruct
	public void setUp() {
		services = servicesService.findAll();

	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTell() {
		return tell;
	}

	public void setTell(String tell) {
		this.tell = tell;
	}

	public List<OrderItem> getSelectedServices() {
		return selectedServices;
	}

	public void setOrderItems(List<OrderItem> orderItems) {
		this.selectedServices = orderItems;
	}

	public BigDecimal getTotal() {
		return total;
	}

	public void setTotal(BigDecimal total) {
		this.total = total;
	}

	public BigDecimal getDeposit() {
		return deposit;
	}

	public void setDeposit(BigDecimal deposit) {
		this.deposit = deposit;
	}

	public BigDecimal getResidual() {
		return residual;
	}

	public void setResidual(BigDecimal residual) {
		this.residual = residual;
	}

	public List<Service> getServices() {
		return services;
	}

	public void setServices(List<Service> services) {
		this.services = services;
	}

	public String getSelectedServiceAsString() {
		return selectedServiceAsString;
	}

	public void setSelectedServiceAsString(String selectedServiceAsString) {
		this.selectedServiceAsString = selectedServiceAsString;
	}

	public Service getSelectedService() {
		return selectedService;
	}

	public void setSelectedService(Service selectedService) {
		this.selectedService = selectedService;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * dataTable
	 */
	public void addRow() {
		System.out.println(selectedService + "  " + description);
		if (selectedService != null) {
			selectedServices.add(ServiceToOrderConverter
					.serviceToOrderItemConverter(selectedService, description));
			calculateTotalAmount();
		}
	}

	/**
	 * removes a row form dataTable from invoice.xhtml
	 * 
	 * @param orderItem
	 *            the selected row to be delete
	 */
	public void removeRow(OrderItem service) {
		if (selectedServices.contains(service)) {
			selectedServices.remove(service);
		}
		calculateTotalAmount();
	}

	/**
	 * updates the total amount in the case of adding row or deleting
	 */
	private void calculateTotalAmount() {
		total = BigDecimal.ZERO;
		for (OrderItem service : selectedServices) {
			total = total.add(service.getPrice());
		}
	}

	public void depositChange() {
		residual = total.subtract(deposit);
	}

	/**
	 * creates the final invoice
	 * 
	 * @return
	 */

	public String createInvoice() {
		Invoice invoice = InvoiceBuilder.create().customerName(name)
				.customerTell(tell).orderItems(selectedServices).build();
		invoiceService.save(invoice);
		return null;
	}

	public void serviceChange() {
		selectedService = servicesService.findByName(selectedServiceAsString);
	}
}
