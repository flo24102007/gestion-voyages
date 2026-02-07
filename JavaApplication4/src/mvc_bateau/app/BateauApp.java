/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mvc_bateau.app;

import mvc_bateau.controller.BateauController;
import mvc_bateau.dao.BateauDAO;
import mvc_bateau.view.BateauView;

/**
 *
 * @author flo
 */
public class BateauApp {
    
    public static void main(String[] args) {
       

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
    }
}
