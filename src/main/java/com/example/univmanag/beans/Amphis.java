package com.example.univmanag.beans;

import com.example.univmanag.dao.facade.AmphiDao;
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
public class Amphis implements Serializable {

    private String nom;
    private Long id;
    private int capacite;
    private boolean available;
    private String image;
    private String theme = "#FFFFFF";
    private String text = "#000000";

    private String show = "all";
    private Date search_date_debut;
    private Date search_date_fin;

    @EJB
    private AmphiDao amphisDAO;

    @EJB
    private ReservationsDao reservationsDao;

    public void processConsoleActionSearch(ActionEvent event) {
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

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getTheme() {
        return this.theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }


    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Amphis(String nom, int capacite, boolean available, String image) {
        this.id = (long) (Math.random() * 900) + 25;
        this.nom = nom;
        this.capacite = capacite;
        this.available = available;
        this.image = image;
    }

    public Amphis(String nom, Long id, int capacite, boolean available, String image) {
        this.nom = nom;
        this.id = id;
        this.capacite = capacite;
        this.available = available;
        this.image = image;
    }

    public Amphis() {
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getCapacite() {
        return capacite;
    }

    public void setCapacite(int capacite) {
        this.capacite = capacite;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }


    public List<Amphis> getAmphis() {
        System.out.println("im being called");
        return amphisDAO.getAmphis(show, search_date_debut, search_date_fin);
    }

    public String addAmphi() {
        boolean persisted = amphisDAO.addAmphi((int) (Math.random() * 900) + 25, nom, capacite, available, image);
        if (persisted)
            return "amphis";
        else
            return "addAmphi";
    }

    public void processConsoleActionDisponible(ActionEvent event) {
        this.show = "available";
    }

    public void processConsoleActionReserve(ActionEvent event) {
        this.show = "taken";

    }

    public void processConsoleActionAll(ActionEvent event) {
        this.show = "all";

    }

    public void processConsoleActionDeleteAmphi(int id) {

        if (reservationsDao.isReserved(id))
            return;
        else
            amphisDAO.deleteAmphi(nom);
    }


    public void processConsoleActionTheme(ActionEvent event) {
        if (this.theme == "#000000")
            this.theme = "#FFFFFF";
        else
            this.theme = "#000000";

        if (this.text == "#000000")
            this.text = "#FFFFFF";
        else
            this.text = "#000000";
    }
}
