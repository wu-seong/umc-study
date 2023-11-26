package umc.spring.domain.mapping;


import lombok.*;
import umc.spring.domain.Category;
import umc.spring.domain.User;
import umc.spring.domain.common.BaseEntity;

import javax.persistence.*;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class UserPrefer extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "User_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;

    //기존에 있던 user의 참조키중 자신에 대한 정보를 지우고, 매핑관계를 새로 설정
    public void setUser(User user){
        if(this.user != null)
            user.getUserPreferList().remove(this);
        this.user = user;
        user.getUserPreferList().add(this);
    }
    public void setFoodCategory(Category foodCategory){
        this.category = foodCategory;
    }
}