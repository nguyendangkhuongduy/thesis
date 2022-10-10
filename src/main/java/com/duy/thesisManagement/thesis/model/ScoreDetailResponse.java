package com.duy.thesisManagement.thesis.model;


import com.duy.thesisManagement.thesis.dto.ScoreDetailDTO;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ScoreDetailResponse {
    List<ScoreDetailDTO> scoreDetails;
}
