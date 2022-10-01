package com.duy.thesisManagement.thesis.service;

import com.duy.thesisManagement.thesis.dto.*;
import com.duy.thesisManagement.thesis.exception.BadRequestException;
import com.duy.thesisManagement.thesis.exception.ResourceNotFoundException;
import com.duy.thesisManagement.thesis.model.*;
import com.duy.thesisManagement.thesis.repository.PositionRepository;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PositionServiceImpl implements PositionService{

    private final PositionRepository positionRepository;

    @Override
    public List<PositionDTO> getAllPosition() {
        List<Position> position = positionRepository.findByActive(true);
        List<PositionDTO> result = position.stream().map(this::toPositionDTO).collect(Collectors.toList());
        return result;
    }

    @Override
    public PositionDTO createPosition(PositionCreationDTO positionCreationDTO) {
        Position position = this.toPosition(positionCreationDTO);
        position.setActive(true);
        Position saved = this.positionRepository.save(position);
        return toPositionDTO(saved);
    }

    @Override
    public void deletePosition(Integer id) {
        Optional<Position> positionOpt = this.positionRepository.findById(id);
        if (positionOpt.isPresent()) {
            Position deleted= positionOpt.get();
            this.positionRepository.delete(deleted);
            return;
        }
        throw new ResourceNotFoundException("Cannot find any Position for deleting action with id: " + id);
    }

    @Override
    public PositionDTO getPositionById(Integer id) {
        Optional<Position> position = this.positionRepository.findById(id);
        if (position.isPresent()) {
            return this.toPositionDTO(position.get());
        }
        throw new ResourceNotFoundException("Cannot find any position with id: " + id);
    }

    @Override
    public Position getPositionByID(Integer id) {
        Optional<Position> position = this.positionRepository.findById(id);
        if (position.isPresent()) {
            return position.get();
        }
        throw new ResourceNotFoundException("Cannot find any position with id: " + id);
    }

    private PositionDTO toPositionDTO(Position position) {
        PositionDTO positionDTO = PositionDTO.builder()
                .id(position.getId())
                .name(position.getName())
                .build();
        return positionDTO;
    }

//    private void validateNewPosition(PositionCreationDTO positionDTO) {
//        boolean nameExisted = this.positionRepository.existsByName(positionDTO.getName());
//        StringBuilder errorMessageBuilder = new StringBuilder();
//        if (nameExisted) {
//            errorMessageBuilder.append("Cannot create position: name already existed");
//        }
//        String errorMessage = errorMessageBuilder.toString();
//        if (StringUtils.isNotBlank(errorMessage)) {
//            throw new BadRequestException(errorMessage);
//        }
//    }

    private Position toPosition(PositionCreationDTO positionCreationDTO) {
        Position position = Position.builder()
                .name(positionCreationDTO.getName())
                .build();
        return position;
    }
}
