package info.rayan.form;

import info.rayan.domains.Service;
import info.rayan.service.ServicesService;
import info.rayan.util.FacesMessageBuilder;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.List;

import javax.annotation.PreDestroy;
import javax.ejb.EJBException;
import javax.inject.Inject;
import javax.inject.Named;

import org.omnifaces.cdi.ViewScoped;

@Named
@ViewScoped
public class ServicesSettings extends GeneralForm implements Serializable {

	private static final long serialVersionUID = 1L;

	private static final String SUCESSFULLY_ADDED = "سرویس با موفقیت اضافه شد.";

	@Inject
	private ServicesService servicesService;

	// properties to add a new service
	private String name;
	private BigInteger price;

	// properties to edit services
	private Service selectedService;
	private List<Service> allServices;
	private String selectedServiceAsString;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public BigInteger getPrice() {
		return price;
	}

	public void setPrice(BigInteger price) {
		this.price = price;
	}

	public Service getSelectedService() {
		return selectedService;
	}

	public void setSelectedService(Service selectedService) {
		this.selectedService = selectedService;
	}

	public List<Service> getAllServices() {
		return allServices;
	}

	public void setAllServices(List<Service> allServices) {
		this.allServices = allServices;
	}

	public String getSelectedServiceAsString() {
		return selectedServiceAsString;
	}

	public void setSelectedServiceAsString(String selectedServiceAsString) {
		this.selectedServiceAsString = selectedServiceAsString;
	}

	/**
	 * called in the case of requesting settings.xhtml view.
	 */
	public void preRenderView() {
		if (!isPostback()) {
			allServices = servicesService.findAll();
		}
	}

	/**
	 * called whenever edit service service name value changed
	 */
	public void serviceChange() {
		selectedService = servicesService.findByName(selectedServiceAsString);
	}

	public void addNewService() {
		try {
			servicesService.save(new Service(name, price, null));
			getFacesContext().addMessage("growl",
					FacesMessageBuilder.create(SUCESSFULLY_ADDED).build());
		} catch (EJBException ex) {
			System.out.println(ex.getCausedByException());
		}

	}

	public void editSelectedService() {
		if (selectedService != null) {
			servicesService.save(selectedService);
		} else {
			System.out.println(selectedService);
		}
	}

	public void deleteSelectedService() {
		servicesService.remove(selectedService);
		allServices.remove(selectedService);

	}

	@PreDestroy
	public void preDestroy() {
		System.out.println("pre destroy called");
	}
}
