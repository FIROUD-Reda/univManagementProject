package com.example.univmanag.beans;

import com.example.univmanag.dao.AmphiDao;
import jakarta.ejb.EJB;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;

import jakarta.faces.event.ActionEvent ;
import java.io.Serializable;
import java.util.List;

@Named
@SessionScoped
public class Amphis implements Serializable {

    private String nom;
    private Long id;
    private int capacite;
    private boolean available;
    private String image;

    @EJB
    private AmphiDao amphisDAO ;

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Amphis(String nom, int capacite, boolean available,String image) {
        this.id= (long) (Math.random() * 900) + 25;
        this.nom=nom;
        this.capacite=capacite;
        this.available=available;
        this.image=image;
    }

    public Amphis(String nom, Long id, int capacite, boolean available,String image) {
        this.nom = nom;
        this.id = id;
        this.capacite = capacite;
        this.available = available;
        this.image=image;
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
        return amphisDAO.getAmphis();
    }

    public String addAmphi() {
        boolean persisted= amphisDAO.addAmphi((int) (Math.random() * 900) + 25,nom,capacite,available,"https://www.letudiant.fr/uploads/mediatheque/ETU_ETU/7/4/253974-universite-versailles-saint-quentin-uvsq-amphi-licence-1-droit-septembre-2014-camille-stromboni-6-original.jpg");
        if(persisted)
            return "amphis";
        else
            return "addAmphi";
    }

    public  void processConsoleAction(ActionEvent event){
        System.out.println(event);
    }

}
