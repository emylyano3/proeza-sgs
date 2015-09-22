package proeza.mci;

import static java.lang.Math.min;

import java.text.Normalizer;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.WordUtils;

public class DataMCI {

	protected String formatCodigo(String value, String defaultValue) {
		if (value == null) {
			return defaultValue;
		}
		value = Normalizer.normalize(value, Normalizer.Form.NFD);
		value = removeSpecialChars(value);
		return value.trim().toUpperCase().substring(0, min(value.trim().length(), 20));
	}

	protected String formatNombre(String value, String defaultValue) {
		if (value == null) {
			return defaultValue;
		}
		return WordUtils.capitalize(value.trim());
	}

	protected String formatDescripcion(String value, String defaultValue) {
		if (value == null) {
			return defaultValue;
		}
		return StringUtils.capitalize(value.trim());
	}

	private String removeSpecialChars(String value) {
		StringBuilder builder = new StringBuilder(value.length());
		for (int i = 0; i < value.length(); i++) {
			if (Character.isLetterOrDigit(value.charAt(i))) {
				builder.append(value.charAt(i));
			}
		}
		return builder.toString();
	}
}