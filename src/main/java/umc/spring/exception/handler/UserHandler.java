package umc.spring.exception.handler;

import umc.spring.apiPayload.code.errorStatus.BaseErrorCode;
import umc.spring.exception.GeneralException;

public class UserHandler extends GeneralException {
    public UserHandler(BaseErrorCode code) {
        super(code);
    }
}
