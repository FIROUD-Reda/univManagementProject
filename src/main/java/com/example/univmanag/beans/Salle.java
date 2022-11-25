package com.example.univmanag.beans;

import jakarta.persistence.*;

@Entity
public class Salle {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String nom;
    private String datedebut;
    private String datefin;
    private int capacite;
    private boolean available;
    private String image;
    @ManyToOne
    @JoinColumn(name = "departement_fk")
    private Departement departement;

    public Salle() {
    }

    public Salle(Long id, String nom, String datedebut, String datefin, int capacite, boolean available, String image, Departement departement) {
        this.id = id;
        this.nom = nom;
        this.datedebut = datedebut;
        this.datefin = datefin;
        this.capacite = capacite;
        this.available = available;
        this.image = image;
        this.departement = departement;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

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

    public Departement getDepartement() {
        return departement;
    }

    public void setDepartement(Departement departement) {
        this.departement = departement;
    }

}
