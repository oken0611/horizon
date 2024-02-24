package jp.co.spiralinks.horizon.utils;

import org.apache.commons.beanutils.converters.AbstractConverter;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class LocalTimeConverter extends AbstractConverter {

	public LocalTimeConverter() {
		super();
	}

	public LocalTimeConverter(final Object defaultValue) {
		super(defaultValue);
	}

	@Override
	protected <T> T convertToType(Class<T> type, Object value) throws Throwable {

		String stringValue = value.toString().trim();

		if (stringValue.length() == 0) {
			return handleMissing(type);
		}

		if (type.equals(LocalTime.class)) {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
			LocalTime localTime = LocalTime.parse(value.toString(), formatter);
			return type.cast(localTime);
		}

		return null;
	}

	@Override
	protected Class<?> getDefaultType() {
		return LocalTime.class;
	}

}
