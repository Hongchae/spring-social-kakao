package org.springframework.social.kakao.api;

public interface StoryOperations {
    String getLinkInfo(String url);

    String postStory(String content, String url);

    void deleteStory(String storyId);
}
