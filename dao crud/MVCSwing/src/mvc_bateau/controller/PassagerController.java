/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mvc_bateau.controller;


import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;
import mvc_bateau.dao.BateauDAO;
import mvc_bateau.infra.view.ButtonEditor;
import mvc_bateau.infra.view.ButtonRenderer;
import mvc_bateau.model.Bateau;
import mvc_bateau.view.PassagerView;
import mvc_bateau.view.FicheBateauView;

import java.util.List;

import java.util.List;
import mvc_bateau.dao.PassagerDAO;
import mvc_bateau.model.Passager;
import mvc_bateau.view.FichePassagerView;



public class PassagerController {
	private final List<Passager> model;
    private final PassagerView view;
    private final PassagerDAO dao;
   
    /**
     *
     * @param m
     * @param v
     * @param d
     */
    public PassagerController(List<Passager> m, PassagerView v, PassagerDAO d){
       model = m;
       view = v;
       dao = d;
       initView();
    }
   
    public void initView(){
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
    private void refreshTable(List<Passager> model) {
        SwingUtilities.invokeLater(() -> {
            DefaultTableModel tableModel = (DefaultTableModel) view.getjTable1().getModel();
            tableModel.setRowCount(0);
            for(Passager b : model) {
                tableModel.addRow(b.toTableRow());
            }
        });
    }
   
    public void initController(){
        // Events Boutons "Actions"
        ActionListener editActionListener = (e) -> {
            int selectedRow = view.getjTable1().getSelectedRow();
            modifier(selectedRow);
        };
        ActionListener deleteActionListener = (e) -> {
            int selectedRow = view.getjTable1().getSelectedRow();
            if(JOptionPane.OK_OPTION == JOptionPane.showConfirmDialog(view, "Voulez-vous vraiment supprimer ce passager ?")) {
                try {
                    supprimer(selectedRow);
                } catch (SQLException ex) {
                    Logger.getLogger(PassagerController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        };
        // Boutons "Actions"
        ButtonRenderer btnRend = (ButtonRenderer) view.getjTable1().getColumn("Actions").getCellRenderer();
        btnRend.setEditActionListener(editActionListener);
        btnRend.setDeleteActionListener(deleteActionListener);
        ButtonEditor btnEditor = (ButtonEditor) view.getjTable1().getColumn("Actions").getCellEditor();
        btnEditor.setEditActionListener(editActionListener);
        btnEditor.setDeleteActionListener(deleteActionListener);
       // Bouton Nouveau
       view.getBtnajouterPassager().addActionListener(e -> nouveau());
    }
    
    /**
     * Modifier une ligne de la table
     */
    private void modifier(int tableRow) {
        // Récupération de l'ID
        Long id = (Long) view.getjTable1().getValueAt(tableRow, 0);
        // Afficher la vue en modale
        FichePassagerView editView = new FichePassagerView();
        FichePassagerController controller = new FichePassagerController(
                dao.selectById(id), 
                editView, 
                dao, 
                Boolean.FALSE, // MAJ
                (e)-> {
                    // Au succès de la modification -> actualiser la ligne
                    SwingUtilities.invokeLater(() -> {
                        DefaultTableModel tableModel = (DefaultTableModel) view.getjTable1().getModel();
                        int i = 0;
                        for(Object obj : ((FichePassagerController) e.getSource()).getModel().toTableRow()) {
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
    private void supprimer(int tableRow) throws SQLException {
        // Récupération de l'ID
        Long id = (Long) view.getjTable1().getValueAt(tableRow, 0);
        String modelTitle = (String) view.getjTable1().getValueAt(tableRow, 1);
        // Suppression DAO
        dao.delete(id);
        // Messages
        JOptionPane.showMessageDialog(
                view,
                "Suppression du passager "+ modelTitle + " effectuée avec succès");
        // Retirer la ligne de la table
        SwingUtilities.invokeLater(() -> {
            ((DefaultTableModel) view.getjTable1().getModel()).removeRow(tableRow);
        });
    }
   
    /**
     * Nouvel enregistrement
     */
    private void nouveau() {
        FichePassagerView newView = new FichePassagerView();
        FichePassagerController controller = new FichePassagerController(
                new Passager(), 
                newView, 
                dao, 
                Boolean.TRUE, // Création
                (e)-> {
                    // Au succès de l'enregistrement -> ajout à la table
                    SwingUtilities.invokeLater(() -> {
                        DefaultTableModel tableModel = (DefaultTableModel) view.getjTable1().getModel();
                        tableModel.addRow(((FichePassagerController) e.getSource()).getModel().toTableRow());
                    });
                });
        controller.initController();
        newView.setVisible(true);
    }
    
    
}

