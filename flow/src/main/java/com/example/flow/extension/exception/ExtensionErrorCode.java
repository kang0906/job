package com.example.flow.extension.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ExtensionErrorCode {
    NO_SUCH_DEFAULT_EXTENSION(HttpStatus.NOT_FOUND.value(), "No Such Default Extension", "존재하지 않는 기본파일확장자입니다."),
    NO_SUCH_EXTENSION(HttpStatus.NOT_FOUND.value(), "No Such Extension", "존재하지 않는 파일확장자입니다."),
    EXTENSION_LENGTH_ERROR(HttpStatus.BAD_REQUEST.value(), "Extension Length can't be longer then 20", "파일확장자는 20자를 넘을 수 없습니다."),
    EXTENSION_UNKNOWN(HttpStatus.BAD_REQUEST.value(), "Extension can only contain alphabet, number", "파일확장자는 알파벳, 숫자만 포함할 수 있습니다."),
    TOO_MUCH_EXTENSION(HttpStatus.BAD_REQUEST.value(), "cannot save more then 200 extension", "파일확장자는 200개를 초과하여 저장할 수 없습니다."),
    DUPLICATED_EXTENSION(HttpStatus.BAD_REQUEST.value(), "Extension already in database ", "이미 저장된 파일확장자 입니다."),
    ERROR(HttpStatus.BAD_REQUEST.value(), "error message", "에러 메세지");

    private final Integer httpStatus;
    private final String message;
    private final String detail;
}
