package com.example.univmanag.dao;

import com.example.univmanag.beans.Amphis;

import java.util.List;

public interface AmphiDao {
     boolean addAmphi(int i, String nom, int capacite, boolean available, String s);
      List<Amphis> getAmphis() ;
}
