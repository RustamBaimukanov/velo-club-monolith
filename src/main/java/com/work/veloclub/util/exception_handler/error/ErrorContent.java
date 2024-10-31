package com.work.veloclub.util.exception_handler.error;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class ErrorContent {

    private final String fieldName;
    private final String message;

}
