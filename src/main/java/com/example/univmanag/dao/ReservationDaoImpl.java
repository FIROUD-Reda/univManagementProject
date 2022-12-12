package com.example.univmanag.dao;

import com.example.univmanag.beans.Reservation;
import com.example.univmanag.util.DataConnect;
import jakarta.ejb.Local;
import jakarta.ejb.Stateless;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

@Local(ReservationsDao.class)
@Stateless
public class ReservationDaoImpl implements ReservationsDao {
    @Override
    public boolean addReservation(Date date_debut, Date date_fin, int user_id, String user_name, int resource_id, String resource_name) {
        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = DataConnect.getConnection();
            assert con != null;
            ps = con.prepareStatement("INSERT INTO reservations(date_debut,date_fin,user_id,user_name,resource_id, resource_name) values (?,?,?,?,?,?)");
            ps.setDate(1, new java.sql.Date(date_debut.getTime()));
            ps.setDate(2, new java.sql.Date(date_fin.getTime()));
            ps.setInt(3, user_id);
            ps.setString(4, user_name);
            ps.setInt(5, resource_id);
            ps.setString(6, resource_name);


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
    public List<Reservation> getReservation(String show) {
        return null;
    }

    @Override
    public List<Reservation> getAllReservation(String show) {
        return null;
    }

    @Override
    public Boolean isReserved(int id) {
        System.out.println("I'm here!");
        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = DataConnect.getConnection();
            assert con != null;
            ps = con.prepareStatement("select * from reservations where resource_id = ? and date_fin > ?");
            ps.setDate(2, new java.sql.Date(new Date().getTime()));
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next() == false) {
                System.out.println("ResultSet is empty");
                return false;
            } else {
                return true;
            }
        } catch (SQLException ex) {
            System.out.println("Login error -->" + ex.getMessage());
            return false;
        } finally {
            DataConnect.close(con);
        }

    }
}
