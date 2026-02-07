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
                    import java.time.format.DateTimeFormatter;
                    import javax.swing.JSpinner;
                    import java.util.Date;
                    import java.time.LocalDateTime;
                    import java.time.ZoneId;

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
	   DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
	   public void initView(){
	       view.getLieuDeDepart().setText(model.getLieuDepart());
	       view.getLieuArrivée ().setText(model.getLieuArrive());
	      view.getDate_heure_depart().setValue(model.getDateHeureDepart());
	       view.getDate_heure_fin().setValue(model.getDateHeureArrive());
                           // view.getSiege_disponible().setValue(model.getSiegeReserver());
	   }
	   
	   public void initController(){
	       view.getEnregistrer().addActionListener(e -> enregistrer());
	   }
	   
	   private void enregistrer() {
	       model.setLieuDepart(view.getLieuDeDepart().getText());
	       model.setLieuArrive(view.getLieuArrivée().getText());
                            // Récupérer la valeur du JSpinner (Date)
           Date date = (Date) view.getDate_heure_depart().getValue();
                          // Convertir Date → LocalDateTime
                      LocalDateTime localDateTime = date.toInstant()
                      .atZone(ZoneId.systemDefault())
                      .toLocalDateTime();
	       // Définir dans le model
                           model.setDateHeureDepart(localDateTime);
                            // Récupérer la valeur du JSpinner (Date)
           Date Date = (Date) view.getDate_heure_fin().getValue();
           // Convertir Date → LocalDateTime
                      LocalDateTime LocalDateTime = Date.toInstant()
                      .atZone(ZoneId.systemDefault())
                      .toLocalDateTime();
	       model.setDateHeureArrive(LocalDateTime);
	       try {
	           // Contrôle de validation
	           validationControl(model);
	           // Enregistrement
	           if(isCreateForm) {
	               // Création
	               model.setId(dao.create(model));
	               JOptionPane.showMessageDialog(null, "Enregistrement du Voyage " + model.getLieuDepart() + " effectué avec succès",  "Enregistrement",JOptionPane.INFORMATION_MESSAGE);
	           } else {
	               // Modification
	               dao.update(model);
	               JOptionPane.showMessageDialog(null, "Modification du Voyage " + model.getLieuDepart() + " effectué avec succès",  "Enregistrement",JOptionPane.INFORMATION_MESSAGE);
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
	       if(Objects.isNull(model.getLieuDepart()) || model.getLieuDepart().isBlank()) {
	           throw new RuntimeException("Le lieu de depart est obligatoire...");
	       }
	       if(Objects.isNull(model.getLieuArrive()) || model.getLieuArrive().isBlank()) {
	           throw new RuntimeException("Le lieu d'arrivé est obligatoire...");
	       }
	       if(Objects.isNull(model.getDateHeureDepart())) {
	           throw new RuntimeException("La date de depart est obligatoire...");
	       }
                            if(Objects.isNull(model.getDateHeureArrive())) {
	           throw new RuntimeException("La date de d'arrivée est obligatoire...");
	       }
	   }

	    public Voyage getModel() {
	        return model;
	    }
	    
	}


