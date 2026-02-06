/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mvc_bateau.dao;
import mvc_bateau.infra.dao.BaseDBDAO;
import mvc_bateau.model.Passager;


/**
 *
 * @author flo
 */
    public class PassagerDAO extends BaseDBDAO<Passager>{
  public PassagerDAO() {  super(
                "passager"); // Nom de table
        
    }
}
    

