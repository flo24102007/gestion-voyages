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
public class Passager extends BaseModel {
    private String nom;

    private String email;

    private Integer numero;

    @Column("id_bateau")
    private Integer idBateau;

    public Passager() {
    }

    public Passager(String nom, String email, Integer numero, Integer idBateau) {
        this.nom = nom;
        this.email = email;
        this.numero = numero;
        this.idBateau = idBateau;
    }

    @Override
    public Object[] toTableRow() {
        return new Object[]{
            id,
            nom,
            email,
            numero,
            idBateau
        };
    }

    // ===== Getters & Setters =====

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public Integer getIdBateau() {
        return idBateau;
    }

    public void setIdBateau(Integer idBateau) {
        this.idBateau = idBateau;
    }

    // ===== equals / hashCode =====

    @Override
    public int hashCode() {
        return Objects.hash(email);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Passager other = (Passager) obj;
        return Objects.equals(email, other.email);
    }

    @Override
    public String toString() {
        return "Passager{" +
                "nom=" + nom +
                ", email=" + email +
                ", numero=" + numero +
                ", idBateau=" + idBateau +
                '}';
    }
    
}
