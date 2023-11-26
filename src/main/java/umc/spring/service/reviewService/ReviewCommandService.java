package umc.spring.service.reviewService;

import umc.spring.domain.Review;
import umc.spring.web.dto.ReviewRequestDTO;

public interface ReviewCommandService {
    public Review enrollReview(ReviewRequestDTO.enrollDto request);
}
