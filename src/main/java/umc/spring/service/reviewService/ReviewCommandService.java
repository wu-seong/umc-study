package umc.spring.service.reviewService;

import umc.spring.domain.Review;
import umc.spring.web.dto.StoreRequestDTO;

public interface ReviewCommandService {
    public Review enrollReview(StoreRequestDTO.ReviewDto request);
}
