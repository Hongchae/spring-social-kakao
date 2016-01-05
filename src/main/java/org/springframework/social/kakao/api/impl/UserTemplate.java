package org.springframework.social.kakao.api.impl;

import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.social.kakao.api.KakaoApi;
import org.springframework.social.kakao.api.KakaoProfile;
import org.springframework.social.kakao.api.UserOperations;

public class UserTemplate extends AbstractKakaoOperations implements UserOperations {

    private final KakaoApi kakaoApi;

    public UserTemplate(KakaoApi kakaoApi, boolean isAuthorizedForUser) {
        super(isAuthorizedForUser);
        this.kakaoApi = kakaoApi;
    }

    @Override
    public Integer getUserId() {
        return kakaoApi.fetchUserId().get("id").asInt();
    }

    @Override
    public boolean isStoryUser() {
        requireAuthorization();
        return kakaoApi.fetchIsStoryUser().get("isStoryUser").asBoolean();
    }

    @Override
    public KakaoProfile getStoryUserProfile() {
        requireAuthorization();
        JsonNode profileNode = kakaoApi.fetchStoryProfile();

        final KakaoProfile profile = new KakaoProfile();
        profile.setUsername(profileNode.get("nickName").asText());
        profile.setProfileUrl(profileNode.get("permalink").asText());
        profile.setImageUrl(profileNode.get("profileImageURL").asText());

        return profile;
    }

    @Override
    public KakaoProfile getTalkUserProfile() {
        requireAuthorization();
        JsonNode profileNode = kakaoApi.fetchTalkProfile();

        final KakaoProfile profile = new KakaoProfile();
        profile.setUsername(profileNode.get("nickName").asText());
        profile.setImageUrl(profileNode.get("profileImageURL").asText());

        return profile;
    }
}
