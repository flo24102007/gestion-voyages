/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mvc_bateau.app;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import mvc_bateau.controller.VoyageController;
import mvc_bateau.dao.VoyageDAO;
import mvc_bateau.view.VoyageView;
import mvc_bateau.view.FicheVoyageView;

/**
 *
 * @author flo
 */
public class MainVoyage {
    public static void main(String[] args) {
        // Lancement GUI Swing
        javax.swing.SwingUtilities.invokeLater(() -> {
            try {
                gui();
            } catch (SQLException ex) {
                Logger.getLogger(MainVoyage.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }

    private static void gui() throws SQLException {
        theme();

        // DAO
        VoyageDAO dao = new VoyageDAO();

        // View principale (liste des voyages)
        VoyageView view = new VoyageView();

        // Controller
        VoyageController controller = new VoyageController(
                dao.selectAll(),
                view,
                dao
        );

        controller.initController();
        view.setVisible(true);
    }

    /**
     * Look & Feel Nimbus
     */
    private static void theme() {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info 
                    : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception ex) {
            java.util.logging.Logger
                .getLogger(FicheVoyageView.class.getName())
                .log(java.util.logging.Level.SEVERE, null, ex);
        }
    }
    
}
