/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mvc_bateau.dao;

import mvc_bateau.infra.dao.BaseDBDAO;
import mvc_bateau.model.Voyage;

/**
 *
 * @author flo
 */
public class VoyageDAO extends BaseDBDAO<Voyage>{
  public VoyageDAO() {  super(
                "voyage"); // Nom de table
        
    }
}
  
