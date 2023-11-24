package umc.spring.apiPayload.code.sucessStatus;

import umc.spring.apiPayload.code.responseDTO.ReasonDTO;

public interface BaseCode {

    public ReasonDTO getReason();

    public ReasonDTO getReasonHttpStatus();
}