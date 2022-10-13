package com.example.univmanag.beans;

import com.example.univmanag.dao.SallesDAO;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;

import java.io.Serializable;
import java.util.List;

@Named
@SessionScoped
public class Salles implements Serializable {
    private String nom;
    private Long id;
    private int capacite;
    private boolean available;
    private String image;
private String departement;

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
        return SallesDAO.getSalles();
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
}
