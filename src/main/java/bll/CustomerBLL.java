package bll;

import java.util.ArrayList;
import java.util.List;

import bll.validators.CustomerAgeValidator;
import bll.validators.EmailValidator;
import bll.validators.Validator;
import dao.CustomerDAO;
import model.Customer;

/**
 * Legatura cu metodele de acces la tabelurile din baza de date
 * si verificarea parametrilor de intrare pentru validarea acestora
 */
public class CustomerBLL {

	private final List<Validator<Customer>> validators;
	private final CustomerDAO dataAccess;

	/**
	 * Constructor simplu pentru initializarea validatoarelor
	 * si pentru accesare a metodelor generice
	 */
	public CustomerBLL() {
		dataAccess = new CustomerDAO();
		validators = new ArrayList<>();
		validators.add(new EmailValidator());
		validators.add(new CustomerAgeValidator());
	}

	/**
	 * Accesarea elementelor din tabelul Customer din sql pentru a
	 * forma tabelul din java
	 * @return o lista cu continutul tabelului
	 */
	public ArrayList<Customer> getAllCustomers(){

        return new ArrayList<>(dataAccess.getTableContent());
	}
	/**
	 * inserarea unui client si validarea datelor transmise
	 * @param customer clientul de inserat
	 * @return id_ul clientului inserat sau 0 daca nu a reusit insertul
	 */
	public int insertCustomer(Customer customer) {
		for (Validator<Customer> v : validators) {
			v.validate(customer);
		}
		return dataAccess.insert(customer);
	}

	/**
	 * updatarea unui client si validarea datelor transmise
	 * @param customer clientul de updatat
	 * @return id_ul clientului modificat sau 0 daca nu a reusit modificarea
	 */

	public int updateCustomer(Customer customer) {
		for (Validator<Customer> v : validators) {
			v.validate(customer);
		}
		return dataAccess.update(customer);
	}
	/**
	 * stergerea unui client si validarea datelor transmise
	 * @param customer clientul de sters
	 * @return id_ul clientului sters sau 0 daca nu a reusit stergerea
	 */
	public int deleteCustomer(Customer customer) {
		for (Validator<Customer> v : validators) {
			v.validate(customer);
		}
		return dataAccess.delete(customer);
	}
}
