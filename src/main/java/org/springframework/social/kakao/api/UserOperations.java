package org.springframework.social.kakao.api;

public interface UserOperations {
    Integer getAccountId();

    boolean isStoryUser();

    KakaoProfile getStoryUserProfile();

    KakaoProfile getTalkUserProfile();
}
