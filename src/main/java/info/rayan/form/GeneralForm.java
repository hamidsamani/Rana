package info.rayan.form;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;

@SuppressWarnings("serial")
public abstract class GeneralForm implements Serializable {

	protected FacesContext getFacesContext() {
		return FacesContext.getCurrentInstance();
	}

	protected Object putOnFlash(String key, Object value) {
		return getFlash().put(key, value);
	}

	protected Object getFromFlash(String key) {
		return getFlash().get(key);
	}

	protected String navigateTo(String view) {
		return String.format("%s?faces-redirect=true", view);
	}

	protected boolean isPostback() {
		return getFacesContext().isPostback();
	}

	protected void addMessage(String summary) {
		getFacesContext().addMessage(null, new FacesMessage(summary));
	}

	protected String getRequestParameterValue(String key) {
		return getFacesContext().getExternalContext().getRequestParameterMap()
				.get(key);
	}

	protected boolean isRequestParametersContain(String key) {
		return getRequestParameterValue(key) != null;
	}

	protected boolean isParameterOnFlash(String key) {
		return getFromFlash(key) != null;
	}

	private Flash getFlash() {
		return getFacesContext().getExternalContext().getFlash();
	}

}
