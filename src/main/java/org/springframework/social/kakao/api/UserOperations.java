package org.springframework.social.kakao.api;

public interface UserOperations {
    Integer getUserId();

    boolean isStoryUser();

    KakaoProfile getStoryUserProfile();

    KakaoProfile getTalkUserProfile();
}
