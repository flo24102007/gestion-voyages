package mvc_bateau.controller;

import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;
import mvc_bateau.dao.VoyageDAO;
import mvc_bateau.infra.view.ButtonEditor;
import mvc_bateau.infra.view.ButtonRenderer;
import mvc_bateau.model.Voyage;
import mvc_bateau.view.FicheVoyageView;
import mvc_bateau.view.VoyageView;

public class VoyageController {

    private final List<Voyage> model;
    private final VoyageView view;
    private final VoyageDAO dao;

    public VoyageController(List<Voyage> m, VoyageView v, VoyageDAO d) {
        this.model = m;
        this.view = v;
        this.dao = d;
        initView();
    }

    public void initView() {
        refreshTable();
    }

    public void refreshTable() {
        SwingUtilities.invokeLater(() -> {
            DefaultTableModel tableModel = (DefaultTableModel) view.getTbListeVoyages().getModel();
            tableModel.setRowCount(0);
            for (Voyage v : model) {
                // toTableRow() appelle maintenant nos getters hybrides formatés
                tableModel.addRow(v.toTableRow());
            }
        });
    }

    public void initController() {
        ActionListener editActionListener = (e) -> {
            int selectedRow = view.getTbListeVoyages().getSelectedRow();
            if (selectedRow != -1) modifier(selectedRow);
        };

        ActionListener deleteActionListener = (e) -> {
            int selectedRow = view.getTbListeVoyages().getSelectedRow();
            if (selectedRow != -1 && JOptionPane.showConfirmDialog(view, "Supprimer ce voyage ?") == JOptionPane.OK_OPTION) {
                try {
                    supprimer(selectedRow);
                } catch (SQLException ex) {
                    Logger.getLogger(VoyageController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        };

        setupTableButtons(editActionListener, deleteActionListener);
        view.getNouveau().addActionListener(e -> nouveau());
    }

    private void setupTableButtons(ActionListener edit, ActionListener delete) {
        // On récupère les colonnes pour injecter les boutons d'action
        ButtonRenderer btnRend = (ButtonRenderer) view.getTbListeVoyages().getColumn("Action").getCellRenderer();
        btnRend.setEditActionListener(edit);
        btnRend.setDeleteActionListener(delete);

        ButtonEditor btnEditor = (ButtonEditor) view.getTbListeVoyages().getColumn("Action").getCellEditor();
        btnEditor.setEditActionListener(edit);
        btnEditor.setDeleteActionListener(delete);
    }

    private void modifier(int tableRow) {
        // On récupère l'ID caché dans la première colonne (0)
        Long id = (Long) view.getTbListeVoyages().getValueAt(tableRow, 0);
        
        // IMPORTANT : On recharge l'objet frais depuis le DAO
        Voyage voyageAmodifier = dao.selectById(id);

        FicheVoyageView editView = new FicheVoyageView();
        FicheVoyageController controller = new FicheVoyageController(
                voyageAmodifier,
                editView,
                dao,
                Boolean.FALSE, 
                (e) -> {
                    // CALLBACK au succès de la modif
                    SwingUtilities.invokeLater(() -> {
                        DefaultTableModel tableModel = (DefaultTableModel) view.getTbListeVoyages().getModel();
                        // On récupère le modèle mis à jour depuis le FicheVoyageController
                        Voyage updatedModel = ((FicheVoyageController) e.getSource()).getModel();
                        Object[] rowData = updatedModel.toTableRow();
                        
                        // Mise à jour cellule par cellule pour garder l'heure formatée
                        for (int i = 0; i < rowData.length; i++) {
                            tableModel.setValueAt(rowData[i], tableRow, i);
                        }
                    });
                });
        controller.initController();
        editView.setVisible(true);
    }

    private void nouveau() {
        FicheVoyageView newView = new FicheVoyageView();
        FicheVoyageController controller = new FicheVoyageController(
                new Voyage(), // Nouvel objet vide
                newView,
                dao,
                Boolean.TRUE, 
                (e) -> {
                    // CALLBACK au succès de la création
                    SwingUtilities.invokeLater(() -> {
                        DefaultTableModel tableModel = (DefaultTableModel) view.getTbListeVoyages().getModel();
                        Voyage createdModel = ((FicheVoyageController) e.getSource()).getModel();
                        tableModel.addRow(createdModel.toTableRow());
                    });
                });
        controller.initController();
        newView.setVisible(true);
    }

    private void supprimer(int tableRow) throws SQLException {
        Long id = (Long) view.getTbListeVoyages().getValueAt(tableRow, 0);
        dao.delete(id);
        SwingUtilities.invokeLater(() -> {
            ((DefaultTableModel) view.getTbListeVoyages().getModel()).removeRow(tableRow);
        });
    }
}