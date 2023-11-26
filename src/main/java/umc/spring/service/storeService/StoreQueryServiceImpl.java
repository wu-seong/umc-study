package umc.spring.service.storeService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import umc.spring.domain.Store;
import umc.spring.repository.StoreRepository;
import umc.spring.validation.annotation.ExistStore;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StoreQueryServiceImpl implements StoreQueryService{
    private final StoreRepository storeRepository;
    @Override
    public Store findStore(Long storeId) {
        Optional<Store> store = storeRepository.findById(storeId);
        if(store.isEmpty()){
            return null;
        }
        return store.get();
    }
}
