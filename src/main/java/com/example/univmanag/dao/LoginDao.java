package com.example.univmanag.dao;


public interface LoginDao {
    public  boolean validate(String user, String password);
    public  boolean verifyExistence(String user);
    public  boolean persist(String user, String pwd, String firstName, String lastName, String university, String faculty);
}
