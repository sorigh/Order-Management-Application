package presentation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *  Prima pagina din interfata grafica, prezentand 4 butoane pentru cele 4
 * categorii de manipulare a database-ului cu produse, clienti si comenzi.
 * Functii butoane:
 * Buton 1: pagina pentru gestionarea clientilor
 * Buton 2: pagina pentru gestionarea produselor
 * Buton 3: pagina pentru gestionarea comenzilor
 * Buton 4: pagina pentru vizualizarea obiectelor de tip Bill
 */
public class View extends JDialog implements ActionListener{

    /**
     * Buton 1: pagina pentru gestionarea clientilor
     */
    private JButton clientOpButton;
    /**
     * Buton 2: pagina pentru gestionarea produselor
     */
    private JButton productOpButton;
    /**
     * Buton 3: pagina pentru gestionarea comenzilor
     */
    private JButton purchaseButton;
    /**
     * Buton 4: pagina pentru vizualizarea obiectelor de tip Bill
     */
    private JButton logTableButton;

    /**
     * Constructor principal al ferestrei principale
     * @param parent frame-ul parinte in raport cu care este afisata
     */
    public View(Frame parent){
            super(parent);
            setTitle("Order Management");
            //panel
            JPanel mainPanel = new JPanel();
            setContentPane(mainPanel);
            setMinimumSize(new Dimension(320, 300));
            setModal(true);
            setLocationRelativeTo(parent);
            setDefaultCloseOperation(DISPOSE_ON_CLOSE);
            setResizable(false);
            mainPanel.setBackground(new Color(87,108,133));
            mainPanel.setLayout(null);

            addContent();
            clientOpButton.addActionListener(e -> openClientView());

            productOpButton.addActionListener(e -> openProductView());

            purchaseButton.addActionListener(e -> openOrderView());

            logTableButton.addActionListener(e -> openBill());
            this.setVisible(true);
        }
    /**
     * Deschide fereastra responsabila cu vizualizarea tabelului Log
     */
    private void openBill() {
        new BillView();
    }
    /**
     * Deschide fereastra responsabila cu vizualizarea clientilor
     */
    private void openClientView() {
        new CustomerOperationsView(null);
    }
    /**
     * Deschide fereastra responsabila cu vizualizarea produselor
     */
    private void openProductView() {
        new ProductOperationsView(null);
    }
    /**
     * Deschide fereastra responsabila cu plasarea unei comenzi
     */
    private void openOrderView() {
        new CreatePurchaseView(null);
    }


    /**
     * Adauga butoanele in panelul principal al ferestrei
     */
    private void addContent(){
        clientOpButton = new JButton("Customer Operations");
        productOpButton = new JButton("Product Operations");
        purchaseButton = new JButton("Order Placement");
        logTableButton = new JButton("Log Table");

        clientOpButton.setBounds(50, 30, 200, 30);
        clientOpButton.setFont(new Font("Cambria",Font.BOLD,15));
        clientOpButton.setForeground(new Color(223, 225, 229));
        clientOpButton.setBackground(new Color(93,64,27));

        productOpButton.setBounds(50, 70, 200, 30);
        productOpButton.setFont(new Font("Cambria",Font.BOLD,15));
        productOpButton.setForeground(new Color(223, 225, 229));
        productOpButton.setBackground(new Color(93,64,27));


        purchaseButton.setBounds(50, 110, 200, 30);
        purchaseButton.setFont(new Font("Cambria",Font.BOLD,15));
        purchaseButton.setForeground(new Color(223, 225, 229));
        purchaseButton.setBackground(new Color(93,64,27));

        logTableButton.setBounds(50, 150, 200, 30);
        logTableButton.setFont(new Font("Cambria",Font.BOLD,15));
        logTableButton.setForeground(new Color(223, 225, 229));
        logTableButton.setBackground(new Color(93,64,27));

            add(clientOpButton);
            add(productOpButton);
            add(purchaseButton);
            add(logTableButton);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
    }
}
