package umc.spring.exception.handler;

import umc.spring.apiPayload.code.errorStatus.BaseErrorCode;
import umc.spring.exception.GeneralException;

public class MissionHandler extends GeneralException {
    public MissionHandler(BaseErrorCode code) {
        super(code);
    }
}