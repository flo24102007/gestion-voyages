/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mvc_voyage.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import mvc_voyage.DAO.VoyageDAO;
import mvc_voyage.model.Voyage;
import mvc_voyage.view.VoyageView;
import mvc_voyage.view.FicheVoyageView;

public class VoyageController {

    private final List<Voyage> modelList;
    private final VoyageView view;
    private final VoyageDAO dao;

    public VoyageController(List<Voyage> modelList, VoyageView view, VoyageDAO dao) {
        this.modelList = modelList;
        this.view = view;
        this.dao = dao;
    }

    public void initController() {
        loadData();
        view.getBtNouveau().addActionListener(e -> nouveauVoyage());

        // 1. On crée une SEULE instance de Renderer et d'Editor
        mvc_voyage.infra.view.ButtonRenderer renderer = new mvc_voyage.infra.view.ButtonRenderer();
        mvc_voyage.infra.view.ButtonEditor editor = new mvc_voyage.infra.view.ButtonEditor(view.getTbListeVoyages());

        // 2. On définit les actions UNE SEULE FOIS pour les deux
        ActionListener editLogique = e -> modifierVoyage(e);
        ActionListener deleteLogique = e -> supprimerVoyage(e);

        renderer.setEditActionListener(editLogique);
        renderer.setDeleteActionListener(deleteLogique);

        editor.setEditActionListener(editLogique); // TRÈS IMPORTANT : L'éditeur doit avoir la logique !
        editor.setDeleteActionListener(deleteLogique);

        // 3. On applique à la colonne
        view.getTbListeVoyages().getColumn("Actions").setCellRenderer(renderer);
        view.getTbListeVoyages().getColumn("Actions").setCellEditor(editor);
    }

    private void loadData() {
        DefaultTableModel tableModel = (DefaultTableModel) view.getTbListeVoyages().getModel();
        tableModel.setRowCount(0);

        for (Voyage voyage : dao.selectAll()) {
//            voyage.setDuree(voyage.calculerDuree());
            tableModel.addRow(voyage.toTableRow());
        }
    }

    private void nouveauVoyage() {
        Voyage nouveauVoyage = new Voyage();
        FicheVoyageView ficheView = new FicheVoyageView();
        FicheVoyageController ficheController = new FicheVoyageController(
                nouveauVoyage,
                ficheView,
                dao,
                true,
                e -> loadData()
        );
        ficheController.initController();
        ficheView.setVisible(true);
    }

    private void modifierVoyage(ActionEvent e) {
        // 1. Récupérer l'index de la ligne (passé via e.getID() par notre Editor)
        int row = e.getID();

        // Sécurité : si le row est invalide, on tente de récupérer la ligne sélectionnée
        if (row < 0) {
            row = view.getTbListeVoyages().getSelectedRow();
        }

        if (row >= 0) {
            try {
                // 2. Récupérer l'ID du voyage (Colonne 0, même si elle est cachée)
                Long id = (Long) view.getTbListeVoyages().getValueAt(row, 0);

                // 3. Charger l'objet complet depuis la base de données via le DAO
                Voyage voyage = dao.selectById(id);

                if (voyage != null) {
                    // 4. Ouvrir la FicheVoyage en mode "Modification"
                    FicheVoyageView ficheView = new FicheVoyageView();

                    // On passe 'false' pour indiquer que ce n'est PAS un nouveau voyage
                    FicheVoyageController ficheController = new FicheVoyageController(
                            voyage,
                            ficheView,
                            dao,
                            false, // isNouveau = false
                            ev -> loadData() // Callback pour rafraîchir la table après sauvegarde
                    );

                    ficheController.initController();

                    // Optionnel : On peut remplir les champs de la vue ici ou dans le initController de la fiche
                    ficheView.setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(view, "Erreur : Voyage introuvable en base.", "Erreur", JOptionPane.ERROR_MESSAGE);
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(view, "Erreur lors de la récupération : " + ex.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
                ex.printStackTrace();
            }
        } else {
            JOptionPane.showMessageDialog(view, "Veuillez sélectionner une ligne.", "Avertissement", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void supprimerVoyage(ActionEvent e) {
        // On récupère la ligne stockée dans l'ID de l'action (envoyé par mon ButtonEditor précédent)
        int row = e.getID();

        // Si row est -1 (cas imprévu), on tente le secours, sinon on utilise row
        int finalRow = (row >= 0) ? row : view.getTbListeVoyages().getSelectedRow();

        if (finalRow >= 0) {
            int confirmation = JOptionPane.showConfirmDialog(view, "Supprimer ce voyage ?", "Confirmation", JOptionPane.YES_NO_OPTION);
            if (confirmation == JOptionPane.YES_OPTION) {
                Long id = (Long) view.getTbListeVoyages().getValueAt(finalRow, 0);
                dao.delete(id);
                loadData();
            }
        } else {
            JOptionPane.showMessageDialog(view, "Erreur : Voyage introuvable en base.", "Erreur", JOptionPane.ERROR_MESSAGE);
        }
    }
}
