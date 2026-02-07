/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mvc_page_first.view;

import javax.swing.JOptionPane;
import mvc_bateau.controller.BateauController;
import mvc_bateau.dao.BateauDAO;
import mvc_bateau.view.BateauView;
import mvc_page_first.view.Page_FirstView;
import mvc_ticket.model.TicketGenerator;
import mvc_ticket.view.TicketView;
import mvc_view.controller.TicketController;
import mvc_voyage.DAO.VoyageDAO;
import mvc_voyage.controller.VoyageController;
import mvc_voyage.view.VoyageView;

/**
 *
 * @author flo
 */
public class controller {

    private Page_FirstView view;

    public controller(Page_FirstView v) {
        view = v;
        initTestView();
    }

    public void initTestView() {
        view.getjButton3();
        view.getjButton4();
        view.getjButton2();
        //view.setlocationRelativeTo(null);
        initController();
    }

    public void initController() {
        view.getjButton4().addActionListener(e -> enregTravel());
        view.getjButton3().addActionListener(e -> enregBoat());
        view.getjButton2().addActionListener(e -> jButton2ActionPerformed());
    }

    private void enregTravel() {
        // 2. Initialiser les couches MVC
        VoyageDAO dao = new VoyageDAO();
        VoyageView view = new VoyageView();

        // On passe les données du DAO au controller
        // Note : on utilise la même instance de dao pour économiser les ressources
        VoyageController controller = new VoyageController(dao.selectAll(), view, dao);

        // 3. Lancer la configuration (boutons, listeners, colonnes)
        controller.initController();

        // 4. Afficher la fenêtre
        view.setVisible(true);
        view.setResizable(false);
    }

    private void enregBoat() {
        //  Récupérer les valeurs tapées dans les champs

        // 2. On prépare la couche de données
        BateauDAO dao = new BateauDAO();

        // 3. On prépare la vue
        BateauView view = new BateauView();

        // 4. On crée le contrôleur de la liste des bateaux
        // On lui passe : la liste actuelle, la vue, et une nouvelle instance de DAO
        BateauController controller = new BateauController(dao.selectAll(), view, dao);

        // 5. On initialise et on affiche
        controller.initController();
        view.setVisible(true);
        view.setResizable(false);
    }
private void jButton2ActionPerformed() {                                         
    //try {
        // 1. On crée les composants
        TicketView formulaire = new TicketView();
        TicketGenerator service = new TicketGenerator();
        
        // 2. On branche le contrôleur (indispensable pour que le bouton du formulaire marche)
        new TicketController(formulaire, service);
        
        // 3. On force l'affichage
        formulaire.pack(); // Ajuste la taille aux composants
        formulaire.setLocationRelativeTo(null); // Centre l'écran
        formulaire.setVisible(true); // Rend visible
        formulaire.toFront(); // Force le premier plan
        
    } //catch (Exception e) {
       // javax.swing.JOptionPane.showMessageDialog(this, "Erreur lors de l'ouverture : " + e.getMessage());
        //e.printStackTrace();
    //}
}
 //}
