package com.duy.thesisManagement.thesis.dao;


import com.duy.thesisManagement.thesis.model.Position;

import java.util.List;

public interface PositionDAO {
    public List<Position> getPosition();

    public boolean createdCouncil(Position position);

}
