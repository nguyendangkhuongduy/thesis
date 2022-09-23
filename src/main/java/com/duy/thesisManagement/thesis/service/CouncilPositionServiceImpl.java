package com.duy.thesisManagement.thesis.service;

import com.duy.thesisManagement.thesis.dto.CouncilPositionCreationDTO;
import com.duy.thesisManagement.thesis.dto.CouncilPositionRequestDTO;
import com.duy.thesisManagement.thesis.dto.CouncilPositionUpdatingDTO;
import com.duy.thesisManagement.thesis.dto.UserDTO;
import com.duy.thesisManagement.thesis.exception.ResourceNotFoundException;
import com.duy.thesisManagement.thesis.model.*;
import com.duy.thesisManagement.thesis.repository.CouncilPositionRepository;
import com.duy.thesisManagement.thesis.repository.CouncilRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CouncilPositionServiceImpl implements CouncilPositionService{

    private final CouncilPositionRepository councilPositionRepository;
    private final CouncilRepository councilRepository;
    private final CouncilService councilService;
    private final PositionService positionService;
    private final UserService userService;


    @Override
    public List<CouncilPositionRequestDTO> getAllCouncilPosition() {
        List<CouncilPosition>  councilPositions = councilPositionRepository.findAll();
        List<CouncilPositionRequestDTO> result = councilPositions.stream().map(this::toCouncilPositionRequestDTO1).collect(Collectors.toList());
        return result;
    }

    @Override
    public List<CouncilPositionRequestDTO> getCouncilPositionByCouncilId(Integer id) {
        List<CouncilPosition> cp = (List<CouncilPosition>) this.councilPositionRepository.findCouncilPositionByCouncilId(id);
        return this.toCouncilPositionRequestDTO((CouncilPosition) cp);
    }

    @Override
    public CouncilPositionRequestDTO createdCouncilPosition(CouncilPositionCreationDTO councilPositionCreationDTO) {
        CouncilPosition councilPosition = this.toCpDTO(councilPositionCreationDTO);
        CouncilPosition saved = this.councilPositionRepository.save(councilPosition);
        return (CouncilPositionRequestDTO) toCouncilPositionRequestDTO(saved);
    }

    @Override
    public CouncilPositionRequestDTO updatedCouncilPosition(CouncilPositionUpdatingDTO councilPositionUpdatingDTO) {
//            updatingUser.setEmail(userUpdatingDTO.getEmail());
//            updatingUser.setFullName(userUpdatingDTO.getFullName());
//            updatingUser.setGender(userUpdatingDTO.getGender());
//            updatingUser.setPhone(userUpdatingDTO.getPhone());
//
//            Set<Role> roles = this.getExistingRoles(userUpdatingDTO.getRoles());
//            updatingUser.setRoles(roles);
//
//            User savedUser = this.userRepository.save(updatingUser);
//            return this.toUserDTO(savedUser);
        return null;
    }

    private CouncilPosition toCpDTO(CouncilPositionCreationDTO councilPositionCreationDTO) {
        Council council = null;
        Position position = null;
        User user = null;
        if (Objects.nonNull(councilPositionCreationDTO.getCouncilId())) {
            council = this.councilService.getCouncilByID(councilPositionCreationDTO.getCouncilId());
        }
        if (Objects.nonNull(councilPositionCreationDTO.getPositionId())) {
            position = this.positionService.getPositionByID(councilPositionCreationDTO.getPositionId());
        }
        if (Objects.nonNull(councilPositionCreationDTO.getCouncilId())) {
            user = this.userService.getUserByID(councilPositionCreationDTO.getUserId());
        }
        CouncilPosition councilPosition = CouncilPosition.builder()
                .councilId(council)
                .positionId(position)
                .userId(user)
                .build();
        return councilPosition;
    }

    private List<CouncilPositionRequestDTO> toCouncilPositionRequestDTO(CouncilPosition councilPosition) {
        CouncilPositionRequestDTO positionRequestDTO = CouncilPositionRequestDTO.builder()
                .id(councilPosition.getId())
                .userId(councilPosition.getUserId().getId())
                .positionId(councilPosition.getPositionId().getId())
                .councilId(councilPosition.getCouncilId().getId())
                .build();
        return (List<CouncilPositionRequestDTO>) positionRequestDTO;
    }

    private CouncilPositionRequestDTO toCouncilPositionRequestDTO1(CouncilPosition councilPosition) {
        CouncilPositionRequestDTO positionRequestDTO = CouncilPositionRequestDTO.builder()
                .id(councilPosition.getId())
                .userId(councilPosition.getUserId().getId())
                .positionId(councilPosition.getPositionId().getId())
                .councilId(councilPosition.getCouncilId().getId())
                .build();
        return positionRequestDTO;
    }

}
