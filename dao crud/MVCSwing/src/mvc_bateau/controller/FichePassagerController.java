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
	import mvc_bateau.dao.PassagerDAO;
	import mvc_bateau.model.Passager;
	import mvc_bateau.view.FichePassagerView;

	public class FichePassagerController {
	   private final Passager model;
	   private final FichePassagerView view;
	   private final PassagerDAO dao;
	   private final boolean isCreateForm;
	   private final ActionListener onSucess;
	   
	   public FichePassagerController(Passager m, FichePassagerView v, PassagerDAO d, boolean isCreateForm, ActionListener onSucess){
	       this.model = m;
	       this.view = v;
	       this.dao = d;
	       this.isCreateForm = isCreateForm;
	       this.onSucess = onSucess;
	       initView();
	   }
	   
	   public void initView(){
	       view.getTfNomPassager().setText(model.getNom());
	       view.getTfDescPassager().setText(model.getDescription());
	       view.getTfNumSiegePassager().setText(model.getNumerotationSiege());
	       view.getSpCapacite().setValue(model.getCapacite());
	   }
	   
	   public void initController(){
	       view.getBtEnregistrer().addActionListener(e -> enregistrer());
	   }
	   
	   private void enregistrer() {
	       model.setNom(view.getTfNomPassager().getText());
	       model.setDescription(view.getTfDescPassager().getText());
	       model.setNumerotationSiege(view.getTfNumSiegePassager().getText());
	       model.setCapacite((Integer) view.getSpCapacite().getValue());
	       try {
	           // Contrôle de validation
	           validationControl(model);
	           // Enregistrement
	           if(isCreateForm) {
	               // Création
	               model.setId(dao.create(model));
	               JOptionPane.showMessageDialog(null, "Enregistrement du Passager " + model.getNom() + " effectué avec succès",  "Enregistrement",JOptionPane.INFORMATION_MESSAGE);
	           } else {
	               // Modification
	               dao.update(model);
	               JOptionPane.showMessageDialog(null, "Modification du Passager " + model.getNom() + " effectué avec succès",  "Enregistrement",JOptionPane.INFORMATION_MESSAGE);
	           }
	           view.setVisible(false);
	           if(Objects.nonNull(onSucess)) {
	               onSucess.actionPerformed(new ActionEvent(this, 0, null));
	           }
	       } catch(RuntimeException ex) {
	           JOptionPane.showMessageDialog(null, ex.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
	       } catch (SQLException ex) {
	           Logger.getLogger(FichePassagerController.class.getName()).log(Level.SEVERE, null, ex);
	           JOptionPane.showMessageDialog(null, "Erreur survenue lors de l'enregistrement", "Erreur", JOptionPane.ERROR_MESSAGE);
	       }
	   }
	   
	   private void validationControl(Passager model) {
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

	    public Passager getModel() {
	        return model;
	    }
	    
	}