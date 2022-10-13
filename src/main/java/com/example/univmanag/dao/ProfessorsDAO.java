package com.example.univmanag.dao;

import com.example.univmanag.beans.Professors;
import com.example.univmanag.util.DataConnect;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProfessorsDAO {
    public static List<Professors> getProfessors() {
        Connection con = null;
        PreparedStatement ps = null;
        List<Professors> professorsList = new ArrayList<>();
        try {

            con = DataConnect.getConnection();
            assert con != null;
            ps = con.prepareStatement("Select nom, available, sex,image,departement from Professors");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String nom = rs.getString("nom");
                String sex = rs.getString("sex");
                boolean available=rs.getBoolean("available");
                String image=rs.getString("image");
                String departement=rs.getString("departement");
                //Assuming you have a user object
                Professors professors = new Professors(nom,sex,available,image,departement);

                professorsList.add(professors);
            }
            System.out.println("hi");
            System.out.println(professorsList);
            System.out.println("by");
        } catch (SQLException ex) {
            System.out.println("Login error -->" + ex.getMessage());
            return professorsList;
        } finally {
            DataConnect.close(con);
        }
        System.out.println(professorsList);
        return professorsList;
    }
}
