/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mvc_ticket.model;

/**
 *
 * @author flo
 */
public class Ticket {
    private String nomPassager;
    private String nomBateau;
    private String lieuDepart;
    private String lieuArrivee;
    private String dateVoyage;
    private String heureDepart;
    private String heureArrivee;

    public Ticket(String nomPassager, String nomBateau, String lieuDepart, 
                          String lieuArrivee, String dateVoyage, String heureDepart, String heureArrivee) {
        this.nomPassager = nomPassager;
        this.nomBateau = nomBateau;
        this.lieuDepart = lieuDepart;
        this.lieuArrivee = lieuArrivee;
        this.dateVoyage = dateVoyage;
        this.heureDepart = heureDepart;
        this.heureArrivee = heureArrivee;
    }

    // Getters
    public String getNomPassager() { return nomPassager; }
    public String getNomBateau() { return nomBateau; }
    public String getLieuDepart() { return lieuDepart; }
    public String getLieuArrivee() { return lieuArrivee; }
    public String getDateVoyage() { return dateVoyage; }
    public String getHeureDepart() { return heureDepart; }
    public String getHeureArrivee() { return heureArrivee; }
    
 }