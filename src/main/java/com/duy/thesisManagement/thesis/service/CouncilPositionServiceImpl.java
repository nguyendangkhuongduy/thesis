package com.duy.thesisManagement.thesis.service;

import com.duy.thesisManagement.thesis.dto.CouncilPositionCreationDTO;
import com.duy.thesisManagement.thesis.dto.CouncilPositionRequestDTO;
import com.duy.thesisManagement.thesis.dto.CouncilPositionUpdatingDTO;
import com.duy.thesisManagement.thesis.exception.BadRequestException;
import com.duy.thesisManagement.thesis.model.Council;
import com.duy.thesisManagement.thesis.model.CouncilPosition;
import com.duy.thesisManagement.thesis.model.Position;
import com.duy.thesisManagement.thesis.model.User;
import com.duy.thesisManagement.thesis.repository.CouncilPositionRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CouncilPositionServiceImpl implements CouncilPositionService{

    private final CouncilPositionRepository councilPositionRepository;
    private final CouncilCoreService councilCoreService;

    private final PositionService positionService;
    private final UserService userService;


    @Override
    public List<CouncilPositionRequestDTO> getAllCouncilPosition() {
        List<CouncilPosition>  councilPositions = councilPositionRepository.findAll();
        List<CouncilPositionRequestDTO> result = councilPositions.stream()
                .map(this::toCouncilPositionRequestDTO1).collect(Collectors.toList());
        return result;
    }

    // FIXME not done, need further action here
    @Override
    public List<CouncilPositionRequestDTO> getCouncilPositionByCouncilId(Integer id) {
        List<CouncilPosition> cp =  this.councilPositionRepository.findByCouncilId(id);
//        return this.toCouncilPositionRequestDTO((CouncilPosition) cp);
        return Collections.EMPTY_LIST;
    }

    @Override
    public CouncilPositionRequestDTO createdCouncilPosition(CouncilPositionCreationDTO councilPositionCreationDTO) {
        CouncilPosition councilPosition = this.toCpDTO(councilPositionCreationDTO);
        CouncilPosition saved = this.councilPositionRepository.save(councilPosition);
        return this.toCouncilPositionRequestDTO(saved);
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

    @Override
    public Set<CouncilPosition> constructSetOfCouncilPosition(Set<CouncilPositionCreationDTO> councilPositionCreationDTOs) {
        Set<CouncilPosition> result = new HashSet<>();
        for (CouncilPositionCreationDTO dto : councilPositionCreationDTOs) {
            Position position = null;
            User user = null;
            if (Objects.nonNull(dto.getPositionId())) {
                position = this.positionService.getPositionByID(dto.getPositionId());
            } else {
                throw new BadRequestException("Cannot create Council because missing positionId");
            }
            if (Objects.nonNull(dto.getUserId())) {
                user = userService.getUserByID(dto.getUserId());
            } else {
                throw new BadRequestException("Cannot create Council because missing userId");
            }
            CouncilPosition councilPosition = CouncilPosition.builder()
                    .positionId(position)
                    .userId(user)
                    .build();
            result.add(councilPosition);
        }
        return result;
    }

    public CouncilPosition toCpDTO(CouncilPositionCreationDTO councilPositionCreationDTO) {
        Council council = null;
        Position position = null;
        User user = null;
        if (Objects.nonNull(councilPositionCreationDTO.getCouncilId())) {
            council = this.councilCoreService.getCouncilByID(councilPositionCreationDTO.getCouncilId());
        }
        if (Objects.nonNull(councilPositionCreationDTO.getPositionId())) {
            position = this.positionService.getPositionByID(councilPositionCreationDTO.getPositionId());
        }
        if (Objects.nonNull(councilPositionCreationDTO.getUserId())) {
            user = userService.getUserByID(councilPositionCreationDTO.getUserId());
        }
        CouncilPosition councilPosition = CouncilPosition.builder()
                .councilId(council)
                .positionId(position)
                .userId(user)
                .build();
        return councilPosition;
    }

    private CouncilPositionRequestDTO toCouncilPositionRequestDTO(CouncilPosition councilPosition) {
        CouncilPositionRequestDTO positionRequestDTO = CouncilPositionRequestDTO.builder()
                .id(councilPosition.getId())
                .userId(councilPosition.getUserId().getId())
                .positionId(councilPosition.getPositionId().getId())
                .councilId(councilPosition.getCouncilId().getId())
                .build();
        return positionRequestDTO;
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
