package info.rayan.domains.exception;

public class InvoiceNotFountException extends RuntimeException {

	private static final long serialVersionUID = -5452828282035621005L;

	public InvoiceNotFountException() {
		super();
	}

	public InvoiceNotFountException(String message) {
		super(message);
	}

}
