package com.duy.thesisManagement.thesis.model;

import com.duy.thesisManagement.thesis.dto.UserDTO;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsersResponse {
		List<UserDTO> users;
}
