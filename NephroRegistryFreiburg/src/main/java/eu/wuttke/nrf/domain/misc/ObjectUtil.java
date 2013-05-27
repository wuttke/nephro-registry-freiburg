package eu.wuttke.nrf.domain.misc;

import java.lang.reflect.Field;

import javax.persistence.Id;

public class ObjectUtil {

	public static String getIdPropertyName(Class<?> cl) {
		for (Field field : cl.getDeclaredFields())
			if (field.getAnnotation(Id.class) != null)
				return field.getName();
		return null;
	}

}
