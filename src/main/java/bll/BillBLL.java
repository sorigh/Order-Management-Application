package bll;

import dao.BillDAO;
import model.Bill;

import java.util.ArrayList;

/**
 * Legatura cu metodele de acces la tabelurile din baza de date
 * si verificarea parametrilor de intrare pentru validarea acestora
 */
public class BillBLL {
    private final BillDAO dataAccess;
    /**
     * Constructor simplu pentru initializare si pentru accesare
     * a metodelor generice
     */
    public BillBLL() {
        dataAccess = new BillDAO();
    }

    /**
     * Accesarea elementelor din tabelul Bill din sql pentru a
     * forma tabela log din java
     * @return o lista cu continutul tabelului
     */

    public ArrayList<Bill> getAllBills(){
        return new ArrayList<>(dataAccess.getTableContent());
    }

    /**
     * inserarea unei facturi dupa realizarea unei comenzi
     * @param bill factura generata dupa comanda
     * @return id_ul obiectului inserat sau 0 daca nu a reusit insertul
     */
    public int insert(Bill bill) {
        return dataAccess.insert(bill);
    }


}
