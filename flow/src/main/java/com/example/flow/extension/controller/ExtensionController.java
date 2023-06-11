package com.example.flow.extension.controller;

import com.example.flow.extension.entity.BlockedExtension;
import com.example.flow.extension.exception.ExtensionErrorCode;
import com.example.flow.extension.exception.ExtensionException;
import com.example.flow.extension.service.ExtensionService;
import jakarta.websocket.server.PathParam;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class ExtensionController {
    private final ExtensionService extensionService;

    @GetMapping("/extension/default/{name}")
    public String extension(@PathVariable String name){
        extensionService.defaultExtensionClick(name);
        return "redirect:/";
    }

    @PostMapping("/extension/custom/{name}")
    public String addCustomExtension(@PathVariable String name){
        extensionService.addCustomExtension(name);
        return "redirect:/";
    }

    @DeleteMapping("/extension/custom/{name}")
    public String deleteCustomExtension(@PathVariable String name){
        extensionService.deleteCustomExtension(name);
        return "redirect:/";
    }

    @PostMapping("/extension/default/{name}")
    public String addDefault(@PathVariable String name){
        extensionService.addDefaultExtension(name);
        return "redirect:/";
    }

    @DeleteMapping("/extension/default/{name}")
    public String deleteDefault(@PathVariable String name){
        extensionService.deleteDefaultExtension(name);
        return "redirect:/";
    }

    @GetMapping("/")
    public String landingPage(Model model){
        List<BlockedExtension> extensionList = extensionService.getExtensionList();
        model.addAttribute("ExtensionList", extensionList);
        model.addAttribute("ExtensionCount", extensionList.size());
        model.addAttribute("DefaultExtensionList", extensionService.getDefaultExtensionList());

        return "index";
    }
}
