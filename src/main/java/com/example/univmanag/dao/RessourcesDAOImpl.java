package com.example.univmanag.dao;

import com.example.univmanag.beans.Ressources;
import com.example.univmanag.dao.facade.ResourcesDao;
import com.example.univmanag.util.DataConnect;
import jakarta.ejb.Local;
import jakarta.ejb.Stateless;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Local(ResourcesDao.class)
@Stateless
public class RessourcesDAOImpl implements ResourcesDao {
    public List<Ressources> getRessources(String show, Date search_date_debut, Date search_date_fin) {
        Connection con = null;
        PreparedStatement ps = null;
        List<Ressources> ressourcesList = new ArrayList<>();
        try {

            con = DataConnect.getConnection();
            assert con != null;

            if (show.equals("all"))
                ps = con.prepareStatement("Select id,nom,type,available,image,departement from ressources");
            else if (show.equals("taken") && search_date_debut != null && search_date_fin != null) {
                ps = con.prepareStatement("Select id,nom,type,available,image,departement from ressources " +
                        "where id in (select resource_id from reservations where date_debut = ? and date_fin = ?)");
                ps.setDate(1, new java.sql.Date(search_date_debut.getTime()));
                ps.setDate(2, new java.sql.Date(search_date_fin.getTime()));
            } else if (show.equals("taken") && search_date_debut == null && search_date_fin == null) {
                ps = con.prepareStatement("Select id,nom,type,available,image,departement from ressources " +
                        "where id in (select resource_id from reservations)");
            } else if (show.equals("available") && search_date_debut != null && search_date_fin != null) {
                ps = con.prepareStatement("Select id,nom,type,available,image,departement from ressources " +
                        "where id not in (select resource_id from reservations where date_debut = ? and date_fin = ?)");
                ps.setDate(1, new java.sql.Date(search_date_debut.getTime()));
                ps.setDate(2, new java.sql.Date(search_date_fin.getTime()));
            } else if (show.equals("available") && search_date_debut == null && search_date_fin == null) {
                ps = con.prepareStatement("Select id,nom,type,available,image,departement from ressources " +
                        "where id not in (select resource_id from reservations )");
            }
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String nom = rs.getString("nom");
                String type = rs.getString("type");
                boolean available = rs.getBoolean("available");
                String image = rs.getString("image");
                String departement = rs.getString("departement");
                //Assuming you have a user object
                Ressources ressources = new Ressources(type, nom, id, available, departement, image);

                ressourcesList.add(ressources);
            }
            System.out.println("hi");
            System.out.println(ressourcesList);
            System.out.println("by");
        } catch (SQLException ex) {
            System.out.println("Login error -->" + ex.getMessage());
            return ressourcesList;
        } finally {
            DataConnect.close(con);
        }
        System.out.println(ressourcesList);
        return ressourcesList;
    }

    public boolean addRessource(int i, String nom, String type, String departement, String image) {
        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = DataConnect.getConnection();
            assert con != null;
            ps = con.prepareStatement("INSERT INTO ressources(id,nom,type,available,departement,image) values (?,?,?,?,?,?)");
            ps.setInt(1, i);
            ps.setString(2, nom);
            ps.setString(3, type);
            ps.setBoolean(4, false);
            ps.setString(5, departement);
            ps.setString(6, image);
            ps.executeUpdate();

        } catch (SQLException ex) {
            System.out.println("Login error -->" + ex.getMessage());
            return false;
        } finally {
            DataConnect.close(con);
        }
        return true;
    }

    @Override
    public void makeResourcesUnReserved(String nom) {
        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = DataConnect.getConnection();
            assert con != null;
            ps = con.prepareStatement("update ressources set available=? where nom=?");
            ps.setBoolean(1, true);
            ps.setString(2, nom);
            ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("Login error -->" + ex.getMessage());

        } finally {
            DataConnect.close(con);
        }
    }

    @Override
    public void makeResourcesReserved(String nom) {
        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = DataConnect.getConnection();
            assert con != null;
            ps = con.prepareStatement("update ressources set available=? where nom=?");
            ps.setBoolean(1, false);
            ps.setString(2, nom);
            ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("Login error -->" + ex.getMessage());

        } finally {
            DataConnect.close(con);
        }
    }

    @Override
    public void deleteResources(int id) {
        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = DataConnect.getConnection();
            assert con != null;
            ps = con.prepareStatement("delete from ressources where id=?");
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("Login error -->" + ex.getMessage());

        } finally {
            DataConnect.close(con);
        }
    }
}
