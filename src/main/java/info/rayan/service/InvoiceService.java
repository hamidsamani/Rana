package info.rayan.service;

import info.rayan.domain.exception.InvoiceNotFountException;
import info.rayan.domains.Invoice;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Session Bean implementation class InvoiceService
 */
@Stateless
@LocalBean
public class InvoiceService {
	private static final String INVOIVE_NOT_FOUND = "Invoice with given id=%d not found.";

	@PersistenceContext(unitName = "rana")
	private EntityManager entityManager;

	/**
	 * Default constructor.
	 */
	public InvoiceService() {
	}

	public Invoice findOne(long id) {
		Invoice invoice = entityManager.find(Invoice.class, id);
		if (invoice == null) {
			throw new InvoiceNotFountException(String.format(INVOIVE_NOT_FOUND,
					id));
		}
		return invoice;
	}

	public Invoice save(Invoice invoice) {
		if (invoice.getId() == 0) {
			entityManager.persist(invoice);
			return invoice;
		}
		return entityManager.merge(invoice);
	}
}
