package info.rayan.form.util;

import info.rayan.domains.Invoice;
import info.rayan.domains.Invoice.InvoiceBuilder;
import info.rayan.domains.OrderItem;
import info.rayan.service.InvoiceService;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@RequestScoped
public class InvoiceBuilderForm {
	@Inject
	private InvoiceService invoiceService;

	public Invoice createInvoice(String name, String tell,
			List<OrderItem> selectedServices) {

		Invoice build = InvoiceBuilder.create().customerName(name)
				.customerTell(tell).orderItems(selectedServices).build();
		return invoiceService.save(build);
	}
}
