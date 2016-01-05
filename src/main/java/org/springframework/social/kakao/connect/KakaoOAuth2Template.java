package org.springframework.social.kakao.connect;

import org.springframework.social.oauth2.OAuth2Template;

public class KakaoOAuth2Template extends OAuth2Template {
    public KakaoOAuth2Template(String clientId) {
        super(clientId, "", "https://kauth.kakao.com/oauth/authorize", "https://kauth.kakao.com/oauth/token");
        setUseParametersForClientAuthentication(true);
    }
}