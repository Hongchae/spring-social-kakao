package org.springframework.social.kakao.connect;

import org.springframework.social.ApiException;
import org.springframework.social.connect.ApiAdapter;
import org.springframework.social.connect.ConnectionValues;
import org.springframework.social.connect.UserProfile;
import org.springframework.social.connect.UserProfileBuilder;
import org.springframework.social.kakao.api.Kakao;
import org.springframework.social.kakao.api.KakaoProfile;

public class KakaoAdaptor implements ApiAdapter<Kakao> {

    public KakaoAdaptor() {}

    @Override
    public boolean test(Kakao kakao) {
        try {
            kakao.userOperations().isStoryUser();
            return true;
        } catch(ApiException e) {
            return false;
        }
    }

    @Override
    public void setConnectionValues(Kakao kakao, ConnectionValues values) {
        KakaoProfile profile = fetchPrimaryProfile(kakao);

        values.setProviderUserId(profile.getId());
        values.setDisplayName(profile.getUsername());
        values.setProfileUrl(profile.getProfileUrl());
        values.setImageUrl(profile.getImageUrl());
    }

    @Override
    public UserProfile fetchUserProfile(Kakao kakao) {
        KakaoProfile profile = fetchPrimaryProfile(kakao);

        return (new UserProfileBuilder()).setUsername(profile.getUsername()).build();
    }

    private KakaoProfile fetchPrimaryProfile(Kakao kakao) {
        KakaoProfile profile;
        if(kakao.userOperations().isStoryUser()) {
            profile = kakao.userOperations().getStoryUserProfile();
        } else {
            profile = kakao.userOperations().getTalkUserProfile();
        }

        Integer accountId = kakao.userOperations().getAccountId();
        profile.setId(Integer.toString(accountId));

        return profile;
    }

    @Override
    public void updateStatus(Kakao kakao, String message) {
        kakao.storyOperations().postStory(message, null);
    }
}
