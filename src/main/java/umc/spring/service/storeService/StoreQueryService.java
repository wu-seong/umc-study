package umc.spring.service.storeService;

import org.springframework.data.domain.Page;
import umc.spring.domain.Mission;
import umc.spring.domain.Review;
import umc.spring.domain.Store;
import umc.spring.domain.User;
import umc.spring.validation.annotation.ExistStore;

import java.util.List;

public interface StoreQueryService {
    public Store findStore(Long storeId);

    Page<Review> getReviewList(Long storeId, Integer page);

    Page<Mission> getMissionList(Long storeId, Integer page);
}
