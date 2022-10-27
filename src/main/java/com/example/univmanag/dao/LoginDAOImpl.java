package com.example.univmanag.dao;

import com.example.univmanag.util.DataConnect;
import jakarta.ejb.Local;
import jakarta.ejb.Stateless;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


@Local(LoginDao.class)
@Stateless
public class LoginDAOImpl implements  LoginDao {

    public  boolean validate(String user, String password) {
        Connection con = null;
        PreparedStatement ps = null;

        try {
            con = DataConnect.getConnection();
            assert con != null;
            ps = con.prepareStatement("Select uname, password from Users where uname = ? and password = ?");
            ps.setString(1, user);
            ps.setString(2, password);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                //result found, means valid inputs
                return true;
            }
        } catch (SQLException ex) {
            System.out.println("Login error -->" + ex.getMessage());
            return false;
        } finally {
            DataConnect.close(con);
        }
        return false;
    }

    public  boolean verifyExistence(String user) {
        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = DataConnect.getConnection();
            assert con != null;
            ps = con.prepareStatement("Select uname from Users where uname = ?");
            ps.setString(1, user);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                //result found, means valid inputs
                return false;
            }
        } catch (SQLException ex) {
            System.out.println("Login error -->" + ex.getMessage());
            return false;
        } finally {
            DataConnect.close(con);
        }
        return true;
    }

    public static boolean persist(String user, String pwd, String firstName, String lastName, String university, String faculty) {
        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = DataConnect.getConnection();
            assert con != null;
            ps = con.prepareStatement("INSERT INTO Users(uid,uname,password,firstName,lastName,university,faculty) values (?,?,?,?,?,?,?)");
            ps.setString(1, String.valueOf((int)(Math.random()*900)+25));
            ps.setString(2, user);
            ps.setString(3, pwd);
            ps.setString(4, firstName);
            ps.setString(5, lastName);
            ps.setString(6, university);
            ps.setString(7, faculty);
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
