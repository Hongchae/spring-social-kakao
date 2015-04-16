package org.springframework.social.kakao.api.impl;


import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.social.kakao.api.KakaoApi;
import org.springframework.social.kakao.api.KakaoProfile;
import org.springframework.social.kakao.api.TalkOperations;

public class TalkTemplate extends AbstractKakaoOperations implements TalkOperations {

    private final KakaoApi kakaoApi;

    public TalkTemplate(KakaoApi kakaoApi, boolean isAuthorizedForUser) {
        super(isAuthorizedForUser);
        this.kakaoApi = kakaoApi;
    }

    @Override
    public KakaoProfile getProfile() {
        requireAuthorization();
        JsonNode profileNode = kakaoApi.fetchStoryProfile();

        final KakaoProfile profile = new KakaoProfile();
        profile.setUsername(profileNode.get("nickName").asText());
        profile.setImageUrl(profileNode.get("profileImageURL").asText());

        return profile;
    }
}
