package umc.spring.service.userService;

import umc.spring.domain.User;
import umc.spring.web.dto.UserRequestDTO;

public interface UserCommandService {

    public User joinUser(UserRequestDTO.JoinDto request);
}
