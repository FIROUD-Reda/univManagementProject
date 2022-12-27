package com.example.univmanag.dao;

import com.example.univmanag.beans.Reservation;
import com.example.univmanag.beans.Users;
import com.example.univmanag.dao.facade.AmphiDao;
import com.example.univmanag.dao.facade.UsersDao;
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

@Local(UsersDao.class)
@Stateless
public class UsersDAOImpl implements UsersDao {
    @Override
    public List<Users> getAllUsers(String username) {
        Connection con = null;
        PreparedStatement ps = null;
        List<Users> ressourcesList = new ArrayList<>();
        try {

            con = DataConnect.getConnection();
            assert con != null;

            if (username==null)
                ps = con.prepareStatement("Select * from users where role = 0");
            else {
                ps = con.prepareStatement("select * from users where uname = ? and role = 0");
                ps.setString(1, username);
            }

            ResultSet rs = ps.executeQuery();
            System.out.println("ahiata"+rs);
            while (rs.next()) {
                int id = rs.getInt("uid");
                String uname = rs.getString("uname");
                Boolean status = rs.getBoolean("status");
                String faculty = rs.getString("faculty");

                //Assuming you have a user object
                Users users = new Users(id, uname, faculty,status);
                System.out.println("UsersDaoImpl -->" + users.toString());

                ressourcesList.add(users);
            }
        } catch (SQLException ex) {
            System.out.println("UsersDaoImpl error -->" + ex.getMessage());
            return ressourcesList;
        } finally {
            DataConnect.close(con);
        }
        System.out.println(ressourcesList);
        return ressourcesList;
    }

    @Override
    public void deleteUser(int id) {
        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = DataConnect.getConnection();
            assert con != null;
            ps = con.prepareStatement("delete from users where uid=?");
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("UsersDAo error -->" + ex.getMessage());

        } finally {
            DataConnect.close(con);
        }
    }

    @Override
    public void blockUser(int id) {
        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = DataConnect.getConnection();
            assert con != null;
            ps = con.prepareStatement("update users set status = ? where uid=?");
            ps.setInt(2, id);
            ps.setBoolean(1, false);
            ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("UsersDAO error -->" + ex.getMessage());

        } finally {
            DataConnect.close(con);
        }
    }
}
