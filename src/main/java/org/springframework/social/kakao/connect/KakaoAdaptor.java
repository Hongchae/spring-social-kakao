package org.springframework.social.kakao.connect;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.social.ApiException;
import org.springframework.social.connect.ApiAdapter;
import org.springframework.social.connect.ConnectionValues;
import org.springframework.social.connect.UserProfile;
import org.springframework.social.connect.UserProfileBuilder;
import org.springframework.social.kakao.api.Kakao;
import org.springframework.social.kakao.api.KakaoProfile;

public class KakaoAdaptor implements ApiAdapter<Kakao> {

    @Override
    public boolean test(Kakao kakao) {
        try {
            kakao.userOperations().isStoryUser();
            return true;
        } catch (ApiException e) {
            return false;
        }
    }

    @Override
    public void setConnectionValues(Kakao kakao, ConnectionValues values) {
        KakaoProfile profile = fetchPrimaryProfile(kakao);
        if (profile != null) {
            values.setProviderUserId(profile.getId());
            values.setDisplayName(profile.getUsername());
            values.setProfileUrl(profile.getProfileUrl());
            values.setImageUrl(profile.getImageUrl());
        }
    }

    @Override
    public UserProfile fetchUserProfile(Kakao kakao) {
        UserProfileBuilder profileBuilder = new UserProfileBuilder();

        KakaoProfile profile = fetchPrimaryProfile(kakao);
        if (profile != null) {
            profileBuilder.setUsername(profile.getUsername());
        }

        return profileBuilder.build();

    }

    private KakaoProfile fetchPrimaryProfile(Kakao kakao) {
        KakaoProfile profile = null;

        try {
            profile = kakao.userOperations().getTalkUserProfile();
        } catch (Exception ignore) {}

        if (profile == null) {
            if (kakao.userOperations().isStoryUser()) {
                profile = kakao.userOperations().getStoryUserProfile();
            } else {
                profile = new KakaoProfile();
                profile.setUsername("Kakao-" + RandomStringUtils.randomAlphanumeric(5));
            }
        }

        if (profile != null) {
            profile.setId(Integer.toString(kakao.userOperations().getUserId()));
        }

        return profile;
    }

    @Override
    public void updateStatus(Kakao kakao, String message) {
        kakao.storyOperations().postStory(message, null);
    }
}
