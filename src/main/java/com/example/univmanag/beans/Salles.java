package com.example.univmanag.beans;

import com.example.univmanag.dao.SallesDAOImpl;
import com.example.univmanag.dao.SallesDao;
import jakarta.ejb.EJB;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.event.ActionEvent;
import jakarta.inject.Named;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Named
@SessionScoped
public class Salles implements Serializable {
    private String nom;
    private String datedebut;
    private String datefin;
    private Long id;
    private int capacite;
    private boolean available;
    private String image;
    private String departement;
    private String show="all";
    @EJB
    private SallesDao sallesDao ;
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

    public Salles() {
    }

    public Salles(String nom, Long id, int capacite, boolean available, String image, String departement) {
        this.nom = nom;
        this.id = id;
        this.capacite = capacite;
        this.available = available;
        this.image = image;
        this.departement = departement;
    }
    public Salles(String nom, int capacite, boolean available, String image, String departement) {
        this.nom = nom;
        this.id =  (long) (Math.random() * 900) + 25;
        this.capacite = capacite;
        this.available = available;
        this.image = image;
        this.departement = departement;
    }

    public List<Salles> getSalles() {
        return sallesDao.getSalles(show);
    }
    public String reserveSalle(String nom) throws ParseException {
        sallesDao.reserveSalle(nom,datedebut,datefin);
        return "Salles";
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
    public void processConsoleActionMakeReservation(String nom,Boolean available) {
        System.out.println("hiiiii reda");
        System.out.println("salam reda "+nom+available);
        if(available)
            sallesDao.makeSalleReserved(nom);
        else
            sallesDao.makeSalleUnReserved(nom);

    }
    public void processConsoleActionDeleteSalle(String nom,Boolean available) {

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

    public  String  addSalle() {
        boolean persisted= sallesDao.addSalle((int) (Math.random() * 900) + 25,nom,capacite,departement,image);
        if(persisted)
            return "Salles";
        else
            return "addSalle";
    }

    public  void processConsoleAction(ActionEvent event){
        System.out.println(event);
    }
}
