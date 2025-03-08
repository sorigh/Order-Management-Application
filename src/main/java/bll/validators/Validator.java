package bll.validators;

/**
 * Interfata generica pentru validarea datelor
 * @param <T> Purchase, Customer
 */
public interface Validator<T> {
	/**
	 * metoda de implementat
	 * @param t tipul obiectului de validat
	 */
	public void validate(T t);
}
