package org.springframework.social.kakao.connect;

import org.springframework.social.kakao.api.Kakao;
import org.springframework.social.kakao.api.impl.KakaoTemplate;
import org.springframework.social.oauth2.AbstractOAuth2ServiceProvider;

public class KakaoServiceProvider extends AbstractOAuth2ServiceProvider<Kakao> {
    private String appNamespace;

    public KakaoServiceProvider(String apiKey, String appNamespace) {
        super(new KakaoOAuth2Template(apiKey));
        this.appNamespace = appNamespace;
    }

    public Kakao getApi(String accessToken) {
        return new KakaoTemplate(accessToken);
    }
}
