package jp.co.spiralinks.horizon.utils;

import org.apache.commons.beanutils.converters.AbstractConverter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class LocalDateConverter extends AbstractConverter {

	public LocalDateConverter() {
		super();
	}

	public LocalDateConverter(final Object defaultValue) {
		super(defaultValue);
	}

	@Override
	protected <T> T convertToType(Class<T> type, Object value) throws Throwable {

		String stringValue = value.toString().trim();

		if (stringValue.length() == 0) {
			return handleMissing(type);
		}

		if (type.equals(LocalDate.class)) {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
			LocalDate localDate = LocalDate.parse(value.toString(), formatter);
			return type.cast(localDate);
		}

		return null;
	}

	@Override
	protected Class<?> getDefaultType() {
		return LocalDate.class;
	}

}
