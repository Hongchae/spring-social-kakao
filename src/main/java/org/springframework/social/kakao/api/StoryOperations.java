package org.springframework.social.kakao.api;

public interface StoryOperations {
    boolean isStoryUser();

    KakaoProfile getProfile();

    String getLinkInfo(String url);

    String postNote(String content);

    String postLink(String content, String url);

    void delete(String storyId);
}
