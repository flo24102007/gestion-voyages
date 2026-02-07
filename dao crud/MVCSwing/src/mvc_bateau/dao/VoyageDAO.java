/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mvc_bateau.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import static jdk.internal.util.StaticProperty.userName;
import mvc_bateau.infra.dao.BaseDBDAO;
import mvc_bateau.model.Bateau;
import mvc_bateau.model.Voyage;

/**
 *
 * @author flo
 */
public class VoyageDAO extends BaseDBDAO<Voyage>{
  public VoyageDAO() throws SQLException {  super(
                "voyage"); // Nom de table



}
  }
