package dao;

import model.Product;
import model.Purchase;

/**
 * extinde AbstractDAO pentru a exista o implementare
 * concreta a accesului la date pentru comenzi
 */
public class PurchaseDAO extends AbstractDAO<Purchase>{
    /**
     * accesul la date
     */
    ProductDAO productDAO = new ProductDAO();

    /**
     * Constructor simplu pentru initializare si pentru accesare
     * a metodelor generice
     */

    public PurchaseDAO() {
        super();
    }

    /**
     * metoda utilizata doar de purchase, pentru a se asigura de modificarea
     * stocului dupa o comanda
     * @param p obiectul comenzii realizate
     * @return id-ul obiectului inserat sau 0 in caz ca nu a reusit insertul
     */
    public int insertOrderWithProductUpdate(Purchase p) {
        //valid, checked in bll
        Product product = productDAO.findById(p.getProductId());
        product.decrementQuantity(p.getAmount());
        productDAO.update(product);

        return super.insert(p);
    }



}
