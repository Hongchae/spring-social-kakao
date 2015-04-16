package org.springframework.social.kakao.api;

import java.util.Map;

public interface KakaoApi {
    String KAKAO_API_URL = "https://kapi.kakao.com/v1/";

    Map<String, Integer> fetchAccountId();

    Map<String, Boolean> fetchIsStoryUser();

    Map<String, String> fetchStoryProfile();

    Map<String, String> fetchTalkProfile();

    String fetchLinkInfo(String url);

    Map<String, String> postNote(String content);

    Map<String, String> postLink(String content, String url);

    void deleteStory(String id);
}
