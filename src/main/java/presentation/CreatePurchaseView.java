package presentation;

import bll.CustomerBLL;
import bll.ProductBLL;
import bll.PurchaseBLL;
import model.Customer;
import model.Product;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Field;
import java.util.ArrayList;
/**
 *  Clasa responsabila de afisarea clientilor si produselor si
 *  inserarea  comenzilor, cu updatare a stocului tabelei produs
 *  Extinde TableView pentru referirea simplificata asupra tabelelor
 */
public class CreatePurchaseView extends TableView implements ActionListener {
    /**
     * panel principal atasat la fereastra
     */
    private final JPanel panel;
    /**
     * scroll pane-ul desemnat pentru tabela de clienti
     */
    private JScrollPane scrollPaneCustomer;
    /**
     * scroll pane-ul desemnat pentru tabela de produse
     */
    private JScrollPane scrollPaneProduct;
    /**
     * accesare model produs
     */
    private final Product p = new Product();
    /**
     * accesare model client
     */
    private final Customer c = new Customer();
    /**
     * produsul selectat din tabel
     */
    private Product selectedProduct;
    /**
     * clientul selectat din tabel
     */
    private Customer selectedCustomer;
    /**
     * accesare business logic de tip produs
     */
    private final ProductBLL productBLL = new ProductBLL();
    /**
     * accesare business logic de tip client
     */
    private final CustomerBLL customerBLL = new CustomerBLL();
    /**
     * accesare business logic de tip produs
     */
    private final PurchaseBLL purchaseBLL = new PurchaseBLL();
    /**
     * tabel cu produse
     */
    private JTable tableProduct;
    /**
     * tabel cu clienti
     */
    private JTable tableCustomer;
    /**
     * zona de afisare client selectat
     */
    private JTextArea customerTextArea;
    /**
     * zona de afisare produs selectat
     */
    private JTextArea productTextArea;
    /**
     * zona de tastare cantitatea de produs dorita pentru comanda
     */
    private JTextField amountTextField;
    /**
     * buton pentru plasare comanda
     */
    private JButton orderButton;
    /**
     * Constructor principal al ferestrei cu action listener implementat pentru butonul de creat comanda
     * @param parent frame-ul parinte in raport cu care este afisata
     */
    public CreatePurchaseView(Frame parent) {
        setTitle("Order Placement");
        panel = new JPanel();
        setContentPane(panel);
        setMinimumSize(new Dimension(900, 650));
        setModal(true);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setResizable(false);
        panel.setBackground(new Color(87, 108, 133));
        panel.setLayout(null);
        addTable(scrollPaneProduct, tableProduct, p, 50, 250);
        addTable(scrollPaneCustomer, tableCustomer, c, 50, 50);


        addComponents();

        orderButton.addActionListener(ae -> {
            placeOrder(selectedCustomer, selectedProduct, Integer.parseInt(amountTextField.getText()));
            refreshTables();
        });
        this.setVisible(true);
    }

    /**
     * metoda diferita de adaugare componente datorita utilizarii unor text area
     * care nu pot fi modificate pentru vizualizarea clientului selectat
     */
    private void addComponents() {
        JLabel tableHeaderLabel = new JLabel();
        tableHeaderLabel.setText("Customer table: ");
        tableHeaderLabel.setBounds(50, -50, 200, 150);
        tableHeaderLabel.setFont(new Font("Cambria",Font.BOLD,15));
        tableHeaderLabel.setForeground(new Color(223, 225, 229));
        panel.add(tableHeaderLabel);

        JLabel tableHeaderLabel2 = new JLabel();
        tableHeaderLabel2.setText("Product table: ");
        tableHeaderLabel2.setBounds(50, 150, 200, 150);
        tableHeaderLabel2.setFont(new Font("Cambria",Font.BOLD,15));
        tableHeaderLabel2.setForeground(new Color(223, 225, 229));
        panel.add(tableHeaderLabel2);

        JLabel selectedCustomerLabel = new JLabel();
        selectedCustomerLabel.setText("Selected customer: ");
        selectedCustomerLabel.setBounds(50, 350, 200, 150);
        selectedCustomerLabel.setFont(new Font("Cambria",Font.BOLD,15));
        selectedCustomerLabel.setForeground(new Color(223, 225, 229));


        customerTextArea = new JTextArea();
        customerTextArea.setBounds(50, 450, 200, 150);
        customerTextArea.setEditable(false);

        JLabel selectedProductLabel = new JLabel();
        selectedProductLabel.setText("Selected product: ");
        selectedProductLabel.setBounds(300, 350, 200, 150);
        selectedProductLabel.setFont(new Font("Cambria",Font.BOLD,15));
        selectedProductLabel.setForeground(new Color(223, 225, 229));

        productTextArea = new JTextArea();
        productTextArea.setBounds(300, 450, 200, 150);
        productTextArea.setEditable(false);

        JLabel amountLabel = new JLabel();
        amountLabel.setText("Amount: ");
        amountLabel.setBounds(510, 350, 200, 150);
        amountLabel.setFont(new Font("Cambria",Font.BOLD,15));
        amountLabel.setForeground(new Color(223, 225, 229));

        amountTextField = new JTextField();
        amountTextField.setBounds(510, 450, 100, 50);

        orderButton = new JButton();
        orderButton.setText("Order");
        orderButton.setBounds(630, 450, 100, 50);
        orderButton.setBackground(new Color(93,64,27));
        orderButton.setFont(new Font("Cambria",Font.BOLD,15));
        orderButton.setForeground(new Color(223, 225, 229));

        panel.add(customerTextArea);
        panel.add(productTextArea);
        panel.add(amountTextField);
        panel.add(orderButton);

        panel.add(selectedCustomerLabel);
        panel.add(selectedProductLabel);
        panel.add(amountLabel);
    }

    /**
     * curatare zonele din interfata grafica dupa plasarea unei comenzi
     */

    private void clearFields() {
        customerTextArea.setText("");
        productTextArea.setText("");
        amountTextField.setText("");
        selectedProduct = null;
        selectedCustomer = null;
    }

    /**
     * comunicare cu purchaseBLL pentru a insera comanda
     * @param selectedCustomer clientul selectat in interfata din tabel
     * @param selectedProduct produsul selectat in interfata din tabel
     * @param amount cantitatea de produs cumparat
     */
    private void placeOrder(Customer selectedCustomer, Product selectedProduct, int amount) {
        if (purchaseBLL.placePurchase(selectedCustomer, selectedProduct, amount) == -1) {
            JOptionPane.showMessageDialog(null, "Not enough stock!", "Warning", JOptionPane.WARNING_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "Order placed successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    /**
     * sterge tabelele existente pentru a le reincarca dupa
     * modificarea continutului acestora in urma inserarii
     */
    private void removeTables() {
        // Remove existing tables if they exist
        panel.remove(scrollPaneProduct);
        panel.remove(scrollPaneCustomer);
    }

    /**
     *
     * @param scrollPane scroll pane-ul in care se creaza tabelul
     * @param table tabelul care va fi initializat si creat
     * @param object tipul tabelului
     * @param x coordonate de amplasare tabel
     * @param y coordonate de amplasare tabel
     */
    private void addTable(JScrollPane scrollPane, JTable table, Object object, int x, int y) {
        ArrayList<?> items = (object instanceof Product) ? productBLL.getAllProducts() : customerBLL.getAllCustomers();

        DefaultTableModel t;
        t = createTableModel(object, items);
        table = new JTable(t);
        table.setDefaultEditor(Object.class, null);
        JTable finalTable = table;
        table.getSelectionModel().addListSelectionListener(e -> {
            int selectedRow = finalTable.getSelectedRow();
            if (selectedRow != -1) {
                if (object instanceof Product) {
                    selectedProduct = (Product) items.get(selectedRow);
                    displayData(selectedProduct, productTextArea);
                } else if (object instanceof Customer) {
                    selectedCustomer = (Customer) items.get(selectedRow);
                    displayData(selectedCustomer, customerTextArea);
                }
            }
        });

        scrollPane = new JScrollPane(table);
        scrollPane.setBounds(x, y, 800, 150);
        if (object instanceof Product) {
            scrollPaneProduct = scrollPane;
            panel.add(scrollPaneProduct);
        } else if (object instanceof Customer) {
            scrollPaneCustomer = scrollPane;
            panel.add(scrollPaneCustomer);
        }
    }

    /**
     *
     * @param dataObject tipul clasei pentru preluarea campurilor
     * @param textArea zona in care vor fi introduse datele obiectului selectat
     */
    private void displayData(Object dataObject, JTextArea textArea) {
        textArea.setText("");
        for (Field field : dataObject.getClass().getDeclaredFields()) {
            field.setAccessible(true); // set modifier to public
            Object value;
            try {
                value = field.get(dataObject);
                textArea.append(value.toString() + "\n");
            } catch (IllegalArgumentException | IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * reinitializare tabele dupa insert in purchase si update in product
     */

    private void refreshTables() {
        removeTables();
        addTable(scrollPaneProduct, tableProduct, p, 50, 200);
        addTable(scrollPaneCustomer, tableCustomer, c, 50, 10);
        clearFields();
        setContentPane(panel);
    }



    public void actionPerformed(ActionEvent e) {

    }
}
