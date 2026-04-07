package com.architect.platform.common.exception;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;


@Getter
@Builder
@AllArgsConstructor
public class ApiErrorResponse {

    private final boolean success;
    private final String message;
    private final String errorCode;
    private final List<String> details;
    private final LocalDateTime timestamp;
}
