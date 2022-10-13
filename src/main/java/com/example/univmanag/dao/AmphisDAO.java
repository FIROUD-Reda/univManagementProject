package com.example.univmanag.dao;

import com.example.univmanag.beans.Amphis;
import com.example.univmanag.util.DataConnect;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AmphisDAO {
    public static List<Amphis> getAmphis() {
        Connection con = null;
        PreparedStatement ps = null;
        List<Amphis> amphisList = new ArrayList<>();
        try {

            con = DataConnect.getConnection();
            assert con != null;
            ps = con.prepareStatement("Select nom, capacite, available,image from Amphis");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String nom = rs.getString("nom");
                int capacite = rs.getInt("capacite");
                boolean available=rs.getBoolean("available");
                String image=rs.getString("image");
                //Assuming you have a user object
                Amphis amphis = new Amphis(nom, capacite,available,image);

                amphisList.add(amphis);
            }
        } catch (SQLException ex) {
            System.out.println("Login error -->" + ex.getMessage());
return amphisList;
        } finally {
            DataConnect.close(con);
        }
        System.out.println(amphisList);
        return amphisList;
    }
}
