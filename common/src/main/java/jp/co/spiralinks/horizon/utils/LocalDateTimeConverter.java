package jp.co.spiralinks.horizon.utils;

import org.apache.commons.beanutils.converters.AbstractConverter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class LocalDateTimeConverter extends AbstractConverter {

	public LocalDateTimeConverter() {
		super();
	}

	public LocalDateTimeConverter(final Object defaultValue) {
		super(defaultValue);
	}

	@Override
	protected <T> T convertToType(Class<T> type, Object value) throws Throwable {

		String stringValue = value.toString().trim();

		if (stringValue.length() == 0) {
			return handleMissing(type);
		}

		if (type.equals(LocalDateTime.class)) {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
			LocalDateTime localDateTime = LocalDateTime.parse(value.toString(), formatter);
			return type.cast(localDateTime);
		}

		return null;
	}

	@Override
	protected Class<?> getDefaultType() {
		return LocalDate.class;
	}

}
