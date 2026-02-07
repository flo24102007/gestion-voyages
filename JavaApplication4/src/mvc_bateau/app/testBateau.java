/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mvc_bateau.app;

import mvc_voyage.DAO.VoyageDAO;
import mvc_voyage.controller.VoyageController;
import mvc_voyage.view.VoyageView;

/**
 *
 * @author flo
 */
public class testBateau {

    public static void main(String[] args) {
        

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
    }
}
