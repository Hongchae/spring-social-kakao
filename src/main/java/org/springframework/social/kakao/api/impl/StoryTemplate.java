package org.springframework.social.kakao.api.impl;


import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.social.kakao.api.KakaoApi;
import org.springframework.social.kakao.api.KakaoProfile;
import org.springframework.social.kakao.api.StoryOperations;

public class StoryTemplate extends AbstractKakaoOperations implements StoryOperations {

    private final KakaoApi kakaoApi;

    public StoryTemplate(KakaoApi kakaoApi, boolean isAuthorizedForUser) {
        super(isAuthorizedForUser);
        this.kakaoApi = kakaoApi;
    }

    @Override
    public boolean isStoryUser() {
        requireAuthorization();
        return kakaoApi.fetchIsStoryUser().get("isStoryUser").asBoolean();
    }

    @Override
    public KakaoProfile getProfile() {
        requireAuthorization();
        JsonNode profileNode = kakaoApi.fetchStoryProfile();

        final KakaoProfile profile = new KakaoProfile();
        profile.setUsername(profileNode.get("nickName").asText());
        profile.setProfileUrl(profileNode.get("permalink").asText());
        profile.setImageUrl(profileNode.get("profileImageURL").asText());

        return profile;
    }

    @Override
    public String getLinkInfo(String url) {
        requireAuthorization();
        return kakaoApi.fetchLinkInfo(url).asText();
    }

    @Override
    public String postNote(String content) {
        requireAuthorization();
        return kakaoApi.postNote(content).get("id").asText();
    }

    @Override
    public String postLink(String content, String url) {
        requireAuthorization();
        String linkInfo = getLinkInfo(url);
        return kakaoApi.postLink(content, linkInfo).get("id").asText();
    }

    @Override
    public void delete(String storyId) {
        requireAuthorization();
        kakaoApi.deleteStory(storyId);
    }
}
