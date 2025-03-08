package model;

/**
 * Clasa de tip record pentru memorarea comenzilor efectuate si dupa ce a fost sters
 * un produs sau un client.
 * @param purchaseID id-ul comenzii efectuate
 * @param customerName numele clientului
 * @param productName numele produsului
 * @param price pretul produsului
 * @param amount cantitatea comandata
 * @param total suma totala achitata de client
 */
public record Bill(int purchaseID, String customerName, String productName, double price, int amount, double total) {
}
