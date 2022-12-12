package com.example.univmanag.beans;

import com.example.univmanag.dao.ReservationsDao;
import com.example.univmanag.dao.ResourcesDao;
import jakarta.ejb.EJB;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.event.ActionEvent;
import jakarta.inject.Named;

import java.io.File;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Named
@SessionScoped
public class Ressources implements Serializable {
    String type;
    String nom;
    int id;
    boolean available;
    String departement;
    String image;
    File imaj;

    private String show = "all";
    private Date search_date_debut;
    private Date search_date_fin;
    @EJB
    ResourcesDao resourcesDao;

    @EJB
    private ReservationsDao reservationsDao;

    public File getImaj() {
        return imaj;
    }

    public void setImaj(File imaj) {
        this.imaj = imaj;
    }

    public Ressources(String type, String nom, int id, boolean available, String departement, String image) {
        this.type = type;
        this.nom = nom;
        this.id = id;
        this.available = available;
        this.departement = departement;
        this.image = image;

    }

    public Ressources(String type, String nom, boolean available, String departement, String image) {
        this.type = type;
        this.id = (int) (Math.random() * 900) + 25;
        this.nom = nom;
        this.available = available;
        this.departement = departement;
        this.image = image;
    }

    public Ressources() {
    }


    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public String getDepartement() {
        return this.departement;
    }

    public void setDepartement(String departement) {
        this.departement = departement;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public List<Ressources> getRessources() {
        return resourcesDao.getRessources(show, search_date_debut, search_date_fin);
    }

    public String addRessource() {
        boolean persisterd = resourcesDao.addRessource((int) (Math.random() * 900) + 25, nom, type, departement, image);
        if (persisterd)
            return "ressources";
        else
            return "addRessource";
    }

    public void processConsoleAction(ActionEvent event) {
        System.out.println(event);
    }

    public void processConsoleActionMakeReservation(String nom, Boolean available) {

        if (available)
            resourcesDao.makeResourcesReserved(nom);
        else
            resourcesDao.makeResourcesUnReserved(nom);

    }

    public void processConsoleActionDeleteResources(int id) {

        if (reservationsDao.isReserved(id))
            return;
        else
            resourcesDao.deleteResources(nom);
    }

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
}
