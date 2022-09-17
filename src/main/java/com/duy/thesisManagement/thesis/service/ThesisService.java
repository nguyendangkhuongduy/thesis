package com.duy.thesisManagement.thesis.service;

import com.duy.thesisManagement.thesis.model.Thesis;
import java.util.List;

public interface ThesisService {
    public List<Thesis> getThesis();

    public boolean createdThesis(Thesis thesis);

    public Thesis getThesisById(int id);

    public boolean deleteThesis(int id);



}
