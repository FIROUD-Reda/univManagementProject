package com.example.univmanag.dao;

import com.example.univmanag.beans.Amphis;

import java.util.Date;
import java.util.List;

public interface AmphiDao {
    void makeAmphiUnReserved(String nom);

    boolean addAmphi(int i, String nom, int capacite, boolean available, String s);
      List<Amphis> getAmphis(String show, Date search_date_debut, Date search_date_fin) ;

    void makeAmphiReserved(String nom);
    void deleteAmphi(String nom);
}
