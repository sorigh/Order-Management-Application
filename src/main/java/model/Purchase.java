package model;
/**
 * Una dintre clasele model utilizate pentru proiectarea tabelului cu acelasi nume
 * din baza de date sql.
 * Campurile au acelasi nume cu numele coloanelor din tabela pentru simplitate
 */
public class Purchase {
    int purchaseId;
    int productId;
    int customerId;
    int amount;

    /**
     * Constructor principal care creaza obiectul de tip Purchase cu datele trimise ca paramentru
     * @param customerId id-ul clientului care face comanda. Preluat din selected customer
     * @param productId id-ul produsului comandat. Preluat din selected product
     * @param amount cantitatea comandata din produsul mentionat
     */
    public Purchase(int customerId, int productId, int amount){
        this.customerId = customerId;
        this.productId = productId;
        this.amount = amount;
    }

    /**
     * Getter pentru validare si accesare
     * in data access pentru crearea unor querry-uri
     * @return id-ul produsului
     */
    public int getProductId() {
        return productId;
    }

    /**
     * Getter pentru validare si accesare
     * in data access pentru crearea unor querry-uri
     * @return cantitatea disponibila
     */
    public int getAmount() {
        return amount;
    }


}
