package org.springframework.social.kakao.api;

import com.fasterxml.jackson.databind.JsonNode;

public interface KakaoApi {
    String KAKAO_API_URL = "https://kapi.kakao.com/v1/";

    JsonNode fetchUserId();

    JsonNode fetchIsStoryUser();

    JsonNode fetchStoryProfile();

    JsonNode fetchTalkProfile();

    JsonNode fetchLinkInfo(String url);

    JsonNode postNote(String content);

    JsonNode postLink(String content, String url);

    void deleteStory(String id);
}
