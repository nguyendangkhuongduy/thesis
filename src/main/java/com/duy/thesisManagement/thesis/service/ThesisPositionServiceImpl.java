package com.duy.thesisManagement.thesis.service;

import com.duy.thesisManagement.thesis.dto.*;
import com.duy.thesisManagement.thesis.exception.ResourceNotFoundException;
import com.duy.thesisManagement.thesis.model.*;
import com.duy.thesisManagement.thesis.repository.PositionForThesisRepository;
import com.duy.thesisManagement.thesis.repository.ThesisPositionRepository;
import com.duy.thesisManagement.thesis.repository.ThesisRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ThesisPositionServiceImpl implements ThesisPositionService{

    private final UserService userService;
    private final ThesisService thesisService;

    private final ThesisPositionRepository thesisPositionRepository;

    private final PositionForThesisService positionForThesisService;

    private final ThesisRepository thesisRepository;



    @Override
    public List<ThesisPositionDTO> getThesisPositionByThesisId(Integer id) {
        Optional<Thesis> thesis = thesisRepository.findById(id);
        List<ThesisPosition> thesisPositions = thesisPositionRepository.findByThesisId(thesis.get());
        List<ThesisPositionDTO> result = thesisPositions.stream()
                .map(this::toThesisPositionDTO).collect(Collectors.toList());
        return result;
    }

    @Override
    public List<ThesisPositionDTO> getAllThesisPosition() {
        List<ThesisPosition> thesisPosition = thesisPositionRepository.findAll();
        List<ThesisPositionDTO> result = thesisPosition.stream().map(this::toThesisPositionDTO).collect(Collectors.toList());
        return result;
    }

    @Override
    public ThesisPositionDTO createThesisPosition(ThesisPositionCreationDTO thesisPositionCreationDTO) {
        ThesisPosition thesisPosition = this.toThesisPosition(thesisPositionCreationDTO);
        ThesisPosition saved = this.thesisPositionRepository.save(thesisPosition);
        return toThesisPositionDTO(saved);
    }

    @Override
    public void deleteThesisPosition(Integer id) {
        Optional<ThesisPosition> thesisPositionOpt = this.thesisPositionRepository.findById(id);
        if (thesisPositionOpt.isPresent()) {
            ThesisPosition deleted = thesisPositionOpt.get();
            this.thesisPositionRepository.delete(deleted);
            return;
        }
        throw new ResourceNotFoundException("Cannot find any Thesis Position for deleting action with id: " + id);
    }

    private ThesisPosition toThesisPosition(ThesisPositionCreationDTO thesisPositionCreationDTO) {
        User user = null;
        Thesis thesis = null;
        PositionForThesis thesisP = null;
        if (Objects.nonNull(thesisPositionCreationDTO.getUserId())) {
            user = this.userService.getUserByID(thesisPositionCreationDTO.getUserId());
        }
        if (Objects.nonNull(thesisPositionCreationDTO.getThesisId())) {
            thesis = this.thesisService.getThesisByID(thesisPositionCreationDTO.getUserId());
        }
        if (Objects.nonNull(thesisPositionCreationDTO.getThesisPosition())) {
            thesisP = this.positionForThesisService.findById(thesisPositionCreationDTO.getThesisPosition());
        }

        ThesisPosition thesisPosition1 = ThesisPosition.builder()
                .userId(user)
                .thesisId(thesis)
                .thesisPosition(thesisP)
                .build();
        return thesisPosition1;
    }

    private ThesisPositionDTO toThesisPositionDTO(ThesisPosition thesisPosition) {
        ThesisPositionDTO thesisPositionDTO = ThesisPositionDTO.builder()
                .id(thesisPosition.getId())
                .userId(thesisPosition.getUserId().getId())
                .thesisPosition(thesisPosition.getThesisPosition().getId())
                .thesisId(thesisPosition.getThesisId().getId())
                .build();
        return thesisPositionDTO;
    }
}
