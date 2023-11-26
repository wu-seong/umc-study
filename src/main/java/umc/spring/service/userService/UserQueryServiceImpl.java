package umc.spring.service.userService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import umc.spring.domain.User;
import umc.spring.repository.UserRepository;

import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class UserQueryServiceImpl implements UserQueryService {
    private final UserRepository userRepository;
    @Override
    public User findUser(Long userId) {
        Optional<User> user = userRepository.findById(userId);
        if(user.isEmpty()){
            return null;
        }
        return user.get();
    }

    @Override
    public boolean isExist(List<Long> values){
        return values.stream()
                .allMatch(value -> userRepository.existsById(value));
    }
}
