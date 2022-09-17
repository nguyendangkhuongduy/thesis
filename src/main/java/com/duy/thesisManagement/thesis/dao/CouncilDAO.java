package com.duy.thesisManagement.thesis.dao;



import com.duy.thesisManagement.thesis.model.Council;

import java.util.List;

public interface CouncilDAO {
    public List<Council> getCouncil();

    public Council getCouncilById(int id);

    public boolean createdCouncil(Council council);

    public boolean deleteCouncil(int id);
}
