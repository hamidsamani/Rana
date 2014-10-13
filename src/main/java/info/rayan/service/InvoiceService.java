package info.rayan.service;


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
	@PersistenceContext(unitName = "rana")
	private EntityManager entityManager;

	/**
	 * Default constructor.
	 */
	public InvoiceService() {
	}

	public void save(Invoice invoice) {
		entityManager.persist(invoice);
	}

}
