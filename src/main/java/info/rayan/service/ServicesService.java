package info.rayan.service;

import java.util.List;

import info.rayan.domains.Service;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class ServicesService {
	@PersistenceContext(unitName = "rana")
	private EntityManager entityManager;

	public List<Service> findAll() {
		return entityManager.createQuery("FROM Service", Service.class)
				.getResultList();
	}

	public Service save(Service service) {
		if (service.getId() == 0) {
			entityManager.persist(service);
			return service;
		}
		return entityManager.merge(service);
	}

	public Service findByName(String serviceName) {
		return entityManager
				.createQuery("SELECT s From Service s WHERE s.name = :name",
						Service.class).setParameter("name", serviceName)
				.getSingleResult();
	}

	public void remove(Service service) {
		entityManager.remove(entityManager.contains(service) ? service
				: entityManager.merge(service));
	}
}
