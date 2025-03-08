package presentation;


import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Field;
import java.util.ArrayList;

/**
 *  Pagina responsabila de crearea tabelelor cu reflection pentru
 *  eliminarea codului duplicat din celalalte clase care folosesc tabele
 *  Contine metode pentru :
 *  1. generarea header-ului tabelului pe baza campurilor dintr-o clasa
 *  2. adaugarea in componente a informatilor selectate din tabel
 *  3. stergerea tabelelor din scrollPane-urile existente pentru refresh
 *
 */
public class TableView extends JDialog implements ActionListener {
    /**
     * constructor default
     */
    public TableView(){}
    /**
     * Creeaza un model de tabel cu reflection pentru a putea fi utilizat
     * de clase diferite
     * @param object clasa din care vrem sa preluam campurile
     * @param items toate obiectele (de tipul clasei) din tabel pentru afisare completa
     * @return continutul unui tabel, de tip DefaultTableModel
     */
    public DefaultTableModel createTableModel(Object object, ArrayList<?> items) {
        String[] columnNames = new String[object.getClass().getDeclaredFields().length];
        int i = 0;
        for (Field field : object.getClass().getDeclaredFields()) {
            field.setAccessible(true);
            columnNames[i] = field.getName();
            i++;
        }
        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);

        for (Object item : items) {
            Object[] rowData = new Object[columnNames.length];
            int j = 0;
            for (Field field : object.getClass().getDeclaredFields()) {
                field.setAccessible(true);
                try {
                    rowData[j] = field.get(item);
                } catch (IllegalAccessException e) {
                    JOptionPane.showMessageDialog(null, "Error retrieving field!", "Fail", JOptionPane.ERROR_MESSAGE);
                }
                j++;
            }
            tableModel.addRow(rowData);
        }
        return tableModel;
    }

    /**
     * Adauga componente de tip JTextField pentru a instantia locul unde se
     * afiseaza informatiile obiectului selectat din tabel
     * @param panel panelul principal in care vrem sa adaugam componentele
     * @param object tipul clasei pentru a putea accesa campurile
     * @param textFields arrayList de JTextField pentru accesarea acestor componente deja
     *                   declarate din interfata grafica existenta
     */
    public void addFields(JPanel panel, Object object, ArrayList<JTextField> textFields) {
        int labelX = 50;
        int fieldX = 150;
        int y = 250;
        int labelWidth = 100;
        int fieldWidth = 200;
        int height = 30;

        int i = 0;
        for (Field field : object.getClass().getDeclaredFields()) {
            JLabel label = new JLabel(field.getName() + ":");
            label.setBounds(labelX, y, labelWidth, height);
            label.setForeground(new Color(223, 225, 229));
            label.setFont(new Font("Cambria",Font.BOLD,15));
            panel.add(label);
            JTextField textField = new JTextField();

            textField.setBounds(fieldX, y, fieldWidth, height);
            textFields.add(textField);
            panel.add(textFields.get(i));
            i++;

            y += 40; //vertical spacing
        }
        textFields.get(0).setEditable(false);

    }

    /**
     * Adauga datele stocate in object in textFieldurile trimise ca paramentru
     * @param object pentru a putea accesa campurile si valorile
     * @param textFields arrayList de JTextField pentru accesarea acestor componente deja
     *      *                   declarate din interfata grafica existenta
     */
    public void displayData(Object object, ArrayList<JTextField> textFields) {
        int i= 0;
        for (Field field : object.getClass().getDeclaredFields()) {
            field.setAccessible(true); // set modifier to public
            Object value;
            try {
                value = field.get(object);
                textFields.get(i).setText(value.toString());
                i++;
            } catch (IllegalArgumentException | IllegalAccessException e) {
                JOptionPane.showMessageDialog(null, "Error displaying field!", "Fail", JOptionPane.ERROR_MESSAGE);
            }

        }
    }

    /**
     * sterge tabelele existente pentru a le reincarca dupa
     * modificarea continutului acestora in urma operatilor efectuate
     * @param panel panelul principal in care se afla tabelele de sters
     */
    public void removeTable(JPanel panel){
        Component[] components = panel.getComponents();
        for (Component component : components) {
            if (component instanceof JScrollPane) {
                panel.remove(component);
                break;
            }
        }
    }
    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
