package com.example.univmanag.beans;

import com.example.univmanag.dao.facade.ReservationsDao;
import jakarta.ejb.EJB;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.event.ActionEvent;
import jakarta.inject.Named;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Named
@SessionScoped
public class Reservation implements Serializable {

    private Date datedebut;
    private Date datefin;

    private String resourceName;
    private String username;
    private String status;

    private int id;


    private String show = "all";
    private Date search_date_debut;
    private Date search_date_fin;
    @EJB
    private ReservationsDao reservationsDao ;

    public void processConsoleActionMakeReservation(String resource_name, int resource_id,int user_id,String user_name) {
        reservationsDao.addReservation(datedebut,datefin,user_id, user_name,resource_id ,resource_name);
    }

    public Date getDatedebut() {
        return datedebut;
    }

    public void setDatedebut(Date datedebut) {
        this.datedebut = datedebut;
    }

    public Date getDatefin() {
        return datefin;
    }

    public void setDatefin(Date datefin) {
        this.datefin = datefin;
    }


    public String getResourceName() {
        return resourceName;
    }

    public void setResourceName(String resourceName) {
        this.resourceName = resourceName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getShow() {
        return show;
    }

    public void setShow(String show) {
        this.show = show;
    }

    public Date getSearch_date_debut() {
        return search_date_debut;
    }

    public void setSearch_date_debut(Date search_date_debut) {
        this.search_date_debut = search_date_debut;
    }

    public Date getSearch_date_fin() {
        return search_date_fin;
    }

    public void setSearch_date_fin(Date search_date_fin) {
        this.search_date_fin = search_date_fin;
    }

    public List<Reservation> getReservations() {
        return reservationsDao.getAllReservation(show, search_date_debut, search_date_fin);
    }
    public List<Reservation> getUserReservations() {
        return reservationsDao.getAllReservation(show, search_date_debut, search_date_fin);
    }

    public Reservation(Date datedebut, Date datefin, String resourceName, String username, String status, int id) {
        this.datedebut = datedebut;
        this.datefin = datefin;
        this.resourceName = resourceName;
        this.username = username;
        this.status = status;
        this.id = id;
    }

    public void processConsoleActionSearch(ActionEvent event) {
    }

    @Override
    public String toString() {
        return "Reservation{" +
                "datedebut=" + datedebut +
                ", datefin=" + datefin +
                ", resourceName='" + resourceName + '\'' +
                ", username='" + username + '\'' +
                ", id=" + id +
                '}';
    }

    public Reservation() {
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void processConsoleActionDeleteReservation(int id) {

            reservationsDao.deleteResrvation(id);
    }

    public void processConsoleActionCancelReservation(int id) {
        reservationsDao.cancelResrvation(id);

    }


}
