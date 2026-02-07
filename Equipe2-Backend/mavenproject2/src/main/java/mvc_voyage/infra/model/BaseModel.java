/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mvc_voyage.infra.model;
import java.beans.Transient;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author user
 */
public abstract class BaseModel {
         protected Long id;

    public BaseModel() {
    }

    public BaseModel(Long id) {
        this.id = id;
    }

    public boolean isNew() {
        return id == null;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Récupère les valeurs des champs pour la persistance (DAO)
     */
    public Object[] getPersistFieldsVal() {
        return getFieldsVal(false);
    }

    /**
     * Récupère toutes les valeurs des champs
     */
    public Object[] getFieldsVal() {
        return getFieldsVal(true);
    }

    /**
     * Récupère les valeurs des champs avec possibilité d'inclure ou non les champs Transient
     */
    public Object[] getFieldsVal(boolean takeTransients) {
        List<Object> values = new ArrayList<>();
        for (Field f : getFields(takeTransients)) {
            f.setAccessible(true);
            try {
                values.add(f.get(this));
            } catch (IllegalAccessException e) {
                throw new RuntimeException("Erreur accès champ: " + f.getName(), e);
            }
            f.setAccessible(false);
        }
        return values.toArray();
    }

    /**
     * Récupère les champs persistants (DAO)
     */
    public Field[] getPersistFields() {
        return getFields(false);
    }

    /**
     * Récupère tous les champs, y compris transitoires si demandé
     */
    public Field[] getFields() {
        return getFields(true);
    }

    public Field[] getFields(boolean takeTransients) {
        List<Field> fields = new ArrayList<>();
        Class<?> clazz = this.getClass();
        for (Field f : clazz.getDeclaredFields()) {
            if (f.getName().equals("id")) continue; // ignorer l'id
            if (!takeTransients && f.isAnnotationPresent(Transient.class)) continue; // ignorer transient
            fields.add(f);
        }
        return fields.toArray(new Field[0]);
    }

    /**
     * Convertit un objet modèle en tableau pour affichage JTable
     */
    public abstract Object[] toTableRow();

}
