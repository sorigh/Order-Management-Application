package presentation;

import bll.BillBLL;
import model.Bill;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

/**
 *  Clasa responsabila de afisarea, elementelor de tip record
 *  existente in tabela din database.
 *  Extinde TableView pentru referirea simplificata asupra tabelelor
 *  insa utilizeaza alte metode care privesc preluarea datelor pentru formarea
 *  tabelului
 */
public class BillView extends TableView {
    private final BillBLL billBLL = new BillBLL();
    private final JPanel panel;

    /**
     * constructor principal ptr initializarea ferestrei cu records
     */
    public BillView() {

        setTitle("Log Table");
        setSize(830, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        setModal(true);
        panel = new JPanel();
        setContentPane(panel);
        panel.setBackground(new Color(87, 108, 133));
        panel.setLayout(null);
        addTable(10, 10);
        this.setVisible(true);

    }

    /**
     * table model diferit pentru a a functiona cu elemente de tip Bill
     * @param items elementele de amplasat in tabel
     * @return table model
     */
    public static DefaultTableModel createTableModel(ArrayList<Bill> items) {
    String[] columnNames = { "PurchaseID", "Customer Name", "Product Name", "Price", "Amount", "Total" };
    DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);

    for (Bill item : items) {
        Object[] rowData = {
                item.purchaseID(),
                item.customerName(),
                item.productName(),
                item.price(),
                item.amount(),
                item.total()
        };
        tableModel.addRow(rowData);
    }
    return tableModel;
    }

    /**
     * Adauga un tabel in panel in functie de coordonatele date
     * @param x coordonata x
     * @param y coordonata y
     */
    private void addTable(int x, int y) {
        ArrayList<Bill> items = billBLL.getAllBills();

        DefaultTableModel t = createTableModel(items);
        JTable table = new JTable(t);
        table.setDefaultEditor(Object.class, null);

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(x, y, 800, 150);
        panel.add(scrollPane);
    }
}