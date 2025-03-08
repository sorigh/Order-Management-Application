package model;

/**
 * Una dintre clasele model utilizate pentru proiectarea tabelului cu acelasi nume
 * din baza de date sql.
 * Campurile au acelasi nume cu numele coloanelor din tabela pentru simplitate
 */
public class Customer {
	private int customerId;
	private String lastName;
	private String firstName;
	private String address;
	private String email;
	private int age;

	/**
	 *  Creare obiect gol pentru preluarea campurilor declarate
	 */
	public Customer(){}

	/**
	 * Constructor complet pentru crearea unui obiect pe baza datelor transmise
	 * @param lastName numele de familie a clientului
	 * @param firstName prenumele clientului
	 * @param address adresa clientului
	 * @param email email-ul clientului
	 * @param age varsta clientului (intre 5 si 100 de ani)
	 */
	public Customer(String lastName, String firstName, String address, String email, int age) {
		super();
		this.lastName = lastName;
		this.firstName = firstName;
		this.address = address;
		this.email = email;
		this.age = age;
	}
	/**
	 * Getter pentru validare si accesare
	 * in data access pentru crearea unor querry-uri
	 * @return id ul clientului
	 */
	public int getCustomerId() {
		return customerId;
	}
	/**
	 * Setter pentru initializare id
	 * updatarea/ setarea datelor pentru a fi utilizate in querry uri si
	 * pentru transmiterea lor
	 * @param customerId id-ul clientului
	 */
	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}
	/**
	 * Getter pentru validare si accesare
	 * in data access pentru crearea unor querry-uri
	 * @return numele de familie a clientului
	 */
	public String getLastName() {
		return lastName;
	}
	/**
	 * Setter pentru initializare numele
	 * updatarea/ setarea datelor pentru a fi utilizate in querry uri si
	 * pentru transmiterea lor
	 * @param lastName numele de familie a clientului
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	/**
	 * Getter pentru validare si accesare
	 * in data access pentru crearea unor querry-uri
	 * @return prenumele clientului
	 */
	public String getFirstName() {
		return firstName;
	}
	/**
	 * Setter pentru initializare prenumele
	 * updatarea/ setarea datelor pentru a fi utilizate in querry uri si
	 * pentru transmiterea lor
	 * @param firstName prenumele clientului
	 */
	public void setFirstName(String  firstName) {
		this.firstName =  firstName;
	}
	/**
	 * Getter pentru validare si accesare
	 * in data access pentru crearea unor querry-uri
	 * @return adresa clientului
	 */
	public String getAddress() {
		return address;
	}
	/**
	 * Setter pentru initializare adresa
	 * updatarea/ setarea datelor pentru a fi utilizate in querry uri si
	 * pentru transmiterea lor
	 * @param address adresa clientului
	 */
	public void setAddress(String address) {
		this.address = address;
	}
	/**
	 * Getter pentru validare si accesare
	 * in data access pentru crearea unor querry-uri
	 * @return varsta clientului
	 */
	public int getAge() {
		return age;
	}
	/**
	 * Setter pentru initializare varsta
	 * updatarea/ setarea datelor pentru a fi utilizate in querry uri si
	 * pentru transmiterea lor
	 * @param age varsta clientului
	 */
	public void setAge(int age) {
		this.age = age;
	}
	/**
	 * Getter pentru validare si accesare
	 * in data access pentru crearea unor querry-uri
	 * @return emailul clientului
	 */
	public String getEmail() {
		return email;
	}
	/**
	 * Setter pentru initializare email
	 * updatarea/ setarea datelor pentru a fi utilizate in querry uri si
	 * pentru transmiterea lor
	 * @param email emailul clientului
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "Customer [ " +
				"id=" + customerId +
				", lastName=" + lastName +
				", firstName=" + firstName +
				", address=" + address +
				", email=" + email +
				", age=" + age +
				"]";
	}

}
