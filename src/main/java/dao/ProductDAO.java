package dao;

import model.Product;
/**
 * extinde AbstractDAO pentru a exista o implementare
 * concreta a accesului la date pentru produse
 */
public class ProductDAO extends AbstractDAO<Product>{
    /**
     * Constructor simplu pentru initializare si pentru accesare
     * a metodelor generice
     */
    public ProductDAO(){
        super();
    }

}
