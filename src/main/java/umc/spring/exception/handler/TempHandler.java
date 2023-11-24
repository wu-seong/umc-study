package umc.spring.exception.handler;

import umc.spring.apiPayload.code.errorStatus.BaseErrorCode;
import umc.spring.exception.GeneralException;

public class TempHandler extends GeneralException {

    public TempHandler(BaseErrorCode errorCode) {
        super(errorCode);
    }
}
