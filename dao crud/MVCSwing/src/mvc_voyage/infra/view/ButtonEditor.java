/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mvc_voyage.infra.view;

import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.util.Objects;
import javax.swing.AbstractCellEditor;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.TableCellEditor;

/**
 *
 * @author macbook
 */
public class ButtonEditor extends AbstractCellEditor implements TableCellEditor {
    private final JPanel panel;
    private final JTable table;
    private ActionListener editActionListener;
    private ActionListener listener;
    private ActionListener deleteActionListener;
    private int currentRow; // Stocke la ligne actuelle

    public ButtonEditor(JTable table) {
        this.table = table;
        panel = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 5));
        JButton[] btns = ButtonRenderer.actionBtns();

        // Bouton MODIFIER
        btns[0].addActionListener(e -> {
            stopCellEditing(); // Notifie la table que l'édition est finie
            if (editActionListener != null) {
                // On passe un événement qui contient la ligne correcte
                editActionListener.actionPerformed(new java.awt.event.ActionEvent(table, currentRow, "edit"));
            }
        });

        // Bouton SUPPRIMER
        btns[1].addActionListener(e -> {
            stopCellEditing();
            if (deleteActionListener != null) {
                deleteActionListener.actionPerformed(new java.awt.event.ActionEvent(table, currentRow, "delete"));
            }
        });

        panel.add(btns[0]);
        panel.add(btns[1]);
    }

    public void setEditActionListener(ActionListener editActionListener) {
        this.editActionListener = editActionListener;
        
    }

    public void setDeleteActionListener(ActionListener deleteActionListener) {
        this.deleteActionListener = deleteActionListener;
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        return panel;
    }

    @Override
    public Object getCellEditorValue() {
        return null;
    }
}


