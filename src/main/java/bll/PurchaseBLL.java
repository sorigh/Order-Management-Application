package bll;

import bll.validators.AmountValidator;
import bll.validators.Validator;
import dao.PurchaseDAO;
import model.Bill;
import model.Customer;
import model.Product;
import model.Purchase;

import java.util.ArrayList;
import java.util.List;
/**
 * Legatura cu metodele de acces la tabelele din baza de date
 * si verificarea parametrilor de intrare pentru validarea acestora
 */
public class PurchaseBLL {
    /**
     * lista cu validatoarele de tip produs
     */
    private final List<Validator<Purchase>> validators;
    /**
     * accesul la date din sql
     */
    private final PurchaseDAO dataAccess;
    /**
     * legatura cu business logic din facturi pentru realizarea inserarilor
     */
    private final BillBLL billBll;
    /**
     * Constructor simplu pentru initializarea validatoarelor
     * si pentru accesare a metodelor generice
     */
    public PurchaseBLL() {
        dataAccess = new PurchaseDAO();
        billBll = new BillBLL();
        validators = new ArrayList<>();
        validators.add(new AmountValidator());
    }

    /**
     * Plasare comanda pe baza datelor care sunt prelucrate si trimise si in factura
     * @param c clientul care a realizat comanda
     * @param p produsul comandat
     * @param amount cantitatea de produs
     * @return id-ul comenzii
     */
    public int placePurchase(Customer c, Product p, int amount) {
        Purchase purchase = new Purchase(c.getCustomerId(),p.getProductId(),amount);
        for (Validator<Purchase> v : validators) {
            try {
                v.validate(purchase);
            }
            catch (IllegalArgumentException e) {
                return -1;
            }
        }

        int purchaseId = dataAccess.insertOrderWithProductUpdate(purchase);
        Bill bill = new Bill(purchaseId, c.getFirstName() +" "+c.getLastName(), p.getName(), p.getPrice(), amount,p.getPrice() * amount);
        billBll.insert(bill);
        return purchaseId;
    }

}
