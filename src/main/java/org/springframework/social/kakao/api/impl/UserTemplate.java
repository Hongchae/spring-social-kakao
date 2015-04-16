package org.springframework.social.kakao.api.impl;


import org.springframework.social.kakao.api.KakaoApi;
import org.springframework.social.kakao.api.UserOperations;

public class UserTemplate extends AbstractKakaoOperations implements UserOperations {

    private final KakaoApi kakaoApi;

    public UserTemplate(KakaoApi kakaoApi, boolean isAuthorizedForUser) {
        super(isAuthorizedForUser);
        this.kakaoApi = kakaoApi;
    }

    @Override
    public Integer getAccountId() {
        return kakaoApi.fetchAccountId().get("id").asInt();
    }
}
