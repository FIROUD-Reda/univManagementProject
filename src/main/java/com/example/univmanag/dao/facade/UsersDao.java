package com.example.univmanag.dao.facade;

import com.example.univmanag.beans.Reservation;
import com.example.univmanag.beans.Users;

import java.util.Date;
import java.util.List;

public interface UsersDao {
    public List<Users> getAllUsers(String username);
    void deleteUser(int id);
    void blockUser(int id);
}
