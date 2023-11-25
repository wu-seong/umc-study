package umc.spring.exception.handler;

import umc.spring.apiPayload.code.errorStatus.BaseErrorCode;
import umc.spring.exception.GeneralException;

public class FoodCategoryHandler extends GeneralException {
    public FoodCategoryHandler(BaseErrorCode code) {
        super(code);
    }
}
