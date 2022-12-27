package com.example.univmanag.dao;

import com.example.univmanag.beans.Reservation;
import com.example.univmanag.dao.facade.ReservationsDao;
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
    public List<Reservation> getAllReservation(String show, Date search_date_debut, Date search_date_fin) {
        Connection con = null;
        PreparedStatement ps = null;
        List<Reservation> ressourcesList = new ArrayList<>();
        try {

            con = DataConnect.getConnection();
            assert con != null;

            if (search_date_debut==null && search_date_fin == null)
                ps = con.prepareStatement("Select * from reservations");
            else if (search_date_debut!=null && search_date_fin != null) {
                ps = con.prepareStatement("select * from reservations where date_debut = ? and date_fin = ?");
                ps.setDate(1,  new java.sql.Date(search_date_debut.getTime()));
                ps.setDate(2,  new java.sql.Date(search_date_fin.getTime()));
            }

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String resource_name = rs.getString("resource_name");
                String status = rs.getString("status");
                String user_name = rs.getString("user_name");
                Date date_debut =rs.getDate("date_debut");
                Date date_fin =rs.getDate("date_fin");
                //Assuming you have a user object
                Reservation reservation = new Reservation(date_debut,date_fin,resource_name,user_name,status,id);
                System.out.println("ReservationDaoImpl -->" + reservation.toString());

                ressourcesList.add(reservation);
            }
        } catch (SQLException ex) {
            System.out.println("ReservationDaoImpl error -->" + ex.getMessage());
            return ressourcesList;
        } finally {
            DataConnect.close(con);
        }
        System.out.println(ressourcesList);
        return ressourcesList;
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
            System.out.println("ReservationDaoImpl error -->" + ex.getMessage());
            return false;
        } finally {
            DataConnect.close(con);
        }

    }

    @Override
    public void deleteResrvation(int id) {
        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = DataConnect.getConnection();
            assert con != null;
            ps = con.prepareStatement("delete from reservations where id=?");
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("Reservation error -->" + ex.getMessage());

        } finally {
            DataConnect.close(con);
        }
    }

    @Override
    public void cancelResrvation(int id) {
        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = DataConnect.getConnection();
            assert con != null;
            ps = con.prepareStatement("update reservations set status = ? where id=?");
            ps.setInt(2, id);
            ps.setString(1, "Annulé");
            ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("Reservation error -->" + ex.getMessage());

        } finally {
            DataConnect.close(con);
        }
    }
}
