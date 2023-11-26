package umc.spring.exception.handler;

import umc.spring.apiPayload.code.errorStatus.BaseErrorCode;
import umc.spring.exception.GeneralException;

public class StoreHandler extends GeneralException {
    public StoreHandler(BaseErrorCode code) {
        super(code);
    }
}
