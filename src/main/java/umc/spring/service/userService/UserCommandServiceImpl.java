package umc.spring.service.userService;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import umc.spring.apiPayload.code.errorStatus.ErrorStatus;
import umc.spring.converter.UserConverter;
import umc.spring.converter.UserPreferConverter;
import umc.spring.domain.Category;
import umc.spring.domain.User;
import umc.spring.domain.mapping.UserPrefer;
import umc.spring.exception.handler.FoodCategoryHandler;
import umc.spring.repository.FoodCategoryRepository;
import umc.spring.repository.UserRepository;
import umc.spring.web.dto.UserRequestDTO;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional // (readOnly = true) 찾아보니 readOnly 속성이 사라짐
public class UserCommandServiceImpl implements UserCommandService {
    private final UserRepository userRepository;
    private final FoodCategoryRepository foodCategoryRepository;

    @Override
    @Transactional
    public User joinUser(UserRequestDTO.JoinDto request) {
        User newUser = UserConverter.toUser(request);

        //회원가입시 선택한 카테고리 정보를 리스트에 저장
        List<Category> categoryList = request.getPreferCategory().stream()
                .map(category -> {
                    return foodCategoryRepository.findById(category).orElseThrow(() -> new FoodCategoryHandler(ErrorStatus.FOOD_CATEGORY_NOT_FOUND));
                }).collect(Collectors.toList());
        //리스트에 저장된 정보와 유저 정보를 매핑테이블에 추가
        List<UserPrefer> userPreferList = UserPreferConverter.toUserPreferList(categoryList);
        userPreferList.forEach(userPrefer -> {userPrefer.setUser(newUser);});

        return userRepository.save(newUser);
    }
}
