package com.example.univmanag.beans;

import com.example.univmanag.dao.facade.ReservationsDao;
import com.example.univmanag.dao.facade.SallesDao;
import jakarta.ejb.EJB;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.event.ActionEvent;
import jakarta.inject.Named;

import java.io.Serializable;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

@Named
@SessionScoped
public class Salles implements Serializable {
    private String nom;
    private String datedebut;
    private String datefin;
    private int id;
    private int capacite;
    private boolean available;
    private String image;
    private String departement;
    private String show = "all";
    private Date search_date_debut;
    private Date search_date_fin;
    @EJB
    private SallesDao sallesDao;
    @EJB
    private ReservationsDao reservationsDao;

    public String getDatedebut() {
        return datedebut;
    }

    public void setDatedebut(String datedebut) {
        this.datedebut = datedebut;
    }

    public String getDatefin() {
        return datefin;
    }

    public void setDatefin(String datefin) {
        this.datefin = datefin;
    }

    public String getShow() {
        return show;
    }

    public void setShow(String show) {
        this.show = show;
    }


    public SallesDao getSallesDao() {
        return sallesDao;
    }

    public void setSallesDao(SallesDao sallesDao) {
        this.sallesDao = sallesDao;
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDepartement() {
        return departement;
    }

    public void setDepartement(String departement) {
        this.departement = departement;
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

    public Salles() {
    }

    public Salles(String nom, int id, int capacite, boolean available, String image, String departement) {
        this.nom = nom;
        this.id = id;
        this.capacite = capacite;
        this.available = available;
        this.image = image;
        this.departement = departement;
    }

    public Salles(String nom, int capacite, boolean available, String image, String departement) {
        this.nom = nom;
        this.id = (int) (Math.random() * 900) + 25;
        this.capacite = capacite;
        this.available = available;
        this.image = image;
        this.departement = departement;
    }

    public List<Salles> getSalles() {
        return sallesDao.getSalles(show, search_date_debut, search_date_fin);
    }

    public String reserveSalle(String nom) throws ParseException {
        sallesDao.reserveSalle(nom, datedebut, datefin);
        return "Salles";
    }

    public void processConsoleActionSearch(ActionEvent event) {
    }


    public void processConsoleActionDeleteSalle(int id) {

        if (reservationsDao.isReserved(id))
            return;
        else
            sallesDao.deleteSalle(nom);
    }

    @Override
    public String toString() {
        return "Salles{" +
                "nom='" + nom + '\'' +
                ", id=" + id +
                ", capacite=" + capacite +
                ", available=" + available +
                ", image='" + image + '\'' +
                ", departement='" + departement + '\'' +
                '}';
    }

    public String addSalle() {
        boolean persisted = sallesDao.addSalle((int) (Math.random() * 900) + 25, nom, capacite, departement, image);
        if (persisted)
            return "Salles";
        else
            return "addSalle";
    }

    public void processConsoleAction(ActionEvent event) {
        System.out.println(event);
    }
}
