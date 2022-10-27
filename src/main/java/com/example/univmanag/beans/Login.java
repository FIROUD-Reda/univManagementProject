package com.example.univmanag.beans;

import java.io.Serializable;


import com.example.univmanag.dao.LoginDAOImpl;
import com.example.univmanag.dao.LoginDao;
import jakarta.ejb.EJB;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Named;
import jakarta.servlet.http.HttpSession;

@Named
@SessionScoped
public class Login implements Serializable {

    private static final long serialVersionUID = 1094801825228386363L;

    private String pwd;
    private String msg;
    private String user;

    private String firstName;
    private String lastName;
    private String university;
    private String faculty;

    @EJB
    private LoginDao loginService ;

    public String getFaculty() {
        return faculty;
    }

    public void setFaculty(String faculty) {
        this.faculty = faculty;
    }

    public String getUniversity() {
        return university;
    }

    public void setUniversity(String university) {
        this.university = university;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    //validate login
    public String validateUsernamePassword() {
        boolean valid = loginService.validate(user, pwd);
        if (valid) {
            System.out.println(user + pwd);
            HttpSession session = SessionUtils.getSession();
            session.setAttribute("username", user);
            return "admin";
        } else {
            FacesContext.getCurrentInstance().addMessage(
                    null,
                    new FacesMessage(FacesMessage.SEVERITY_WARN,
                            "Incorrect Username and Passowrd",
                            "Please enter correct username and Password"));
            return "login";
        }
    }

    public String register() {
        boolean valid = loginService.verifyExistence(user);
        if (valid) {
            System.out.println(user);
            boolean registred = LoginDAOImpl.persist(user, pwd, firstName, lastName, university, faculty);
            if(registred)
                return "login";
            else
                return "register";
        }
        return "register";
    }

    //logout event, invalidate session
    public String logout() {
        HttpSession session = SessionUtils.getSession();
        session.invalidate();
        return "login";
    }

    public String reroute(String sthg) {
        return sthg;
    }

}