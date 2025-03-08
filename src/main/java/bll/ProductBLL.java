package bll;

import bll.validators.EmailValidator;
import bll.validators.PriceValidator;
import bll.validators.QuantityValidator;
import bll.validators.Validator;
import dao.BillDAO;
import dao.ProductDAO;
import model.Customer;
import model.Product;
import model.Purchase;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
/**
 * Legatura cu metodele de acces la tabelele din baza de date
 * si verificarea parametrilor de intrare pentru validarea acestora
 */
public class ProductBLL {
    private final List<Validator<Product>> validators;
    /**
     * accesul la date din sql
     */
    private ProductDAO dataAccess;

    /**
     * constructor default
     */
    public ProductBLL() {
        dataAccess = new ProductDAO();
        validators = new ArrayList<>();
        validators.add(new QuantityValidator());
        validators.add(new PriceValidator());
    }

    /**
     * Cautarea unui produs pe baza id-ului sau
     * @param id id-ul produsului
     * @return primul obiect gasit in tabel sau null
     * throws NoSuchElementException
     */
    public Product findProductById(int id) {
        Product st = dataAccess.findById(id);
        st.setProductId(id);
        if (st == null) {
            throw new NoSuchElementException("The customer with id =" + id + " was not found!");
        }
        return st;
    }
    /**
     * Accesarea elementelor din tabelul Product din sql pentru a
     * forma tabelul din java
     * @return o lista cu continutul tabelului
     */
    public ArrayList<Product> getAllProducts(){
        ArrayList<Product> products = new ArrayList<>(dataAccess.getTableContent());
        if (products == null) {
            throw new NoSuchElementException("The table is empty!");
        }
        return products;
    }
    /**
     * inserarea unui produs si validarea datelor transmise
     * @param product produsul de inserat
     * @return id_ul produsului inserat sau 0 daca nu a reusit insertul
     */
    public int insertProduct(Product product) {
        for (Validator<Product> v : validators) {
            try {
                v.validate(product);
            }
            catch (IllegalArgumentException e) {
                return -1;
            }
        }
        return dataAccess.insert(product);
    }
    /**
     * updatarea unui produs si validarea datelor transmise
     * @param product produsul de updatat
     * @return id_ul produsului modificat sau 0 daca nu a reusit modificarea
     */
    public int updateProduct(Product product) {

        for (Validator<Product> v : validators) {
            try {
                v.validate(product);
            }
            catch (IllegalArgumentException e) {
                return -1;
            }
        }
        return dataAccess.update(product);
    }
    /**
     * stergerea unui client
     * @param product produsul de sters
     * @return id_ul produsului sters sau 0 daca nu a reusit stergerea
     */
    public int deleteProduct(Product product) {
        return dataAccess.delete(product);
    }
}
