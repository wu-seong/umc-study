package umc.spring.service.reviewService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import umc.spring.converter.StoreConverter;
import umc.spring.domain.Review;
import umc.spring.repository.ReviewRepository;
import umc.spring.web.dto.StoreRequestDTO;

import javax.transaction.Transactional;


@Service
@RequiredArgsConstructor
@Transactional
public class ReviewCommandServiceImpl implements ReviewCommandService{
    private final ReviewRepository reviewRepository;
    @Override
    @Transactional
    public Review enrollReview(StoreRequestDTO.ReviewDto request) {
        //리뷰 등록
        Review review = StoreConverter.toReview(request);
        // 유저 양방향 매핑
        review.setUser(request.getUser());
        // 식당 양방향 매핑
        review.setStore(request.getStore());

        return reviewRepository.save(review);
    }
}
