package com.example.univmanag.dao;


import com.example.univmanag.beans.Professors;

import java.util.List;

public interface ProfesorsDao {
    public  boolean addProfessor(int a, String nom, String sex, String departement, String image);
    public List<Professors> getProfessors();
}
