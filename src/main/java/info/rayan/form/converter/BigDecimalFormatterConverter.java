package info.rayan.form.converter;

import java.math.BigDecimal;
import java.text.DecimalFormat;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

@FacesConverter("bigDecimalFormatter")
public class BigDecimalFormatterConverter implements Converter {

	private static final String CURRENCY_PATTERN = "###,###,###";

	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String arg2)
			throws ConverterException {
		return null;
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object arg2)
			throws ConverterException {
		if (arg2 == null) {
			return null;
		}

		BigDecimal value = (BigDecimal) arg2;
		DecimalFormat formatter = new DecimalFormat(CURRENCY_PATTERN);
		return formatter.format(value).toString();
	}
}
