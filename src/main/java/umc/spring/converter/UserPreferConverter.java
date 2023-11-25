package umc.spring.converter;

import umc.spring.domain.Category;
import umc.spring.domain.mapping.UserPrefer;

import java.util.List;
import java.util.stream.Collectors;

public class UserPreferConverter {

    //음식 카테고리를 순회하며 UserPrefer 테이블에 인스턴스 생성
    public static List<UserPrefer> toUserPreferList(List<Category> foodCategoryList){

        return foodCategoryList.stream()
                .map(foodCategory ->   //
                        UserPrefer.builder()
                                .category(foodCategory)
                                .build()
                ).collect(Collectors.toList());
    }
}
