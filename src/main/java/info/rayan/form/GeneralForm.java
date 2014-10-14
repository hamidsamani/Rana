package info.rayan.form;

import java.io.Serializable;

import javax.faces.context.FacesContext;

@SuppressWarnings("serial")
public abstract class GeneralForm implements Serializable {
	
	protected FacesContext getFacesContext() {
		return FacesContext.getCurrentInstance();
	}

}
