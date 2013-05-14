package eu.wuttke.nrf.ui.misc;

import java.util.Locale;

import org.apache.commons.lang3.StringUtils;

import com.vaadin.data.util.converter.Converter;

import eu.wuttke.nrf.domain.misc.PrecisionDate;
import eu.wuttke.nrf.domain.misc.PrecisionDateUtil;

public class StringToPrecisionDateConverter
implements Converter<String, PrecisionDate> {

	private static final long serialVersionUID = 1L;

	public PrecisionDate convertToModel(String value, Locale locale)
	throws com.vaadin.data.util.converter.Converter.ConversionException {
		if (StringUtils.isEmpty(value))
			return null;
		
		PrecisionDate d = PrecisionDateUtil.parsePrecisionDate(value);
		if (d == null)
			throw new ConversionException("unable to convert to date with precision: " + value);
		
		return d;
	}

	public String convertToPresentation(PrecisionDate value, Locale locale)
	throws com.vaadin.data.util.converter.Converter.ConversionException {
		if (value == null)
			return "";
		return PrecisionDateUtil.formatPrecisionDate(value, locale);
	}

	public Class<PrecisionDate> getModelType() {
		return PrecisionDate.class;
	}

	public Class<String> getPresentationType() {
		return String.class;
	}

}
