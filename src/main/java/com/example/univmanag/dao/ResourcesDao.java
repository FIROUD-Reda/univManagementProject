package com.example.univmanag.dao;
import com.example.univmanag.beans.Ressources;

import java.util.Date;
import java.util.List;

public interface ResourcesDao {
    public  List<Ressources> getRessources(String show, Date search_date_debut, Date search_date_fin);
    public  boolean addRessource(int i, String nom, String type, String departement, String image);
    void makeResourcesUnReserved(String nom);
    void    makeResourcesReserved(String nom);
    void deleteResources(String nom);

}
