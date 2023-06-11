package com.example.flow.config.exception;

import com.example.flow.extension.entity.BlockedExtension;
import com.example.flow.extension.exception.ExtensionException;
import com.example.flow.extension.service.ExtensionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;

@ControllerAdvice
@RequiredArgsConstructor
public class GlobalExceptionHandler {

    private final ExtensionService extensionService;

    @ExceptionHandler(ExtensionException.class)
    protected String handleGlobalException(ExtensionException e, Model model) {
        List<BlockedExtension> extensionList = extensionService.getExtensionList();
        model.addAttribute("ExtensionList", extensionList);
        model.addAttribute("ExtensionCount", extensionList.size());
        model.addAttribute("DefaultExtensionList", extensionService.getDefaultExtensionList());
        model.addAttribute("message", e.getErrorCode().getDetail());
        return "index";
    }
}
