package info.rayan.form;

import info.rayan.domains.Invoice;
import info.rayan.service.InvoiceService;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;

import org.omnifaces.cdi.ViewScoped;

@Named
@ViewScoped
public class IssuedInvoiceForm extends GeneralForm {

	private static final long serialVersionUID = 2671603694117379866L;

	@Inject
	private InvoiceService invoiceService;

	private Invoice invoice;

	@PostConstruct
	public void setUp() {
		if (null != getFromFlash("id")) {
			invoice = invoiceService.findOne((long) getFromFlash("id"));
		}
	}

	public Invoice getInvoice() {
		return invoice;
	}

	public void setInvoice(Invoice invoice) {
		this.invoice = invoice;
	}

}
