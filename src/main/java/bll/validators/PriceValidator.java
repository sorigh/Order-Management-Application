package bll.validators;

import dao.ProductDAO;
import model.Product;
import model.Purchase;

public class PriceValidator implements Validator<Product>{
    /**
     * constructor default pentru intitializare
     */
    public PriceValidator(){}
    /**
     * Metoda principala de validarea cantitatii inserate inainte
     * de a introduce produsul
     * @throws IllegalArgumentException daca este valoare negativa
     */

    @Override
    public void validate(Product product) {
        if (product.getPrice() < 0)
            throw new IllegalArgumentException("No negative values!");
    }
}
