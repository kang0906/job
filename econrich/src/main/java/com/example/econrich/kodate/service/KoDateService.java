package com.example.econrich.kodate.service;

import com.example.econrich.kodate.dto.AirDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class KoDateService {

    private final MessageSource messageSource;

    public List<AirDto> KoDataCustomApi() {

        String timestamp = Long.toString(System.currentTimeMillis());
        String url = messageSource.getMessage("api.url", null, null) +
                "?serviceKey=" + messageSource.getMessage("api.key.encoding", null, null) + "&returnType=JSON";
        String params = "&numOfRows=" + 100 + "&pageNo=" + 1 + "&sidoName=%EC%84%9C%EC%9A%B8";
        URI uri = null;
        try {
            uri = new URI(url + params);
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }

        // HTTP Header 생성
        HttpHeaders headers = new HttpHeaders();

        log.info("url : {}", url+params);

        // HTTP 요청 보내기
        HttpEntity<String> request =new HttpEntity<>(null,headers);
        System.out.println("Request = " + request);
        RestTemplate rt = new RestTemplate();
//        ResponseEntity<String> response = rt.exchange(
//                url+params,
//                HttpMethod.GET,
//                request,
//                String.class
//        );
        ResponseEntity<String> response = rt.exchange(
                uri,
                HttpMethod.GET,
                request,
                String.class
        );

        // HTTP 응답 (JSON)
        String responseBody = response.getBody();

        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode;

        try {
            jsonNode = objectMapper.readTree(responseBody);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        JsonNode jsonList = jsonNode.get("response").get("body").get("items");
        int count = jsonNode.get("response").get("body").get("totalCount").asInt();

        List<AirDto> list = new ArrayList<>();
        for(int i = 0; i<count; i++){
            list.add(new AirDto(jsonList.get(i).get("stationName").asText(), jsonList.get(i).get("pm10Value").asText()));
        }

        return list;

    }
}
