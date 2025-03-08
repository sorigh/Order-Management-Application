package dao;


import model.Customer;

/**
 * extinde AbstractDAO pentru a exista o implementare
 * concreta a accesului la date pentru clienti
 */
public class CustomerDAO extends AbstractDAO<Customer>{

    /**
     * Constructor simplu pentru initializare si pentru accesare
     * a metodelor generice
     */
    public CustomerDAO(){
        super();
    }


}
