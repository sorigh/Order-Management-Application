package model;

/**
 * Una dintre clasele model utilizate pentru proiectarea tabelului cu acelasi nume
 * din baza de date sql.
 * Campurile au acelasi nume cu numele coloanelor din tabela pentru simplitate
 */
public class Product {
    private int productId;
    private String name;
    private String description;
    private double price;
    private int quantity;

    /**
     *  Creare obiect gol pentru preluarea campurilor declarate
     */
    public Product(){}

    /**
     * Constructor complet pentru crearea unui obiect pe baza datelor transmise
     * @param name numele produsului
     * @param description descrierea produsului
     * @param price pretul produsului
     * @param quantity cantitatea in stoc a produsului
     */
    public Product(String name, String description, Double price, int quantity) {
        super();
        this.name = name;
        this.description = description;
        this.price = price;
        this.quantity = quantity;
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
     * Setter pentru initializare id
     * updatarea/ setarea datelor pentru a fi utilizate in querry uri si
     * pentru transmiterea lor
     * @param productId id-ul produsului
     */
    public void setProductId(int productId) {
        this.productId = productId;
    }
    /**
     * Getter pentru validare si accesare
     * in data access pentru crearea unor querry-uri
     * @return numele produsului
     */
    public String getName() {
        return name;
    }
    /**
     * Setter pentru initializare numele
     * updatarea/ setarea datelor pentru a fi utilizate in querry uri si
     * pentru transmiterea lor
     * @param name numele produsului
     */
    public void setName(String name) {
        this.name = name;
    }
    /**
     * Getter pentru validare si accesare
     * in data access pentru crearea unor querry-uri
     * @return descrierea produsului
     */
    public String getDescription() {
        return description;
    }
    /**
     * Setter pentru initializare descrierea produsului
     * updatarea/ setarea datelor pentru a fi utilizate in querry uri si
     * pentru transmiterea lor
     * @param description descrierea produsului
     */
    public void setDescription(String description) {
        this.description = description;
    }
    /**
     * Getter pentru validare si accesare
     * in data access pentru crearea unor querry-uri
     * @return pretul produsului
     */
    public double getPrice() {
        return price;
    }
    /**
     * Setter pentru initializare pretul produsului
     * updatarea/ setarea datelor pentru a fi utilizate in querry uri si
     * pentru transmiterea lor
     * @param price pretul produsului
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * Getter pentru validare si accesare
     * in data access pentru crearea unor querry-uri
     * @return cantitatea disponibila
     */
    public int getQuantity() {
        return quantity;
    }
    /**
     * Setter pentru initializare cantitatea disponibila
     * updatarea/ setarea datelor pentru a fi utilizate in querry uri si
     * pentru transmiterea lor
     * @param quantity cantitatea de produs
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    /**
     * Decrementare cantitatea ramasa din produsul selectat
     * dupa ce a fost comandat
     * @param amount cantitatea cu care se decrementeaza cantitatea de produs
     */
    public void decrementQuantity(int amount) {
        this.quantity -= amount;
    }

    /**
     * pentru afisarea in consola in partea de testare
     */
    @Override
    public String toString() {

        return "Product [" +
                "id=" + productId +
                ", name='" + name +
                ", description='" + description +
                ", price=" + price +
                ", quantity=" + quantity +
                ']';
    }


}
