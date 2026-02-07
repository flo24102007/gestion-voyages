/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mvc_view.controller;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import mvc_ticket.model.Ticket;
import mvc_ticket.model.TicketGenerator;
import mvc_ticket.view.TicketView;
/**
 *
 * @author flo
 */
public class TicketController {
    private TicketView vue;
    private TicketGenerator service;

    public TicketController(TicketView vue, TicketGenerator service) {
        this.vue = vue;
        this.service = service;

        // On définit l'action du bouton UNE SEULE FOIS ici
        this.vue.btnGenerer.addActionListener(e -> {
            // 1. Récupération des données depuis la vue
            Ticket monTicket = new Ticket(
                vue.txtNom.getText(),
                vue.txtBateau.getText(),
                vue.txtLieuDepart.getText(),
                vue.txtLieuArrivee.getText(),
                "07/02/2026", 
                vue.txtHeureDepart.getText(),
                vue.txtHeureArrivee.getText()
            );

            // 2. Appel du service pour générer le PDF
            String nomFichier = "Ticket_" + monTicket.getNomPassager() + ".pdf";
            service.genererPdf(monTicket, nomFichier);
            

            // 3. Notification utilisateur
            JOptionPane.showMessageDialog(vue, "PDF généré avec succès !");
        });
       

    }
}