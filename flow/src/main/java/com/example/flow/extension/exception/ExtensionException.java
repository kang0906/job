package com.example.flow.extension.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ExtensionException extends RuntimeException{
    private final ExtensionErrorCode errorCode;
}
