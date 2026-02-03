/*
	 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
	 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
	 */
	package mvc_bateau.controller;

	import java.awt.event.ActionEvent;
	import java.awt.event.ActionListener;
	import java.sql.SQLException;
	import java.util.Objects;
	import java.util.logging.Level;
	import java.util.logging.Logger;
	import javax.swing.JOptionPane;
	import mvc_bateau.dao.VoyageDAO;
	import mvc_bateau.model.Voyage;
	import mvc_bateau.view.FicheVoyageView;

	public class FicheVoyageController {
	   private final Voyage model;
	   private final FicheVoyageView view;
	   private final VoyageDAO dao;
	   private final boolean isCreateForm;
	   private final ActionListener onSucess;
	   
	   public FicheVoyageController(Voyage m, FicheVoyageView v, VoyageDAO d, boolean isCreateForm, ActionListener onSucess){
	       this.model = m;
	       this.view = v;
	       this.dao = d;
	       this.isCreateForm = isCreateForm;
	       this.onSucess = onSucess;
	       initView();
	   }
	   
	   public void initView(){
	       view.getTfNomVoyage().setText(model.getNom());
	       view.getTfDescVoyage().setText(model.getDescription());
	       view.getTfNumSiegeVoyage().setText(model.getNumerotationSiege());
	       view.getSpCapacite().setValue(model.getCapacite());
	   }
	   
	   public void initController(){
	       view.getBtEnregistrer().addActionListener(e -> enregistrer());
	   }
	   
	   private void enregistrer() {
	       model.setNom(view.getTfNomVoyage().getText());
	       model.setDescription(view.getTfDescVoyage().getText());
	       model.setNumerotationSiege(view.getTfNumSiegeVoyage().getText());
	       model.setCapacite((Integer) view.getSpCapacite().getValue());
	       try {
	           // Contrôle de validation
	           validationControl(model);
	           // Enregistrement
	           if(isCreateForm) {
	               // Création
	               model.setId(dao.create(model));
	               JOptionPane.showMessageDialog(null, "Enregistrement du Voyage " + model.getNom() + " effectué avec succès",  "Enregistrement",JOptionPane.INFORMATION_MESSAGE);
	           } else {
	               // Modification
	               dao.update(model);
	               JOptionPane.showMessageDialog(null, "Modification du Voyage " + model.getNom() + " effectué avec succès",  "Enregistrement",JOptionPane.INFORMATION_MESSAGE);
	           }
	           view.setVisible(false);
	           if(Objects.nonNull(onSucess)) {
	               onSucess.actionPerformed(new ActionEvent(this, 0, null));
	           }
	       } catch(RuntimeException ex) {
	           JOptionPane.showMessageDialog(null, ex.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
	       } catch (SQLException ex) {
	           Logger.getLogger(FicheVoyageController.class.getName()).log(Level.SEVERE, null, ex);
	           JOptionPane.showMessageDialog(null, "Erreur survenue lors de l'enregistrement", "Erreur", JOptionPane.ERROR_MESSAGE);
	       }
	   }
	   
	   private void validationControl(Voyage model) {
	       if(Objects.isNull(model.getNom()) || model.getNom().isBlank()) {
	           throw new RuntimeException("Le nom est obligatoire...");
	       }
	       if(Objects.isNull(model.getNumerotationSiege()) || model.getNumerotationSiege().isBlank()) {
	           throw new RuntimeException("La numérotation du siège est obligatoire...");
	       }
	       if(Objects.isNull(model.getCapacite())) {
	           throw new RuntimeException("La capacité est obligatoire...");
	       }
	   }

	    public Voyage getModel() {
	        return model;
	    }
	    
	}