package jp.co.spiralinks.horizon.utils;

import org.apache.commons.beanutils.converters.AbstractConverter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class StringConverter extends AbstractConverter {

	public StringConverter() {
		super();
	}

	public StringConverter(final Object defaultValue) {
		super(defaultValue);
	}

	@Override
	protected <T> T convertToType(Class<T> type, Object value) throws Throwable {

		if (String.class.equals(type) || Object.class.equals(type)) {
			return type.cast(value.toString());
		}

		throw conversionException(type, value);
	}

	@Override
	protected String convertToString(Object value) throws Throwable {

		if (value instanceof LocalDate) {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
			return formatter.format((LocalDate) value);
		}

		if (value instanceof LocalDateTime) {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
			return formatter.format((LocalDateTime) value);
		}

		if (value instanceof LocalTime) {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
			return formatter.format((LocalTime) value);
		}

		return super.convertToString(value);
	}

	@Override
	protected Class<?> getDefaultType() {
		return String.class;
	}

}
