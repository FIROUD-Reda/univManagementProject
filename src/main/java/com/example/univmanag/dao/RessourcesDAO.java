package com.example.univmanag.dao;

import com.example.univmanag.beans.Ressources;
import com.example.univmanag.util.DataConnect;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RessourcesDAO {
    public static List<Ressources> getRessources() {
        Connection con = null;
        PreparedStatement ps = null;
        List<Ressources> ressourcesList = new ArrayList<>();
        try {

            con = DataConnect.getConnection();
            assert con != null;
            ps = con.prepareStatement("Select nom,type,available,image,departement from Ressources");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String nom = rs.getString("nom");
                String type = rs.getString("type");
                boolean available=rs.getBoolean("available");
                String image=rs.getString("image");
                String departement=rs.getString("departement");
                //Assuming you have a user object
                Ressources ressources = new Ressources(type,nom,available,departement,image);

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

    public static boolean addRessource(int i, String nom, String type, String departement, String image) {
        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = DataConnect.getConnection();
            assert con != null;
            ps = con.prepareStatement("INSERT INTO Ressources(i,nom,type,available,departement,image) values (?,?,?,?,?,?)");
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
}
