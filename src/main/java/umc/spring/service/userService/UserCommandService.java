package umc.spring.service.userService;

import umc.spring.domain.User;
import umc.spring.web.dto.UserRequestDTO;

import java.util.List;

public interface UserCommandService {

    public User joinUser(UserRequestDTO.JoinDto request);

}
