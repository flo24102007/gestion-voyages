package mvc_bateau.infra.dao;

import java.util.Objects;
import mvc_bateau.infra.model.BaseModel;
import mvc_bateau.infra.dao.Column; 
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date; // Nécessaire pour la compatibilité infra

/**
 * @author flo
 */
public class Voyage extends BaseModel {

    @Column("lieu_depart")
    private String lieuDepart;

    @Column("lieu_arrive")
    private String lieuArrive;

    // ASTUCE : Si ton infra ne supporte pas LocalDateTime, 
    // on mappe sur un type Date classique pour la base, 
    // mais on utilise LocalDateTime pour l'affichage.
    @Column("date_heure_depart")
    private Date date_heure_depart_sql; 

    @Column("date_heure_arrive")
    private Date date_heure_arrive_sql;

    @Column("prix")
    private Integer prix;

    @Column("siege_reserver")
    private Integer siegeReserver;

    public Voyage() {
        this.prix = 0;
        this.siegeReserver = 0;
    }

    // ===== Logique de conversion pour l'affichage =====

    public LocalDateTime getDateHeureDepart() {
        if (this.date_heure_depart_sql == null) return null;
        return this.date_heure_depart_sql.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDateTime();
    }

    public void setDateHeureDepart(LocalDateTime ldt) {
        if (ldt == null) {
            this.date_heure_depart_sql = null;
        } else {
            this.date_heure_depart_sql = java.sql.Timestamp.valueOf(ldt);
        }
    }

    public LocalDateTime getDateHeureArrive() {
        if (this.date_heure_arrive_sql == null) return null;
        return this.date_heure_arrive_sql.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDateTime();
    }

    public void setDateHeureArrive(LocalDateTime ldt) {
        if (ldt == null) {
            this.date_heure_arrive_sql = null;
        } else {
            this.date_heure_arrive_sql = java.sql.Timestamp.valueOf(ldt);
        }
    }

    @Override
    public Object[] toTableRow() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        LocalDateTime depart = getDateHeureDepart();
        LocalDateTime arrive = getDateHeureArrive();
        
        return new Object[]{
            id,
            lieuDepart,
            lieuArrive,
            depart != null ? depart.format(formatter) : "N/A",
            arrive != null ? arrive.format(formatter) : "N/A",
            prix,
            siegeReserver
        };
    }

    // ===== Getters & Setters standards pour le reste =====

    public String getLieuDepart() { return lieuDepart; }
    public void setLieuDepart(String lieuDepart) { this.lieuDepart = lieuDepart; }

    public String getLieuArrive() { return lieuArrive; }
    public void setLieuArrive(String lieuArrive) { this.lieuArrive = lieuArrive; }

    public Integer getPrix() { return prix; }
    public void setPrix(Integer prix) { this.prix = prix; }

    public Integer getSiegeReserver() { return siegeReserver; }
    public void setSiegeReserver(Integer siegeReserver) { this.siegeReserver = siegeReserver; }

    @Override
    public String toString() {
        return "Voyage{" + "id=" + id + ", depart=" + getDateHeureDepart() + '}';
    }
}