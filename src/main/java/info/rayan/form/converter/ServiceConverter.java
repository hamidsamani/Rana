package info.rayan.form.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

@FacesConverter("serviceConverter")
public class ServiceConverter implements Converter {
	public ServiceConverter() {
		System.out.println("converter initialized....." + this.hashCode());
	}

	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String arg2)
			throws ConverterException {
		System.out.println(arg0 + " " + arg1 + " " + arg2);
		return arg2;
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object arg2)
			throws ConverterException {
		System.out.println(arg0 + " " + arg1 + " " + arg2);
		return null;
	}

}
