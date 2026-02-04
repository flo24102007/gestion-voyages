/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mvc_bateau.model;
import java.util.Objects;
import mvc_bateau.infra.model.BaseModel;
import mvc_bateau.infra.dao.Column;


/**
 *
 * @author flo
 */
public class Ticket extends BaseModel {
    @Column("id_voyage")
    private Integer idVoyage;

    @Column("id_passager")
    private Integer idPassager;

    @Column("numero_siege")
    private Integer numeroSiege;

    private Integer prix;

    public Ticket() {
        this.prix = 0;
    }

    public Ticket(Integer idVoyage, Integer idPassager, Integer numeroSiege, Integer prix) {
        this.idVoyage = idVoyage;
        this.idPassager = idPassager;
        this.numeroSiege = numeroSiege;
        this.prix = prix;
    }

    @Override
    public Object[] toTableRow() {
        return new Object[]{
            id,
            idVoyage,
            idPassager,
            numeroSiege,
            prix
        };
    }

    // ===== Getters & Setters =====

    public Integer getIdVoyage() {
        return idVoyage;
    }

    public void setIdVoyage(Integer idVoyage) {
        this.idVoyage = idVoyage;
    }

    public Integer getIdPassager() {
        return idPassager;
    }

    public void setIdPassager(Integer idPassager) {
        this.idPassager = idPassager;
    }

    public Integer getNumeroSiege() {
        return numeroSiege;
    }

    public void setNumeroSiege(Integer numeroSiege) {
        this.numeroSiege = numeroSiege;
    }

    public Integer getPrix() {
        return prix;
    }

    public void setPrix(Integer prix) {
        this.prix = prix;
    }

    // ===== equals / hashCode =====

    @Override
    public int hashCode() {
        return Objects.hash(idVoyage, idPassager, numeroSiege);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Ticket other = (Ticket) obj;
        return Objects.equals(idVoyage, other.idVoyage)
                && Objects.equals(idPassager, other.idPassager)
                && Objects.equals(numeroSiege, other.numeroSiege);
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "id=" + id +
                ", idVoyage=" + idVoyage +
                ", idPassager=" + idPassager +
                ", numeroSiege=" + numeroSiege +
                ", prix=" + prix +
                '}';
    }
    
}
