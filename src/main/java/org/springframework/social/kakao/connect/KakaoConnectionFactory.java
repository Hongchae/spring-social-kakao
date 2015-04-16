package org.springframework.social.kakao.connect;

import org.springframework.social.connect.support.OAuth2ConnectionFactory;
import org.springframework.social.kakao.api.Kakao;

public class KakaoConnectionFactory extends OAuth2ConnectionFactory<Kakao> {
    public KakaoConnectionFactory(String apiKey) {
        this(apiKey, null);
    }

    public KakaoConnectionFactory(String apiKey, String appNamespace) {
        super("kakao", new KakaoServiceProvider(apiKey, appNamespace), new KakaoAdaptor());
    }
}