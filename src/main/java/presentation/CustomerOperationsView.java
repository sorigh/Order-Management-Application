package presentation;

import bll.CustomerBLL;
import model.Customer;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 *  Clasa responsabila de afisarea, inserarea si stergerea clientilor
 *  existente in tabela din database.
 *  Extinde TableView pentru referirea simplificata asupra tabelelor
 */
public class CustomerOperationsView extends TableView implements ActionListener {
    /**
     * panel principal atasat la fereastra
     */
    private final JPanel panel;
    /**
     * accesare model
     */
    private final Customer c = new Customer();
    /**
     * clientul selectat din tabel
     */
    private Customer selectedCustomer;
    /**
     * clientul creat dupa insert
     */
    private final Customer createCustomer = new Customer();
    /**
     * accesare business logic de tip client
     */
    private final CustomerBLL businessLogic = new CustomerBLL();
    /**
     * array list cu toate textFieldurile atasate la panel pentru afisarea clientilor
     */
    ArrayList<JTextField> textFields = new ArrayList<>();
    /**
     * tabelul in care va fi atasat tabelul cu clienti
     */
    private JTable table;
    /**
     * buton pentru updatarea unui client
     */
    private JButton updateButton;
    /**
     * buton pentru curatarea fieldurilor din interfata grafica
     */
    private JButton clearFieldsButton;
    /**
     * buton pentru stergerea unui client
     */
    private JButton deleteButton;
    /**
     * buton pentru inserarea unui client
     */
    private JButton insertButton;

    /**
     * Constructor principal al ferestrei cu action listenere implementate pentru butoane
     * @param parent frame-ul parinte in raport cu care este afisata
     */
    public CustomerOperationsView(Frame parent) {
        //super(parent);
        setTitle("Customer operations");
        panel = new JPanel();
        setContentPane(panel);
        setMinimumSize(new Dimension(900, 650));
        setModal(true);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setResizable(false);

        panel.setBackground(new Color(87, 108, 133));
        panel.setLayout(null);

        JLabel selectedLabel = new JLabel();
        selectedLabel.setText("Selected Customer:");
        selectedLabel.setBounds(50,200,200,50);
        selectedLabel.setFont(new Font("Cambria",Font.BOLD,17));
        selectedLabel.setForeground(new Color(223, 225, 229));
        panel.add(selectedLabel);

        addTable();
        addButtons();
        updateButton.addActionListener(ae -> {
            performUpdate();
            performUpdate();
            setContentPane(panel);
            addTable();
        });

        clearFieldsButton.addActionListener(ae -> clearFields());

        deleteButton.addActionListener(ae -> {
            //System.out.println("delete button works");
            performDelete();
            setContentPane(panel);
            addTable();


        });

        insertButton.addActionListener(ae -> {
            performInsert();
            setContentPane(panel);
            addTable();

        });
        this.setVisible(true);
    }
    /**
     * Adauga un tabel in panel principal, utilizand metode din TableView
     */
    public void addTable() {
        removeTable(panel);
        ArrayList<Customer> customers = businessLogic.getAllCustomers();
        DefaultTableModel tableModel = createTableModel(c, customers);

        table = new JTable(tableModel);
        table.setDefaultEditor(Object.class, null);
        table.getSelectionModel().addListSelectionListener(e -> {
            int selectedRow = table.getSelectedRow();
            if (selectedRow != -1) {
                selectedCustomer = customers.get(selectedRow);
                displayData(selectedCustomer, textFields);
            }
        });

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(50, 10, 800, 200);
        panel.add(scrollPane);

        addFields(panel, c, textFields);
    }
    /**
     * Executa un delete in tabela pe baza informatilor introduse de la tastatura
     * in interfata grafica
     */
    private void performDelete() {
        System.out.println(selectedCustomer);
        if (selectedCustomer != null) {
            if (businessLogic.deleteCustomer(selectedCustomer) == 0) {
                JOptionPane.showMessageDialog(null, "Error on deleting customer!", "Fail", JOptionPane.ERROR_MESSAGE);
            }else {
                JOptionPane.showMessageDialog(null, "Deleted customer successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
            }

            clearFields();
            selectedCustomer =null;
        }
    }
    /**
     * curata textFieldurile si variabila responsabila de tinerea
     * clientului selectat pentru a reselecta un alt client sau inserarea unui client nou
     */
    private void clearFields() {
        for (JTextField t : textFields){
            t.setText("");
        }
        selectedCustomer =null;
    }
    /**
     * Executa un insert in tabela pe baza informatilor introduse de la tastatura
     * in interfata grafica
     */
    private void performInsert() {
        createCustomer.setLastName(textFields.get(1).getText());
        createCustomer.setFirstName(textFields.get(2).getText());
        createCustomer.setAddress(textFields.get(3).getText());
        createCustomer.setEmail(textFields.get(4).getText());
        createCustomer.setAge(Integer.parseInt(textFields.get(5).getText())); // Assuming age is an integer
        if (businessLogic.insertCustomer(createCustomer) == 0) {
            JOptionPane.showMessageDialog(null, "Error on adding customer!", "Fail", JOptionPane.ERROR_MESSAGE);
        }else {
            JOptionPane.showMessageDialog(null, "Added customer successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
        }
    }
    /**
     * Executa un update in tabela pe baza informatilor introduse de la tastatura
     * in interfata grafica
     */
    private void performUpdate() {
        if (selectedCustomer != null) {
            selectedCustomer.setLastName(textFields.get(1).getText());
            selectedCustomer.setFirstName(textFields.get(2).getText());
            selectedCustomer.setAddress(textFields.get(3).getText());
            selectedCustomer.setEmail(textFields.get(4).getText());
            selectedCustomer.setAge(Integer.parseInt(textFields.get(5).getText())); // Assuming age is an integer
            if (businessLogic.updateCustomer(selectedCustomer) == 0) {
                JOptionPane.showMessageDialog(null, "Error on updating customer!", "Fail", JOptionPane.ERROR_MESSAGE);
            }else {
                JOptionPane.showMessageDialog(null, "Updated customer successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
            }
        }
        selectedCustomer = null;
    }
    /**
     * Adauga butoanele pentru interactiunea din interfata grafica
     * cu operatiile principale pentru tabelei
     */
    public void addButtons() {
        updateButton = new JButton("Update");
        updateButton.setBounds(50, 500, 100, 30);
        updateButton.setFont(new Font("Cambria",Font.BOLD,15));
        updateButton.setForeground(new Color(223, 225, 229));
        updateButton.setBackground(new Color(93,64,27));


        clearFieldsButton = new JButton("Clear");
        clearFieldsButton.setBounds(160, 500, 100, 30);
        clearFieldsButton.setFont(new Font("Cambria",Font.BOLD,15));
        clearFieldsButton.setForeground(new Color(223, 225, 229));
        clearFieldsButton.setBackground(new Color(93,64,27));

        deleteButton = new JButton("Delete");
        deleteButton.setBounds(270, 500, 100, 30);
        deleteButton.setFont(new Font("Cambria",Font.BOLD,15));
        deleteButton.setForeground(new Color(223, 225, 229));
        deleteButton.setBackground(new Color(93,64,27));

        insertButton = new JButton("Insert");
        insertButton.setBounds(380, 500, 100, 30);
        insertButton.setFont(new Font("Cambria",Font.BOLD,15));
        insertButton.setForeground(new Color(223, 225, 229));
        insertButton.setBackground(new Color(93,64,27));

        panel.add(updateButton);
        panel.add(clearFieldsButton);
        panel.add(deleteButton);
        panel.add(insertButton);
    }


}
