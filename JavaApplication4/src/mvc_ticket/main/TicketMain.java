/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mvc_ticket.main;

import mvc_ticket.model.TicketGenerator;
import mvc_ticket.view.TicketView;
import mvc_view.controller.TicketController;

/**
 *
 * @author flo
 */
public class TicketMain {
    public static void main(String[] args) {
        // 1. Initialisation du Service (la logique métier)
        TicketGenerator service = new TicketGenerator();

        // 2. Initialisation de la Vue (l'interface graphique)
        TicketView vue = new TicketView();

        // 3. Initialisation du Contrôleur (le lien entre les deux)
        // Il va écouter les clics sur le bouton de la vue
        new TicketController(vue, service);

        // 4. Affichage de la fenêtre
        vue.setVisible(true);
    }
    
}
