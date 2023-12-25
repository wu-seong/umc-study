package umc.spring.service.storeService;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import umc.spring.apiPayload.code.errorStatus.ErrorStatus;
import umc.spring.domain.Mission;
import umc.spring.domain.Review;
import umc.spring.domain.Store;
import umc.spring.exception.handler.StoreHandler;
import umc.spring.repository.MissionRepository;
import umc.spring.repository.ReviewRepository;
import umc.spring.repository.StoreRepository;
import umc.spring.validation.annotation.ExistStore;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StoreQueryServiceImpl implements StoreQueryService{
    private final StoreRepository storeRepository;
    private final ReviewRepository reviewRepository;
    private final MissionRepository missionRepository;
    @Override
    public Store findStore(Long storeId) {
        Optional<Store> store = storeRepository.findById(storeId);
        if(store.isEmpty()){
            return null;
        }
        return store.get();
    }

    @Override
    public Page<Review> getReviewList(Long storeId, Integer page) {
        Store store = storeRepository.findById(storeId).orElseThrow(() -> new StoreHandler(ErrorStatus.STORE_NOT_FOUND));
        Page<Review> allByStore = reviewRepository.findAllByStore(store, PageRequest.of(page, 10));

        return allByStore;
    }

    @Override
    public Page<Mission> getMissionList(Long storeId, Integer page) {
        Store store = storeRepository.findById(storeId).orElseThrow(() -> new StoreHandler(ErrorStatus.STORE_NOT_FOUND));
        Page<Mission> allByStore = missionRepository.findAllByStore(store, PageRequest.of(page, 10));

        return allByStore;
    }
}
