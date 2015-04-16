package org.springframework.social.kakao.api.impl;


import org.springframework.social.kakao.api.KakaoApi;
import org.springframework.social.kakao.api.StoryOperations;

public class StoryTemplate extends AbstractKakaoOperations implements StoryOperations {

    private final KakaoApi kakaoApi;

    public StoryTemplate(KakaoApi kakaoApi, boolean isAuthorizedForUser) {
        super(isAuthorizedForUser);
        this.kakaoApi = kakaoApi;
    }

    @Override
    public String getLinkInfo(String url) {
        requireAuthorization();
        return kakaoApi.fetchLinkInfo(url);
    }

    @Override
    public String postStory(String content, String url) {
        requireAuthorization();
        String linkInfo = null;

        if(url != null) {
           linkInfo = getLinkInfo(url);
        }

        if(linkInfo != null) {
            return kakaoApi.postLink(content, linkInfo).get("id");
        } else {
            return kakaoApi.postNote(content).get("id");
        }
    }

    @Override
    public void deleteStory(String storyId) {
        requireAuthorization();
        kakaoApi.deleteStory(storyId);
    }
}
