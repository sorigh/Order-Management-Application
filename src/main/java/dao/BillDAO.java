package dao;

import connection.ConnectionFactory;
import model.Bill;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 * Nu extinde AbstractDAO pentru a impune faptul ca elementele
 * pot fi doar inserate si vizualizate, nu modificate sau sterse.
 */
public class BillDAO {
    /**
     * constructor default pentru intitializare
     */
    public BillDAO(){}
    /**
     * Preluarea clasei care acceseaza datele
     */
    protected static final Logger LOGGER = Logger.getLogger(AbstractDAO.class.getName());

    /**
     * Creaza obiecte de tip bill in urma executiei unui querry
     * @param resultSet result setul rezultat in urma executiei unui querry
     * @return o lista cu elementele Bill gasite
     * @throws SQLException
     */
    private List<Bill> createObjects(ResultSet resultSet) throws SQLException {
        List<Bill> list = new ArrayList<>();
        while (resultSet.next()) {
            int purchaseId = resultSet.getInt("purchaseId");
            String customerName = resultSet.getString("customerName");
            String productName = resultSet.getString("productName");
            double price = resultSet.getDouble("price");
            int amount = resultSet.getInt("amount");
            double total = resultSet.getDouble("total");
            Bill bill = new Bill(purchaseId, customerName, productName, price, amount, total);
            list.add(bill);
        }
        return list;
    }

    /**
     * preia continutul tabelului Bill si il returneaza sub forma unei liste
     * @return o lista de obiecte Bill
     */
    public List<Bill> getTableContent() {
        String query = "SELECT * FROM Bill";
        try (
                Connection connection = ConnectionFactory.getConnection();
                PreparedStatement statement = connection.prepareStatement(query);
                ResultSet resultSet = statement.executeQuery()
        ) {
            return createObjects(resultSet);
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, "Error retrieving data from the log table: " + e.getMessage());
        }
        return new ArrayList<>();
    }
    /**
     * utilizeaza un querry de insert pentru a insera in baza de date
     * @param bill un record Bill de inserat
     * @return id-ul obiectului inserat sau 0 daca nu a reusit insertul
     */
    public int insert(Bill bill) {
        String query = "INSERT INTO Bill (purchaseId, customerName, productName, price, amount, total) VALUES (?, ?, ?, ?, ?, ?)";
        try (
                Connection connection = ConnectionFactory.getConnection();
                PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)
        ) {
            statement.setInt(1, bill.purchaseID());
            statement.setString(2, bill.customerName());
            statement.setString(3, bill.productName());
            statement.setDouble(4, bill.price());
            statement.setInt(5, bill.amount());
            statement.setDouble(6, bill.total());
            statement.executeUpdate();

            ResultSet rs = statement.getGeneratedKeys();
            if (rs.next()) {
                return rs.getInt(1); // return the inserted id
            }
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, "Error inserting data into the log table: " + e.getMessage());
        }
        return 0; //0 if insert fails
    }
}