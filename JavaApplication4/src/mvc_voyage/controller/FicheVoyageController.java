/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mvc_voyage.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.Objects;
import javax.swing.JOptionPane;
import mvc_voyage.DAO.VoyageDAO;
import mvc_voyage.model.Voyage;
import mvc_voyage.view.FicheVoyageView;

public class FicheVoyageController {

    private final Voyage model;
    private final FicheVoyageView view;
    private final VoyageDAO dao;
    private final boolean isCreateForm;
    private final ActionListener onSuccess;

    public FicheVoyageController(Voyage m, FicheVoyageView v, VoyageDAO d,
            boolean isCreateForm, ActionListener onSuccess) {
        this.model = m;
        this.view = v;
        this.dao = d;
        this.isCreateForm = isCreateForm;
        this.onSuccess = onSuccess;
        initView();
    }

    public void initView() {
        view.getTfLieuDepart().setText(model.getLieuDepart());
        view.getTfLieuArrive().setText(model.getLieuArrive());
        if (model.getDateHeureDepart() != null) {
            view.getDateDepart().setDate(model.getDateHeureDepart());
        }
        if (model.getDateHeureArrive() != null) {
            view.getDateArrive().setDate(model.getDateHeureArrive());
        }
        view.getTfPrix().setText(model.getPrix() != null ? model.getPrix().toString() : "");
        view.getTfSiegesReserves().setText(model.getSiegeReserver() != null ? model.getSiegeReserver().toString() : "");
        view.getTfBateauId().setText(model.getBateauId() != null ? model.getBateauId().toString() : "");

        // Calculer et afficher la durée si les dates existent
//        if (model.getDateHeureDepart() != null && model.getDateHeureArrive() != null) {
//            model.setDuree(model.calculerDuree());
//            view.getTfDuree().setText(model.getDuree());
//        }
    }

    public void initController() {
        view.getBtEnregistrer().addActionListener(e -> enregistrer());
        view.getBtCalculerDuree().addActionListener(e -> calculerDuree());
    }

    private void calculerDuree() {
        java.util.Date depart = view.getDateDepart().getDate();
        java.util.Date arrive = view.getDateArrive().getDate();

        if (depart != null && arrive != null) {
            long diff = arrive.getTime() - depart.getTime();
            long hours = diff / (1000 * 60 * 60);
            long minutes = (diff % (1000 * 60 * 60)) / (1000 * 60);
            String duree = String.format("%02d:%02d", hours, minutes);
            view.getTfDuree().setText(duree);
        } else {
            JOptionPane.showMessageDialog(view, "Veuillez sélectionner les dates de départ et d'arrivée.", "Information", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void enregistrer() {
        try {
            model.setLieuDepart(view.getTfLieuDepart().getText());
            model.setLieuArrive(view.getTfLieuArrive().getText());
            model.setDateHeureDepart(view.getDateDepart().getDate());
            model.setDateHeureArrive(view.getDateArrive().getDate());
            model.setPrix(Integer.parseInt(view.getTfPrix().getText()));
            model.setSiegeReserver(Integer.parseInt(view.getTfSiegesReserves().getText()));
            model.setBateauId(Integer.parseInt(view.getTfBateauId().getText()));

            // Calculer la durée
//            if (model.getDateHeureDepart() != null && model.getDateHeureArrive() != null) {
//                model.setDuree(model.calculerDuree());
//            }
            validationControl(model);

            if (isCreateForm) {
                    model.setId(dao.create(model));
                    JOptionPane.showMessageDialog(null, "Enregistrement du voyage effectué avec succès", "Enregistrement", JOptionPane.INFORMATION_MESSAGE);                

            } else {
                dao.update(model);
                JOptionPane.showMessageDialog(null, "Modification du voyage effectué avec succès", "Enregistrement", JOptionPane.INFORMATION_MESSAGE);
            }
            view.setVisible(false);
            if (Objects.nonNull(onSuccess)) {
                onSuccess.actionPerformed(new ActionEvent(this, 0, null));
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Veuillez entrer des nombres valides pour le prix, sièges réservés et ID bateau.", "Erreur", JOptionPane.ERROR_MESSAGE);
        } catch (RuntimeException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erreur lors de l'enregistrement : " + ex.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void validationControl(Voyage model) {
        if (Objects.isNull(model.getLieuDepart()) || model.getLieuDepart().isBlank()) {
            throw new RuntimeException("Le lieu de départ est obligatoire");
        }
        if (Objects.isNull(model.getLieuArrive()) || model.getLieuArrive().isBlank()) {
            throw new RuntimeException("Le lieu d'arrivée est obligatoire");
        }
        if (Objects.isNull(model.getDateHeureDepart())) {
            throw new RuntimeException("La date de départ est obligatoire");
        }
        if (Objects.isNull(model.getDateHeureArrive())) {
            throw new RuntimeException("La date d'arrivée est obligatoire");
        }
        if (model.getDateHeureArrive().before(model.getDateHeureDepart())) {
            throw new RuntimeException("La date d'arrivée doit être après la date de départ");
        }
        if (Objects.isNull(model.getPrix()) || model.getPrix() <= 0) {
            throw new RuntimeException("Le prix doit être supérieur à 0");
        }
        if (Objects.isNull(model.getSiegeReserver()) || model.getSiegeReserver() < 0 || model.getSiegeReserver() == 0) {
            throw new RuntimeException("Le nombre de sièges réservés ne peut être négatif ni null");
        }
        if (Objects.isNull(model.getBateauId()) || model.getBateauId() <= 0) {
            throw new RuntimeException("L'ID du bateau est obligatoire");
        }
    }

    public Voyage getModel() {
        return model;
    }
}
