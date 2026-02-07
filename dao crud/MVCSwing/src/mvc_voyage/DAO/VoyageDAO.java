/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mvc_voyage.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import mvc_bateau.infra.exception.DBException;
import mvc_bateau.infra.jdbc.JdbcHelper;
import mvc_voyage.infra.DAO.BaseDBDAO;
import mvc_voyage.infra.DAO.BaseDBDAO;
import mvc_voyage.model.Voyage;

public class VoyageDAO extends BaseDBDAO<Voyage> {

    public VoyageDAO() {
        super("voyage");
    }

    @Override
    protected String defaultSort() {
        return "id DESC";
    }
//@Override
//public List<Voyage> selectAll() {
//
//    }

//    public void update(Voyage model) {
//
//    }
    /**
     *
     * @param model
     * @return
     */
    public Long Create(Voyage model) {
        return null;
    }

    public void delete(Long id) {
        String sql = "DELETE FROM voyage WHERE id = ?";

        try (PreparedStatement stmt = mvc_bateau.infra.jdbc.JdbcHelper.getInstance().getConn().prepareStatement(sql)) {

            stmt.setLong(1, id);
            int rowsAffected = stmt.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Voyage avec l'ID " + id + " supprimé avec succès.");
            }

        } catch (java.sql.SQLException e) {
            throw new mvc_bateau.infra.exception.DBException("Erreur lors de la suppression du voyage : " + e.getMessage());
        }
    }

    public Voyage selectById(Long id) {
        String sql = "SELECT id, lieu_depart, lieu_arrive, date_heure_depart, date_heure_arrive, prix, siege_reserver, bateau_id FROM voyage WHERE id = ?";

        try (PreparedStatement stmt = mvc_bateau.infra.jdbc.JdbcHelper.getInstance().getConn().prepareStatement(sql)) {

            stmt.setLong(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    // On utilise la méthode de mapping pour transformer la ligne SQL en objet Java
                    return mapResultSetToVoyage(rs);
                }
            }
        } catch (java.sql.SQLException e) {
            throw new mvc_bateau.infra.exception.DBException("Erreur lors de la récupération du voyage ID: " + id, e);
        }
        return null; // Retourne null si aucun voyage n'est trouvé avec cet ID
    }

    private Voyage mapResultSetToVoyage(ResultSet rs) throws SQLException {
        Voyage v = new Voyage();
        v.setId(rs.getLong("id")); // Doit correspondre au nom dans la table SQL
        v.setLieuDepart(rs.getString("lieu_depart"));
        v.setLieuArrive(rs.getString("lieu_arrive"));
        v.setDateHeureDepart(rs.getTimestamp("date_heure_depart"));
        v.setDateHeureArrive(rs.getTimestamp("date_heure_arrive"));
        v.setPrix(rs.getInt("prix"));
        v.setSiegeReserver(rs.getInt("siege_reserver"));
        v.setBateauId(rs.getInt("bateau_id"));
        return v;
    }
}
