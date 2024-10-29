package com.meli.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class RestApiService {

    private final RestTemplate restTemplate;
    private String baseUrl;

    @Autowired
    public RestApiService(RestTemplate restTemplate, Environment environment) {
        this.restTemplate = restTemplate;
        this.baseUrl = environment.getProperty("mercadolibre_api_root");
    }

    public <T> T get(String path, String entityId, Class<T> responseType) {
        String finalPath = baseUrl + path + '/' + entityId;
        return restTemplate.getForObject(finalPath, responseType);
    }

}

