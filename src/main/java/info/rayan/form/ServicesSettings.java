package info.rayan.form;

import info.rayan.domains.Service;
import info.rayan.service.ServicesService;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@SessionScoped
public class ServicesSettings implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private ServicesService servicesService;

	// properties to add a new service
	private String name;
	private BigDecimal price;

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

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
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
		if (!FacesContext.getCurrentInstance().isPostback()) {
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
		servicesService.save(new Service(name, price, null));
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
}
