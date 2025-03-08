package presentation;

import bll.ProductBLL;
import model.Product;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 *  Clasa responsabila de afisarea, inserarea si stergerea produselor
 *  existente in tabela din database.
 *  Extinde TableView pentru referirea simplificata asupra tabelelor
 */
public class ProductOperationsView extends TableView implements ActionListener {
    /**
     * panel principal atasat la fereastra
     */
    private final JPanel panel;
    /**
     * accesare model
     */
    private final Product p = new Product();
    /**
     * produsul selectat din tabel
     */
    private Product selected;
    /**
     * produsul creat dupa insert
     */
    private final Product created = new Product();
    /**
     * accesare business logic de tip produs
     */
    private final ProductBLL businessLogic = new ProductBLL();
    /**
     * array list cu toate textFieldurile atasate la panel pentru afisarea oroduselor
     */
    ArrayList<JTextField> textFields = new ArrayList<>();

    /**
     * tabelul in care va fi atasat tabelul cu produse
     */
    private JTable table;
    /**
     * buton pentru updatarea unui produs
     */
    private JButton updateButton;
    /**
     * buton pentru curatarea fieldurilor din interfata grafica
     */
    private JButton clearFieldsButton;
    /**
     * buton pentru stergerea unui produs
     */
    private JButton deleteButton;
    /**
     * buton pentru inserarea unui produs
     */
    private JButton insertButton;

    /**
     * Constructor principal al ferestrei cu action listenere implementate pentru butoane
     * @param parent frame-ul parinte in raport cu care este afisata
     */
    public ProductOperationsView(Frame parent) {
        setTitle("Product operations");
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
        selectedLabel.setText("Selected Product:");
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
 * Executa un insert in tabela pe baza informatilor introduse de la tastatura
 * in interfata grafica
 */
    private void performInsert() {

        created.setName(textFields.get(1).getText());
        created.setDescription(textFields.get(2).getText());
        created.setPrice(Double.parseDouble(textFields.get(3).getText()));
        created.setQuantity(Integer.parseInt((textFields.get(4).getText())));
        if (businessLogic.insertProduct(created) == -1) {
            JOptionPane.showMessageDialog(null, "Could not insert product!", "Warning", JOptionPane.WARNING_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "Inserted product successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
        }
    }
    /**
     * Executa un delete in tabela pe baza informatilor introduse de la tastatura
     * in interfata grafica
     */
    private void performDelete() {
        System.out.println(selected);
        if (selected != null) {
            businessLogic.deleteProduct(selected);
            clearFields();
            selected =null;
        }
    }

    /**
     * Executa un update in tabela pe baza informatilor introduse de la tastatura
     * in interfata grafica
     */
    private void performUpdate() {
        if (selected != null) {
            selected.setName(textFields.get(1).getText());
            selected.setDescription(textFields.get(2).getText());
            selected.setPrice(Double.parseDouble(textFields.get(3).getText()));
            selected.setQuantity(Integer.parseInt((textFields.get(4).getText())));
            if (businessLogic.updateProduct(selected) == -1) {
                JOptionPane.showMessageDialog(null, "Could not perform update!", "Warning", JOptionPane.WARNING_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "Updated product successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
            }
        }
        selected =null;
    }
    /**
     * curata textFieldurile si variabila responsabila de tinerea
     * produsului selectat pentru a reselecta un alt produs sau inserarea unui produs nou
     */
    private void clearFields() {
        for (JTextField t : textFields){
            t.setText("");
        }
        selected =null;
    }

    /**
     * Adauga un tabel in panel principal, utilizand metode din {@Code TableView}
     */
    private void addTable() {
        removeTable(panel);
        ArrayList<Product> products = businessLogic.getAllProducts();
        DefaultTableModel tableModel = createTableModel(p,products);
        table = new JTable(tableModel);
        table.setDefaultEditor(Object.class, null);

        table.getSelectionModel().addListSelectionListener(e -> {
            int selectedRow = table.getSelectedRow();
            if (selectedRow != -1) {
                selected = products.get(selectedRow);
                displayData(selected, textFields);
            }
        });

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(50, 10, 800, 200);
        panel.add(scrollPane);

        addFields(panel, p,textFields);
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
