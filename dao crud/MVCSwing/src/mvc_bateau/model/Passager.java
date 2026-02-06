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

    

    private String prenoms ;
    private String email;

   

    public Passager() {
    }

    public Passager(String nom, String prenoms, String email ) {
        this.nom = nom;
        this.prenoms=prenoms;
        this.email = email;
        
    }

    @Override
    public Object[] toTableRow() {
        return new Object[]{
            id,
            nom,
            prenoms,
            email,
            
        };
    }

    // ===== Getters & Setters =====

    public String getNom() {
        return nom;
    }
    public String getPrenoms() {
        return prenoms;
    }
    

    public void setNom(String nom) {
        this.nom = nom;
    }
    public void setPrenoms(String prenoms) {
        this.prenoms= prenoms;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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
                ", prenoms=" + prenoms +
                ", email=" + email +
                
                
                '}';
    }
    
}
