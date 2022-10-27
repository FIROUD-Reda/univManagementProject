package com.example.univmanag.beans;

import com.example.univmanag.dao.ProfesorsDao;
import com.example.univmanag.dao.ProfessorsDAOImpl;
import jakarta.ejb.EJB;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.event.ActionEvent;
import jakarta.inject.Named;

import java.io.Serializable;
import java.util.List;

@Named
@SessionScoped
public class Professors implements Serializable {
    private String nom;
    private Long id;
    private boolean available;
    private String image;
    private String departement;
    private String sex;

    @EJB
    ProfesorsDao profesorsDao ;

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

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
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

    public Professors() {
    }

    public Professors(String nom, Long id, String sex, boolean available, String image, String departement) {
        this.nom = nom;
        this.id = id;
        this.sex = sex;
        this.available = available;
        this.image = image;
        this.departement = departement;
    }
    public Professors(String nom, String sex, boolean available, String image, String departement) {
        this.nom = nom;
        this.id =  (long) (Math.random() * 900) + 25;
        this.sex = sex;
        this.available = available;
        this.image = image;
        this.departement = departement;
    }

    public List<Professors> getProfessors() {
        return profesorsDao.getProfessors();
    }


    public String addProfessor() {
        boolean persisted= profesorsDao.addProfessor((int) (Math.random() * 900) + 25,nom,sex,departement,"https://img.freepik.com/photos-gratuite/senior-professeur-masculin-expliquer-ecrire-au-tableau-vert_23-2148200956.jpg?w=2000");
        if(persisted)
            return "Professors";
        else
            return "addProfessor";
    }

    public  void processConsoleAction(ActionEvent event){
        System.out.println(event);
    }

}
