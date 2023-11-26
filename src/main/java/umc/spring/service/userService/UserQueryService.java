package umc.spring.service.userService;

import umc.spring.domain.User;

import java.util.List;

public interface UserQueryService {

    public User findUser(Long userId);

    public boolean isExist(List<Long> values);
}
