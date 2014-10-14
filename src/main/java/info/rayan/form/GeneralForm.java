package info.rayan.form;

import java.io.Serializable;

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

	protected String redirect(String view) {
		return String.format("%s?faces-redirect=true", view);
	}

	private Flash getFlash() {
		return getFacesContext().getExternalContext().getFlash();
	}

}
