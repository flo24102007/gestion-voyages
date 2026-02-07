/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mvc_voyage.infra.view;

import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.util.Objects;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

/**
 *
 * @author user
 */
public class ButtonRenderer extends JPanel implements TableCellRenderer {

    private ActionListener editActionListener;
    private ActionListener deleteActionListener;
    private final JButton btnEdit = new JButton();
    private final JButton btnDelete = new JButton();
    private final JButton btnDuree = new JButton(); // Le bouton pour la durée

    public ButtonRenderer() {
        setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

        JButton[] btns = actionBtns();

        btns[0].addActionListener(e -> {
            if (Objects.nonNull(editActionListener)) {
                editActionListener.actionPerformed(e);
            }
        });

        btns[1].addActionListener(e -> {
            if (Objects.nonNull(deleteActionListener)) {
                deleteActionListener.actionPerformed(e);
            }
        });

        add(btns[0]);
        add(btns[1]);
        try {
            btnEdit.setIcon(new javax.swing.ImageIcon(
                    getClass().getResource("/mvc_bateau/icons/edit-icon.png")));
            btnDelete.setIcon(new javax.swing.ImageIcon(
                    getClass().getResource("/mvc_bateau/icons/data-delete-icon.png")));
        } catch (Exception e) {
            // Backup texte si les icônes ne sont pas trouvées
            btnEdit.setText("Edit");
            btnDelete.setText("Del");
        }

        btnEdit.setToolTipText("Modifier le voyage");
        btnDelete.setToolTipText("Supprimer le voyage");
        
        // On rend les boutons non-focusables pour un rendu plus propre
        btnEdit.setFocusable(false);
        btnDelete.setFocusable(false);
        btnDuree.setFocusable(false);
    }

    public static JButton[] actionBtns() {
        JButton btnEdit = new JButton();
        btnEdit.setToolTipText("Modifier le voyage");

        JButton btnDelete = new JButton();
        btnDelete.setToolTipText("Supprimer le voyage");

        btnEdit.setIcon(new javax.swing.ImageIcon(
                ButtonRenderer.class.getResource("/mvc_bateau/icons/edit-icon.png")));

        btnDelete.setIcon(new javax.swing.ImageIcon(
                ButtonRenderer.class.getResource("/mvc_bateau/icons/data-delete-icon.png")));

        return new JButton[]{btnEdit, btnDelete};
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value,
            boolean isSelected, boolean hasFocus, int row, int column) {

        // Nettoyer le panel avant de dessiner
        this.removeAll();

        if (column == 8) {
            // --- MODE DURÉE (Un seul bouton texte) ---
            String texte = (value == null || value.toString().isEmpty()) ? "Calculer" : value.toString();
            btnDuree.setText(texte);
            btnDuree.setIcon(null);
            add(btnDuree);
        } else {
            // --- MODE ACTIONS (Deux boutons icônes) ---
            add(btnEdit);
            add(btnDelete);
        }

        // Gérer la couleur de sélection
        if (isSelected) {
            setBackground(table.getSelectionBackground());
        } else {
            setBackground(table.getBackground());
        }

        return this;
    }
    
    public void setEditActionListener(ActionListener l) {}
    public void setDeleteActionListener(ActionListener l) {}
}
