package com.duy.thesisManagement.thesis.service;

import com.duy.thesisManagement.thesis.model.Thesis;
import java.util.List;

public interface ThesisService {
    public List<Thesis> getTheses();

    public Thesis createdThesis(Thesis thesis);

    public Thesis getThesisById(int id);

    public void deleteThesis(Integer id);



}
