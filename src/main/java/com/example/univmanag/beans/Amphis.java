package com.example.univmanag.beans;

import com.example.univmanag.dao.AmphiDao;
import jakarta.ejb.EJB;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;

import jakarta.faces.event.ActionEvent;

import java.io.Serializable;
import java.util.ArrayList;
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
    private String text="#000000";

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

    @EJB
    private AmphiDao amphisDAO;

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

    String show = "all";

    public List<Amphis> getAmphis() {
        System.out.println("im being called");
        return amphisDAO.getAmphis(show);
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
    public void processConsoleActionDeleteAmphi(String nom,Boolean available) {

        amphisDAO.deleteAmphi(nom);


    }
    public void processConsoleActionMakeReservation(String nom,Boolean available) {
        if(available)
        amphisDAO.makeAmphiReserved(nom);
        else
            amphisDAO.makeAmphiUnReserved(nom);

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
