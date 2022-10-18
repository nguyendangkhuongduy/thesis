package com.duy.thesisManagement.thesis.model;

import com.duy.thesisManagement.thesis.dto.ScoreDetailDTO;
import com.duy.thesisManagement.thesis.dto.ScoreDetailHasUserNameDTO;
import lombok.Getter;
import lombok.Setter;

import java.util.List;


@Getter
@Setter
public class ScoreDetailHasUserNameResponse {
    List<ScoreDetailHasUserNameDTO> scoreDetails;
}
