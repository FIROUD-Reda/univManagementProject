package com.example.univmanag.beans;

import com.example.univmanag.dao.facade.SallesDao;
import com.example.univmanag.dao.facade.UsersDao;
import jakarta.ejb.EJB;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;

import java.io.Serializable;
import java.util.List;

@Named
@SessionScoped
public class Users implements Serializable {

    String username;
    String email;
    Boolean status;
    int id;

    @EJB
    private UsersDao usersDao;

    String search_usrname;

    public List<Users> getUsers() {
        return usersDao.getAllUsers(search_usrname);
    }


    public void processConsoleActionDeleteUser(int id) {

        usersDao.deleteUser(id);
    }

    public void processConsoleActionBlockUser(int id) {
        usersDao.blockUser(id);

    }

    public Users(int id, String username, String email, Boolean status) {
        this.username = username;
        this.email = email;
        this.status = status;
        this.id = id;
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSearch_usrname() {
        return search_usrname;
    }

    public void setSearch_usrname(String search_usrname) {
        this.search_usrname = search_usrname;
    }

    public Users() {
    }
}
