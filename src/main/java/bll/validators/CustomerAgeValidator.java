package bll.validators;

import model.Customer;

/**
 * validarea intervalului de varsta impus unui client
 */
public class CustomerAgeValidator implements Validator<Customer> {
	private static final int MIN_AGE = 5;
	private static final int MAX_AGE = 100;

	/**
	 * verificare varsta client
	 * @param t clientul de validat
	 * @throws IllegalArgumentException
	 */
	public void validate(Customer t) {

		if (t.getAge() < MIN_AGE || t.getAge() > MAX_AGE) {
			throw new IllegalArgumentException("The Customer Age limit is not respected!");
		}

	}

}
