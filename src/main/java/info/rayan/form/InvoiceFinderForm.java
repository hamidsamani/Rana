package info.rayan.form;

import java.util.List;

import info.rayan.domains.Invoice;
import info.rayan.service.InvoiceService;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@RequestScoped
public class InvoiceFinderForm extends GeneralForm {

	private static final long serialVersionUID = 7568214986232459009L;
	private static final String INVOICE_FOUND_PATH = "issuedInvoice";

	@Inject
	private InvoiceService invoiceService;

	private long invoiceId;
	private String name;

	public long getInvoiceId() {
		return invoiceId;
	}

	public void setInvoiceId(long invoiceId) {
		this.invoiceId = invoiceId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String findInvoiceById() {
		putOnFlash("id", invoiceService.findOne(invoiceId).getId());
		return navigateTo(INVOICE_FOUND_PATH);
	}

	public String findInvoiceByName() {

		List<Invoice> list = invoiceService.findByNameLike(name);
		list.forEach(System.out::println);
		return null;
	}
}
