package umc.spring.service.storeService;

import umc.spring.domain.Store;
import umc.spring.domain.User;
import umc.spring.validation.annotation.ExistStore;

import java.util.List;

public interface StoreQueryService {
    public Store findStore(Long storeId);
}
