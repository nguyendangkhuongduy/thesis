package com.duy.thesisManagement.thesis.dao;

import com.duy.thesisManagement.thesis.model.Thesis;

import java.util.List;

public interface ThesisDAO {
    public List<Thesis> getThesis();

    public boolean createdThesis(Thesis thesis);


    public Thesis getThesisById(int id);

    public boolean deleteThesis(int id);
}
