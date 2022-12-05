package com.example.univmanag.dao;

import com.example.univmanag.beans.Departement;
import com.example.univmanag.beans.Salle;
import com.example.univmanag.beans.Salles;
import com.example.univmanag.util.DataConnect;
import jakarta.ejb.Local;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Local(SallesDao.class)
@Stateless
public class SallesDAOImpl implements SallesDao {

    public List<Salles> getSalles(String show) {
        Connection con = null;
        PreparedStatement ps = null;
        List<Salles> sallesList = new ArrayList<>();
        try {

            con = DataConnect.getConnection();
            assert con != null;
            if (show.equals("all"))
                ps = con.prepareStatement("Select nom, capacite, available,image,departement from salles");
            else if (show.equals("available")) {
                ps = con.prepareStatement("Select nom, capacite, available,image,departement from salles where available=?");
                ps.setBoolean(1, true);
            } else if (show.equals("taken")) {
                ps = con.prepareStatement("Select nom, capacite, available,image,departement from salles where available=?");
                ps.setBoolean(1, false);
            }
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String nom = rs.getString("nom");
                int capacite = rs.getInt("capacite");
                boolean available = rs.getBoolean("available");
                String image = rs.getString("image");
                String departement = rs.getString("departement");
                //Assuming you have a user object
                Salles salles = new Salles(nom, capacite, available, image, departement);

                sallesList.add(salles);
            }
            System.out.println("hi");
            System.out.println(sallesList);
            System.out.println("by");
        } catch (SQLException ex) {
            System.out.println("Login error -->" + ex.getMessage());
            return sallesList;
        } finally {
            DataConnect.close(con);
        }
        System.out.println(sallesList);
        return sallesList;
    }

    @Override
    public List<Salle> getAllSalles(String show) {
        return null;
    }

    @Override
    public void makeSalleUnReserved(String nom) {
        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = DataConnect.getConnection();
            assert con != null;
            ps = con.prepareStatement("update salles set available=? where nom=?");
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
    public void makeSalleReserved(String nom) {
        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = DataConnect.getConnection();
            assert con != null;
            ps = con.prepareStatement("update salles set available=? where nom=?");
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
    public void deleteSalle(String nom) {
        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = DataConnect.getConnection();
            assert con != null;
            ps = con.prepareStatement("delete from salles where nom=?");
            ps.setString(1, nom);
            ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("Login error -->" + ex.getMessage());

        } finally {
            DataConnect.close(con);
        }
    }

    @Override
    public void reserveSalle(String nom, String date1, String date2) {
        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = DataConnect.getConnection();
            assert con != null;
            ps = con.prepareStatement("update salles set available=? ,datedebut=? , datefin=? where nom=?");
            ps.setBoolean(1, false);
            ps.setString(1, date1);
            ps.setString(2, date2);
            ps.setString(3, nom);
            ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("Login error -->" + ex.getMessage());

        } finally {
            DataConnect.close(con);
        }
    }

    public boolean addSalle(int i, String nom, int capacite, String departement, String s) {

        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = DataConnect.getConnection();
            assert con != null;
            ps = con.prepareStatement("INSERT INTO salles(id,nom,capacite,available,departement,image,datedebut,datefin) values (?,?,?,?,?,?,?,?)");
            ps.setInt(1, i);
            ps.setString(2, nom);
            ps.setInt(3, capacite);
            ps.setBoolean(4, false);
            ps.setString(5, departement);
            ps.setString(6, s);
            ps.setString(7, "date");
            ps.setString(8, "date");
            ps.executeUpdate();

        } catch (SQLException ex) {
            System.out.println("Login error -->" + ex.getMessage());
            return false;
        } finally {
            DataConnect.close(con);
        }
        return true;
    }
}