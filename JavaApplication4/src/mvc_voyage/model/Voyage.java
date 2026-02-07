/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mvc_voyage.model;

import mvc_voyage.infra.model.BaseModel;
import mvc_voyage.infra.DAO.Column;
import mvc_voyage.infra.DAO.Transient;
import java.util.Date;

public class Voyage extends BaseModel {
    
    @Column("lieu_depart")
    private String lieuDepart;
    
    @Column("lieu_arrive")
    private String lieuArrive;
    
    @Column("date_heure_depart")
    private Date dateHeureDepart;
    
    @Column("date_heure_arrive")
    private Date dateHeureArrive;
    
    private Integer prix;
    
    @Column("siege_reserver")
    private Integer siegeReserver;
    
    @Column("bateau_id")
    private Integer bateauId;
    
//    @Transient
//    private String duree; // Champ calculé, non persistant

    public Voyage() {
        this.prix = 0;
        this.siegeReserver = 0;
        this.bateauId = 0;
    }

    public Voyage(String lieuDepart, String lieuArrive, Date dateHeureDepart, 
                  Date dateHeureArrive, Integer prix, Integer siegeReserver, Integer bateauId) {
        this.lieuDepart = lieuDepart;
        this.lieuArrive = lieuArrive;
        this.dateHeureDepart = dateHeureDepart;
        this.dateHeureArrive = dateHeureArrive;
        this.prix = prix;
        this.siegeReserver = siegeReserver;
        this.bateauId = bateauId;
    }

    @Override
    public Object[] toTableRow() {
        return new Object[] {
            id, 
            lieuDepart, 
            lieuArrive, 
            dateHeureDepart, 
            dateHeureArrive, 
            prix, 
            siegeReserver, 
            bateauId,
//            duree // Ajout de la durée dans la ligne du tableau
        };
    }

    // Getters et Setters
    public String getLieuDepart() {
        return lieuDepart;
    }

    public void setLieuDepart(String lieuDepart) {
        this.lieuDepart = lieuDepart;
    }

    public String getLieuArrive() {
        return lieuArrive;
    }

    public void setLieuArrive(String lieuArrive) {
        this.lieuArrive = lieuArrive;
    }

    public Date getDateHeureDepart() {
        return dateHeureDepart;
    }

    public void setDateHeureDepart(Date dateHeureDepart) {
        this.dateHeureDepart = dateHeureDepart;
    }

    public Date getDateHeureArrive() {
        return dateHeureArrive;
    }

    public void setDateHeureArrive(Date dateHeureArrive) {
        this.dateHeureArrive = dateHeureArrive;
    }

    public Integer getPrix() {
        return prix;
    }

    public void setPrix(Integer prix) {
        this.prix = prix;
    }

    public Integer getSiegeReserver() {
        return siegeReserver;
    }

    public void setSiegeReserver(Integer siegeReserver) {
        this.siegeReserver = siegeReserver;
    }

    public Integer getBateauId() {
        return bateauId;
    }

    public void setBateauId(Integer bateauId) {
        this.bateauId = bateauId;
    }

//    public String getDuree() {
//        return duree;
//    }
//
//    public void setDuree(String duree) {
//        this.duree = duree;
//    }

    // Méthode pour calculer la durée
//    public String calculerDuree() {
//        if (dateHeureDepart != null && dateHeureArrive != null) {
//            long diff = dateHeureArrive.getTime() - dateHeureDepart.getTime();
//            long hours = diff / (1000 * 60 * 60);
//            long minutes = (diff % (1000 * 60 * 60)) / (1000 * 60);
//            return String.format("%02d:%02d", hours, minutes);
//        }
//        return "00:00";
//    }

    @Override
    public String toString() {
        return "Voyage{" + 
               "lieuDepart=" + lieuDepart + 
               ", lieuArrive=" + lieuArrive + 
               ", dateHeureDepart=" + dateHeureDepart + 
               ", dateHeureArrive=" + dateHeureArrive + 
               ", prix=" + prix + 
               ", siegeReserver=" + siegeReserver + 
               ", bateauId=" + bateauId + 
               '}';
    }
}