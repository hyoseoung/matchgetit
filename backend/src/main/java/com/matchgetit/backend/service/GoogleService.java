package com.matchgetit.backend.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.matchgetit.backend.loginAPI.GoogleUser;
import com.nimbusds.jose.shaded.gson.Gson;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

@Service
@AllArgsConstructor
public class GoogleService {

    private static final String GOOGLE_TOKEN_URL = "https://oauth2.googleapis.com/token";
    private static final String GOOGLE_USER_INFO_URL = "https://www.googleapis.com/userinfo/v2/me";

    private final WebClient webClient;

    public GoogleUser getGoogleUserInfo(String code) {
        String accessToken = getAccessToken(code);
        System.out.println(accessToken);
        System.out.println(code);
        if (accessToken != null) {
            return requestUserInfo(accessToken);
        }
        return null;
    }

    private String getAccessToken(String code) {
        MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
        body.add("grant_type", "authorization_code");
        body.add("client_id", "710934810063-hlhppmpgo4bl0s9suosn1o7p2jbsamc3.apps.googleusercontent.com");
        body.add("client_secret", "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");
        body.add("redirect_uri", "http://localhost:8081/matchGetIt/google");
        body.add("code", code);

        return webClient.post()
                .uri(GOOGLE_TOKEN_URL)
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .body(BodyInserters.fromFormData(body))
                .retrieve()
                .bodyToMono(String.class)
                .map(responseJson -> {
                    try {
                        ObjectMapper objectMapper = new ObjectMapper();
                        JsonNode rootNode = objectMapper.readTree(responseJson);
                        System.out.println(responseJson.contains("access_token"));
                        return rootNode.path("access_token").asText();
                    } catch (JsonProcessingException e) {
                        e.printStackTrace();
                        return null;
                    }
                })
                .block(); // block to get the result synchronously (not recommended in a reactive environment)
    }

    private GoogleUser requestUserInfo(String accessToken) {
        System.out.println(accessToken);
        String responseJson = webClient.get()
                .uri(GOOGLE_USER_INFO_URL)
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + accessToken)
                .retrieve()
                .bodyToMono(String.class)
                .block();

        Gson gson = new Gson();
        GoogleUser user = gson.fromJson(responseJson, GoogleUser.class);

        return user;
    }
}
