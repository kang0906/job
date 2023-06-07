package com.example.econrich.kodate.controller;


import com.example.econrich.kodate.dto.AirDto;
import com.example.econrich.kodate.service.KoDateService;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class KoDateController {

    private final KoDateService koDateService;

    @GetMapping("seoul/air")
    public List<AirDto> seoulAir() {
        return koDateService.KoDataCustomApi();
    }
}
