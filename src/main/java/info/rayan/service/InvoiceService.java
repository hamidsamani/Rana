package info.rayan.service;

import info.rayan.domains.Invoice;
import info.rayan.domains.exception.InvoiceNotFountException;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

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

	// TODO correct the N+1 problem in retrieving objects.
	public List<Invoice> findByNameLike(String name) {

		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<Invoice> cq = cb.createQuery(Invoice.class);
		Root<Invoice> root = cq.from(Invoice.class);
		//		Join<Invoice, OrderItem> join = root.join("orderItems");
		cq.where(cb.like(root.get("customer").get("name"),
				decorateLikeExpression(name)));
		return entityManager.createQuery(cq).getResultList();
	}

	public Invoice save(Invoice invoice) {
		if (invoice.getId() == 0) {
			entityManager.persist(invoice);
			return invoice;
		}
		return entityManager.merge(invoice);
	}

	private String decorateLikeExpression(String value) {
		return String.format("%%%s%%", value);
	}
}
