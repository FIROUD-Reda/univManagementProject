package com.example.univmanag.dao;
import com.example.univmanag.beans.Ressources;
import com.example.univmanag.beans.Salles;

import java.util.List;

public interface SallesDao {
    public boolean addSalle(int i, String nom, int capacite, String departement, String s) ;
    public List<Salles> getSalles();

}
