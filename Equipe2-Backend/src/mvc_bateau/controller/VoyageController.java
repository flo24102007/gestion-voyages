/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mvc_bateau.controller;

import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;
import mvc_bateau.infra.view.ButtonEditor;
import mvc_bateau.infra.view.ButtonRenderer;

class VoyageController {

    private final List<Voyage> model;
    private final VoyageView view;
    private final VoyageDAO dao;

    public VoyageController(List<Voyage> m, VoyageView v, VoyageDAO d) {
        model = m;
        view = v;
        dao = d;
        initView();
    }

    public void initView() {
        // Données de vue depuis le model
        refreshTable();
    }

    /**
     * Actualiser une table
     */
    public void refreshTable() {
        refreshTable(model);
    }

    /**
     * Actualiser une table à partir d'un objet model
     */
    private void refreshTable(List<Voyage> model) {
        SwingUtilities.invokeLater(() -> {
            DefaultTableModel tableModel = (DefaultTableModel) view.getTbListeVoyages().getModel();
            tableModel.setRowCount(0);
            for (Voyage b : model) {
                tableModel.addRow(b.toTableRow());
            }
        });
    }

    public void initController() {
        // Events Boutons "Actions"
        ActionListener editActionListener = (e) -> {
            int selectedRow = view.getTbListeVoyagesx().getSelectedRow();
            modifier(selectedRow);
        };
        ActionListener deleteActionListener = (e) -> {
            int selectedRow = view.getTbListeVoyages().getSelectedRow();
            if (JOptionPane.OK_OPTION == JOptionPane.showConfirmDialog(view, "Voulez-vous vraiment supprimer ce Voyage ?")) {
                supprimer(selectedRow);
            }
        };
        // Boutons "Actions"
        ButtonRenderer btnRend = (ButtonRenderer) view.getTbListeVoyages().getColumn("Actions").getCellRenderer();
        btnRend.setEditActionListener(editActionListener);
        btnRend.setDeleteActionListener(deleteActionListener);
        ButtonEditor btnEditor = (ButtonEditor) view.getTbListeVoyages().getColumn("Actions").getCellEditor();
        btnEditor.setEditActionListener(editActionListener);
        btnEditor.setDeleteActionListener(deleteActionListener);
        // Bouton Nouveau
        view.getBtNouveau().addActionListener(e -> nouveau());
    }

    /**
     * Modifier une ligne de la table
     */
    private void modifier(int tableRow) {
        // Récupération de l'ID
        Long id = (Long) view.getTbListeVoyages().getValueAt(tableRow, 0);
        // Afficher la vue en modale
        FicheVoyageView editView = new FicheVoyageView();
        FicheVoyageController controller = new FicheVoyageController(
                dao.selectById(id),
                editView,
                dao,
                Boolean.FALSE, // MAJ
                (e) -> {
                    // Au succès de la modification -> actualiser la ligne
                    SwingUtilities.invokeLater(() -> {
                        DefaultTableModel tableModel = (DefaultTableModel) view.getTbListeVoyages().getModel();
                        int i = 0;
                        for (Object obj : ((FicheVoyageController) e.getSource()).getModel().toTableRow()) {
                            tableModel.setValueAt(obj, tableRow, i++);
                        }
                    });
                });
        controller.initController();
        editView.setVisible(true);
    }

    /**
     * Supprimer une ligne de la table
     */
    private void supprimer(int tableRow) {
        // Récupération de l'ID
        Long id = (Long) view.getTbListeVoyages().getValueAt(tableRow, 0);
        String modelTitle = (String) view.getTbListeVoyage().getValueAt(tableRow, 1);
        // Suppression DAO
        dao.delete(id);
        // Messages
        JOptionPane.showMessageDialog(
                view,
                "Suppression du Voyage " + modelTitle + " effectuée avec succès");
        // Retirer la ligne de la table
        SwingUtilities.invokeLater(() -> {
            ((DefaultTableModel) view.getTbListeVoyage().getModel()).removeRow(tableRow);
        });
    }

    /**
     * Nouvel enregistrement
     */
    private void nouveau() {
        FicheVoyageView newView = new FicheVoyageView();
        FicheVoyageController controller = new FicheVoyageController(
                new Voyage(),
                newView,
                dao,
                Boolean.TRUE, // Création
                (e) -> {
                    // Au succès de l'enregistrement -> ajout à la table
                    SwingUtilities.invokeLater(() -> {
                        DefaultTableModel tableModel = (DefaultTableModel) view.getTbListeVoyage().getModel();
                        tableModel.addRow(((FicheVoyageController) e.getSource()).getModel().toTableRow());
                    });
                });
        controller.initController();
        newView.setVisible(true);
    }

}
