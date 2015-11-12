package org.springframework.social.kakao.api.impl;


import org.springframework.social.kakao.api.KakaoApi;
import org.springframework.social.kakao.api.TalkOperations;

public class TalkTemplate extends AbstractKakaoOperations implements TalkOperations {

    private final KakaoApi kakaoApi;

    public TalkTemplate(KakaoApi kakaoApi, boolean isAuthorizedForUser) {
        super(isAuthorizedForUser);
        this.kakaoApi = kakaoApi;
    }
}
