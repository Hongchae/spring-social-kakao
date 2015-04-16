package org.springframework.social.kakao.api;

import org.springframework.social.ApiBinding;

public interface Kakao extends KakaoApi, ApiBinding {
    UserOperations userOperations();
    StoryOperations storyOperations();
    TalkOperations talkOperations();
}
