package org.springframework.social.kakao.api;

public interface PushOperation {
    String register(String uuid, String pushType, String pushToken, String deviceId);

    String getTokens(String content);

    String deregister(String content, String url);

    void send(String storyId);
}
