/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mvc_voyage.infra.view;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;
import mvc_voyage.infra.view.ButtonEditor;
import mvc_voyage.infra.view.ButtonRenderer;


/**
 *
 * @author user
 */
public class JTableWithActions {
      // Pour test rapide
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new JTableVoyageWithActions().createAndShowGUI());
    }

    public void createAndShowGUI() {
        JFrame frame = new JFrame("Liste des Voyages");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Colonnes
        String[] columnNames = {
            "ID",
            "Référence",
            "Date départ",
            "Date arrivée",
            "Bateau",
            "Actions"
        };

        // Données fictives
        Object[][] data = {
            {1L, "VYG-001", "2025-02-01", "2025-02-03", 1L, null},
            {2L, "VYG-002", "2025-02-05", "2025-02-07", 2L, null},
            {3L, "VYG-003", "2025-02-10", "2025-02-12", 1L, null}
        };

        DefaultTableModel model = new DefaultTableModel(data, columnNames) {
            @Override
            public boolean isCellEditable(int row, int column) {
                // Seule la colonne Actions est éditable
                return column == 5;
            }
        };

        JTable table = new JTable(model);
        table.setRowHeight(40);

        // Renderer + Editor pour Actions
        table.getColumn("Actions")
             .setCellRenderer(new ButtonRenderer());

        table.getColumn("Actions")
             .setCellEditor(new ButtonEditor(table));

        frame.add(new JScrollPane(table));
        frame.setSize(800, 300);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}


