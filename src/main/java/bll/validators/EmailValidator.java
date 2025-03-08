package bll.validators;

import java.util.regex.Pattern;

import model.Customer;

/**
 * validarea formatului emailului clientului
 * cu un regex simplu, care verifica prezenta semnelor "@" si "."
 */
public class EmailValidator implements Validator<Customer> {
	/**
	 * regex pattern pentru email
	 */
	private static final String EMAIL_PATTERN = "[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}";

	/**
	 * Metoda principala de validare a emailului
	 * @throws IllegalArgumentException emailul nu este valid
	 * @param t clientul de validat
	 */
	public void validate(Customer t) {
		Pattern pattern = Pattern.compile(EMAIL_PATTERN);
		if (!pattern.matcher(t.getEmail()).matches()) {
			throw new IllegalArgumentException("Email is not a valid email!");
		}
	}

}
