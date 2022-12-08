package com.example.univmanag.beans;

import com.example.univmanag.dao.ReservationsDao;
import com.example.univmanag.dao.SallesDao;
import jakarta.ejb.EJB;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;

import java.io.Serializable;
import java.text.ParseException;
import java.util.Date;

@Named
@SessionScoped
public class Reservation implements Serializable {

    private Date datedebut;
    private Date datefin;

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
}
