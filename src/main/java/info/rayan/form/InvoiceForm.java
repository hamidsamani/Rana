package info.rayan.form;

import info.rayan.domains.Invoice;
import info.rayan.domains.Invoice.InvoiceBuilder;
import info.rayan.domains.OrderItem;
import info.rayan.domains.Service;
import info.rayan.domains.util.ServiceToOrderConverter;
import info.rayan.service.AmountCalculator;
import info.rayan.service.InvoiceService;
import info.rayan.service.ServicesService;
import info.rayan.util.FacesMessageBuilder;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;

import org.omnifaces.cdi.ViewScoped;

@Named
@ViewScoped
public class InvoiceForm extends GeneralForm {

	private static final long serialVersionUID = 6186965279333488586L;

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
	private BigDecimal discount = BigDecimal.ZERO;
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

	public BigDecimal getDiscount() {
		return discount;
	}

	public void setDiscount(BigDecimal discount) {
		this.discount = discount;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * dataTable
	 */
	public void addRow() {
		if (selectedService != null) {
			selectedServices.add(ServiceToOrderConverter
					.serviceToOrderItemConverter(selectedService, description));
			calculateFinalAmount();
			calculateTotal();

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
		calculateFinalAmount();
		calculateTotal();
	}

	public void depositChange() {
		if (selectedServicesIsValid()) {
			calculateFinalAmount();
		}
	}

	public void discountChange() {
		if (selectedServicesIsValid()) {
			calculateFinalAmount();
		}
	}

	/**
	 * updates the total amount in the case of adding row or deleting
	 */
	private void calculateFinalAmount() {
		final AmountCalculator calculator = new AmountCalculator();

		calculator.setServices(selectedServices);
		calculator.setDeposit(deposit);
		calculator.setDiscount(discount);

		residual = calculator.calculateAmount();
	}

	private void calculateTotal() {
		final AmountCalculator calculator = new AmountCalculator();

		calculator.setServices(selectedServices);
		total = calculator.calculateAmount();
	}

	/**
	 * creates the final invoice
	 * 
	 * @return
	 */

	public String createInvoice() {
		if (!selectedServicesIsValid()) {
			getFacesContext().addMessage(
					null,
					FacesMessageBuilder.create("خدماتی اضافه نشده است.")
							.build());
			return null;
		}
		Invoice invoice = InvoiceBuilder.create().customerName(name)
				.customerTell(tell).orderItems(selectedServices).build();
		Invoice saved = invoiceService.save(invoice);

		putOnFlash("id", saved.getId());
		return redirect("issuedInvoice");
	}

	private boolean selectedServicesIsValid() {
		return !selectedServices.isEmpty();
	}

	public void serviceChange() {
		selectedService = servicesService.findByName(selectedServiceAsString);
	}
}
