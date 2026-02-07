package mvc_ticket.view;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author flo
 */
import javax.swing.*;
import java.awt.*;

public class TicketView extends JFrame {
public JTextField txtNom = new JTextField(15);
    public JTextField txtBateau = new JTextField(15);
    public JTextField txtLieuDepart = new JTextField(15);
    public JTextField txtLieuArrivee = new JTextField(15);
    public JTextField txtHeureDepart = new JTextField(8);
    public JTextField txtHeureArrivee = new JTextField(8);
    public JButton btnGenerer = new JButton("Générer le PDF");

    public TicketView() {
        setTitle("Gestion Croisière");
        setLayout(new GridLayout(7, 2, 5, 5));
        
        add(new JLabel(" Passager :")); add(txtNom);
        add(new JLabel(" Bateau :")); add(txtBateau);
        add(new JLabel(" Lieu Départ :")); add(txtLieuDepart);
        add(new JLabel(" Lieu Arrivée :")); add(txtLieuArrivee);
        add(new JLabel(" Heure Départ :")); add(txtHeureDepart);
        add(new JLabel(" Heure Arrivée :")); add(txtHeureArrivee);
        add(new JLabel(" Action :")); add(btnGenerer);
        
        pack();
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
