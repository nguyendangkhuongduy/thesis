package com.duy.thesisManagement.thesis.service;


import com.duy.thesisManagement.thesis.dao.ThesisDAO;
import com.duy.thesisManagement.thesis.dto.ThesisRequestDTO;
import com.duy.thesisManagement.thesis.dto.UserDetailsImpl;
import com.duy.thesisManagement.thesis.model.Thesis;
import com.duy.thesisManagement.thesis.repository.ThesisRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ThesisServiceImpl implements ThesisService {

    @Autowired
    private ThesisDAO thesisDAO;

    @Override
    public List<Thesis> getThesis() {
        return thesisDAO.getThesis();
    }

    @Override
    public boolean createdThesis(Thesis thesis) {
        return thesisDAO.createdThesis(thesis);
    }

    @Override
    public Thesis getThesisById(int id) {
        return thesisDAO.getThesisById(id);
    }

    @Override
    public boolean deleteThesis(int id) {
        return thesisDAO.deleteThesis(id);
    }
}
