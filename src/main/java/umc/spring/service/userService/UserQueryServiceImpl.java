package umc.spring.service.userService;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import umc.spring.domain.Mission;
import umc.spring.domain.Review;
import umc.spring.domain.Store;
import umc.spring.domain.User;
import umc.spring.domain.mapping.UserMission;
import umc.spring.repository.MissionRepository;
import umc.spring.repository.ReviewRepository;
import umc.spring.repository.UserMissionRepository;
import umc.spring.repository.UserRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class UserQueryServiceImpl implements UserQueryService {
    private final UserRepository userRepository;
    private final ReviewRepository reviewRepository;
    private final UserMissionRepository userMissionRepository;
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

    @Override
    public Page<Review> getReviewList(Long userId, int page) {
        User user = userRepository.findById(userId).get();
        Page<Review> allByStore = reviewRepository.findAllByUser(user, PageRequest.of(page, 10));
        return allByStore;
    }

    @Override
    public Page<Mission> getMissionList(Long userId, int page) {
        User user = userRepository.findById(userId).get();
        Page<UserMission> userMissions = userMissionRepository.findByUser(user, PageRequest.of(page, 10));
        Page<Mission> missions = userMissions.map(UserMission::getMission);
        return missions;
    }
}
