package bll.validators;

import dao.ProductDAO;
import model.Product;
import model.Purchase;


/**
 * validare stoc produs inainte de cumparare
 */
public class AmountValidator implements Validator<Purchase>{
    /**
     * constructor default pentru intitializare
     */
    public AmountValidator(){}
    /**
     * Metoda principala de validarea cantitatii existente inainte de plasare
     * a comenzii
     * @throws IllegalArgumentException daca nu este suficient stoc
     * @param purchase comanda de validat
     */

    @Override
    public void validate(Purchase purchase) {
        ProductDAO productDAO = new ProductDAO();
        Product product = productDAO.findById(purchase.getProductId());
        if (purchase.getAmount() > product.getQuantity())
            throw new IllegalArgumentException("Not enough stock!");
    }
}
