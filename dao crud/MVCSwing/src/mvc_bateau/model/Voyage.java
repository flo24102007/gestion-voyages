/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mvc_bateau.model;
import java.util.Objects;
import mvc_bateau.infra.model.BaseModel;
import mvc_bateau.infra.dao.Column; 
import java.time.Duration;
import java.time.LocalDateTime;


/**
 *
 * @author flo
 */
public class Voyage extends BaseModel {
    @Column("lieu_depart")
    private String lieuDepart;

    @Column("lieu_arrive")
    private String lieuArrive;

    @Column("date_heure_depart")
    private LocalDateTime  dateHeureDepart;

    @Column("date_heure_arrive")
    private LocalDateTime dateHeureArrive;

    private Integer prix;

    @Column("siege_reserver")
    private Integer siegeReserver;
    //@Column("bateau_du_voyage")
    //private Integer id_bateau;

    public Voyage() {
        this.prix = 0;
        this.siegeReserver = 0;
    }

    public Voyage(
            String lieuDepart,
            String lieuArrive,
            LocalDateTime dateHeureDepart,
            LocalDateTime dateHeureArrive,
            Integer prix,
            Integer siegeReserver
            //Integer id_bateau
    ) {
        this.lieuDepart = lieuDepart;
        this.lieuArrive = lieuArrive;
        this.dateHeureDepart = dateHeureDepart;
        this.dateHeureArrive = dateHeureArrive;
        this.prix = prix;
        this.siegeReserver = siegeReserver;
        //this.id_bateau=id_bateau;
    }

    @Override
    public Object[] toTableRow() {
        return new Object[]{
            id,
            lieuDepart,
            lieuArrive,
            dateHeureDepart,
            dateHeureArrive,
            prix,
            siegeReserver,
           // id_bateau
        };
    }

    // ===== Getters & Setters =====

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

    public LocalDateTime getDateHeureDepart() {
        return dateHeureDepart;
    }

    public void setDateHeureDepart(LocalDateTime dateHeureDepart) {
        this.dateHeureDepart = dateHeureDepart;
    }

    public LocalDateTime getDateHeureArrive() {
        return dateHeureArrive;
    }

    public void setDateHeureArrive(LocalDateTime dateHeureArrive) {
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
    //public Integer getId_bateau() {
       // return id_bateau;
    //}
     //public void setId_bateau(Integer id_bateau) {
      //  this.id_bateau = id_bateau;
   // }

    // ===== equals / hashCode =====

    @Override
    public int hashCode() {
        return Objects.hash(lieuDepart, lieuArrive, dateHeureDepart);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Voyage other = (Voyage) obj;
        return Objects.equals(lieuDepart, other.lieuDepart)
                && Objects.equals(lieuArrive, other.lieuArrive)
                && Objects.equals(dateHeureDepart, other.dateHeureDepart);
    }

    @Override
    public String toString() {
        return "Voyage{" +
                "lieuDepart=" + lieuDepart +
                ", lieuArrive=" + lieuArrive +
                ", dateHeureDepart=" + dateHeureDepart +
                ", dateHeureArrive=" + dateHeureArrive +
                ", prix=" + prix +
                ", siegeReserver=" + siegeReserver +
              //  ", id_bateau=" + id_bateau +
                '}';
    }
    
}
