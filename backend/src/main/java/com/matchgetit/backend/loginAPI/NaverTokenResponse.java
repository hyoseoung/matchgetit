package com.matchgetit.backend.loginAPI;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NaverTokenResponse {
    private String accessToken;
    private String tokenType;
    private int expiresIn;
}
