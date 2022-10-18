package com.duy.thesisManagement.thesis.service;

import com.duy.thesisManagement.thesis.dto.CouncilPositionCreationDTO;
import com.duy.thesisManagement.thesis.dto.CouncilPositionDTO;
import com.duy.thesisManagement.thesis.dto.CouncilPositionUpdatingDTO;
import com.duy.thesisManagement.thesis.dto.ThesisRequestDTO;
import com.duy.thesisManagement.thesis.exception.BadRequestException;
import com.duy.thesisManagement.thesis.exception.ResourceNotFoundException;
import com.duy.thesisManagement.thesis.model.*;
import com.duy.thesisManagement.thesis.repository.CouncilPositionRepository;
import com.duy.thesisManagement.thesis.repository.CouncilRepository;
import com.duy.thesisManagement.thesis.repository.UserRepository;
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

    private final UserRepository userRepository;
    private final CouncilRepository councilRepository;


    @Override
    public List<CouncilPositionDTO> getAllCouncilPosition() {
        List<CouncilPosition>  councilPositions = councilPositionRepository.findAll();
        List<CouncilPositionDTO> result = councilPositions.stream()
                .map(this::toCouncilPositionDTO).collect(Collectors.toList());
        return result;
    }

    @Override
    public List<CouncilPositionDTO> getCouncilPositionByUserId(Integer id) {
        Optional<User> user = userRepository.findById(id);
        List<CouncilPosition> cp = councilPositionRepository.getByUserId(user.get());
        List<CouncilPositionDTO> result = cp.stream()
                .map(this::toCouncilPositionDTO).collect(Collectors.toList());
        return result;
    }

    // FIXME not done, need further action here
    @Override
    public List<CouncilPositionDTO> getCouncilPositionByCouncilId(Integer id) {
        Optional<Council> council = councilRepository.findById(id);
        List<CouncilPosition> cp = councilPositionRepository.findByCouncilId(council.get());
        List<CouncilPositionDTO> result = cp.stream()
                .map(this::toCouncilPositionDTO).collect(Collectors.toList());
        return result;
    }

    @Override
    public CouncilPositionDTO createdCouncilPosition(CouncilPositionCreationDTO councilPositionCreationDTO) {
        if (Objects.isNull(councilPositionCreationDTO.getCouncilId())
            || Objects.isNull(councilPositionCreationDTO.getPositionId())
            || Objects.isNull(councilPositionCreationDTO.getUserId())) {
            throw new BadRequestException(
                "Cannot create councilPosition because missing data, current request object: " + councilPositionCreationDTO);
        }
        Council council = this.councilCoreService.getCouncilByID(councilPositionCreationDTO.getCouncilId());
        Position position = this.positionService.getPositionByID(councilPositionCreationDTO.getPositionId());
        User user = userService.getUserByID(councilPositionCreationDTO.getUserId());

        Long b = this.councilPositionRepository.countPresidentByCouncilId(council);
        if (b > 1) {
            throw new BadRequestException(
                    "Cannot create councilPosition because This President location already exists in Council");
        }

        Long c = this.councilPositionRepository.countCriticalByCouncilId(council);
        if (c > 1) {
            throw new BadRequestException(
                    "Cannot create councilPosition because This Critical location already exists in Council");
        }

        Long d = this.councilPositionRepository.countSecretaryByCouncilId(council);
        if (d > 1) {
            throw new BadRequestException(
                    "Cannot create councilPosition because This Secretary location already exists in Council");
        }

        Long e = this.councilPositionRepository.countMemberByCouncilId(council);
        if (e > 2) {
            throw new BadRequestException(
                    "Cannot create councilPosition because This is enough 2 members");
        }

        Long a = this.councilPositionRepository.countByCouncilId(council);
        if (a > 4) {
            throw new BadRequestException(
                    "Cannot create councilPosition because there are enough");
        }

        CouncilPosition councilPosition = CouncilPosition.builder()
            .councilId(council)
            .positionId(position)
            .userId(user)
            .build();
        CouncilPosition saved = this.councilPositionRepository.save(councilPosition);
        return this.toCouncilPositionDTO(saved);
    }

    @Override
    public CouncilPositionDTO updatedCouncilPosition(Integer id, CouncilPositionUpdatingDTO councilPositionUpdatingDTO) {
        Optional<CouncilPosition> councilPositionOpt = this.councilPositionRepository.findById(id);
        if (councilPositionOpt.isPresent()) {
            if (Objects.isNull(councilPositionUpdatingDTO.getPositionId())
                || Objects.isNull(councilPositionUpdatingDTO.getUserId())) {
                throw new BadRequestException("Cannot update councilPosition because missing data, request object: "
                    + councilPositionUpdatingDTO);
            }

            CouncilPosition councilPosition= councilPositionOpt.get();
            Position position = this.positionService.getPositionByID(councilPositionUpdatingDTO.getPositionId());
            User user = this.userService.getUserByID(councilPositionUpdatingDTO.getUserId());
            councilPosition.setPositionId(position);
            councilPosition.setUserId(user);
            CouncilPosition saved = councilPositionRepository.save(councilPosition);
            return this.toCouncilPositionDTO(saved);
        }
        throw new BadRequestException("Cannot find CouncilPosition for update action with id: " + id);
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

    @Override
    public Long countCouncilPositionByCouncilId(Integer id) {
        Optional<Council> council = councilRepository.findById(id);
        Long result = this.councilPositionRepository.countByCouncilId(council.get());
        return result;
    }

    @Override
    public Long countPresidentByCouncilId(Integer id) {
        Optional<Council> council = councilRepository.findById(id);
        Long result = this.councilPositionRepository.countPresidentByCouncilId(council.get());
        return result;
    }

    @Override
    public Long countSecretaryByCouncilId(Integer id) {
        Optional<Council> council = councilRepository.findById(id);
        Long result = this.councilPositionRepository.countSecretaryByCouncilId(council.get());
        return result;
    }

    @Override
    public Long countCriticalByCouncilId(Integer id) {
        Optional<Council> council = councilRepository.findById(id);
        Long result = this.councilPositionRepository.countCriticalByCouncilId(council.get());
        return result;
    }

    @Override
    public Long countMemberByCouncilId(Integer id) {
        Optional<Council> council = councilRepository.findById(id);
        Long result = this.councilPositionRepository.countMemberByCouncilId(council.get());
        return result;
    }

    @Override
    public void deleteCouncilPosition(Integer id) {
        Optional<CouncilPosition> councilPositionOpt = this.councilPositionRepository.findById(id);
        if (councilPositionOpt.isPresent()) {
            CouncilPosition deleted = councilPositionOpt.get();
            this.councilPositionRepository.delete(deleted);
            return;
        }
        throw new ResourceNotFoundException("Cannot find any Council Position for deleting action with id: " + id);
    }


    private CouncilPositionDTO toCouncilPositionDTO(CouncilPosition councilPosition) {
        CouncilPositionDTO positionRequestDTO = CouncilPositionDTO.builder()
                .id(councilPosition.getId())
                .userId(councilPosition.getUserId().getId())
                .positionId(councilPosition.getPositionId().getId())
                .councilId(councilPosition.getCouncilId().getId())
                .build();
        return positionRequestDTO;
    }
}
