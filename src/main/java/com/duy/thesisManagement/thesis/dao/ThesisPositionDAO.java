package com.duy.thesisManagement.thesis.dao;


import com.duy.thesisManagement.thesis.model.ThesisPosition;

import java.util.List;

public interface ThesisPositionDAO {
//    public List<ThesisPosition> getThesisPosition(int thesisId);

    public boolean createdThesisPosition(ThesisPosition thesisPosition);

    public boolean deleteThesis(int id);
}

