package org.springframework.social.kakao.api.impl;


import org.springframework.social.kakao.api.KakaoApi;
import org.springframework.social.kakao.api.KakaoProfile;
import org.springframework.social.kakao.api.UserOperations;

import java.util.Map;

public class UserTemplate extends AbstractKakaoOperations implements UserOperations {

    private final KakaoApi kakaoApi;

    public UserTemplate(KakaoApi kakaoApi, boolean isAuthorizedForUser) {
        super(isAuthorizedForUser);
        this.kakaoApi = kakaoApi;
    }

    @Override
    public Integer getAccountId() {
        return kakaoApi.fetchAccountId().get("id");
    }

    @Override
    public boolean isStoryUser() {
        requireAuthorization();
        return kakaoApi.fetchIsStoryUser().get("isStoryUser");
    }

    @Override
    public KakaoProfile getStoryUserProfile() {
        requireAuthorization();
        Map<String, String> profileMap = kakaoApi.fetchStoryProfile();

        final KakaoProfile profile = new KakaoProfile();
        profile.setUsername(profileMap.get("nickName"));
        profile.setProfileUrl(profileMap.get("permalink"));
        profile.setImageUrl(profileMap.get("profileImageURL"));

        return profile;
    }

    @Override
    public KakaoProfile getTalkUserProfile() {
        requireAuthorization();
        Map<String, String> profileMap = kakaoApi.fetchTalkProfile();

        final KakaoProfile profile = new KakaoProfile();
        profile.setUsername(profileMap.get("nickName"));
        profile.setImageUrl(profileMap.get("profileImageURL"));
        return null;
    }
}
