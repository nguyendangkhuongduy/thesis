package com.duy.thesisManagement.thesis.service;

import com.duy.thesisManagement.thesis.dto.ThesisPositionDTO;
import com.duy.thesisManagement.thesis.dto.UserCreationDTO;
import com.duy.thesisManagement.thesis.dto.UserDTO;
import com.duy.thesisManagement.thesis.model.*;
import com.duy.thesisManagement.thesis.repository.PositionForThesisRepository;
import com.duy.thesisManagement.thesis.repository.ThesisPositionRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;
import java.util.Set;

@Service
@AllArgsConstructor
public class ThesisPositionServiceImpl implements ThesisPositionService{

    private final UserService userService;
    private final ThesisService thesisService;

    private final ThesisPositionRepository thesisPositionRepository;

    private final PositionForThesisService positionForThesisService;


    @Override
    public ThesisPositionDTO createdThesisPosition(ThesisPositionDTO thesisPositionDTO) {
        ThesisPosition thesisPosition = this.toThesisPosition(thesisPositionDTO);
        ThesisPosition save = this.thesisPositionRepository.save(thesisPosition);
        return null;
    }

    private ThesisPosition toThesisPosition(ThesisPositionDTO thesisPosition) {
        User user = null;
        Thesis thesis = null;
        PositionForThesis thesisP = null;
        if (Objects.nonNull(thesisPosition.getUserId())) {
            user = this.userService.getUserByID(thesisPosition.getUserId());
        }
        if (Objects.nonNull(thesisPosition.getThesisId())) {
            thesis = this.thesisService.getThesisByID(thesisPosition.getUserId());
        }
        if (Objects.nonNull(thesisPosition.getThesisPosition())) {
            thesisP = this.positionForThesisService.findById(thesisPosition.getThesisPosition());
        }

        ThesisPosition thesisPosition1 = ThesisPosition.builder()
                .userId(user)
                .thesisId(thesis)
                .thesisPosition(thesisP)
                .build();
        return thesisPosition1;
    }
}
