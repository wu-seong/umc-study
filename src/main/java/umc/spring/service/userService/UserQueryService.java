package umc.spring.service.userService;

import org.springframework.data.domain.Page;
import umc.spring.domain.Mission;
import umc.spring.domain.Review;
import umc.spring.domain.User;

import java.util.List;

public interface UserQueryService {

    public User findUser(Long userId);

    public boolean isExist(List<Long> values);

    Page<Review> getReviewList(Long userId, int page);

    Page<Mission> getMissionList(Long userId, int page);
}
