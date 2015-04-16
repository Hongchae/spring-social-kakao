package org.springframework.social.kakao.api.impl;

import org.springframework.social.MissingAuthorizationException;

public class AbstractKakaoOperations {
    private final boolean isAuthorized;

    public AbstractKakaoOperations(boolean isAuthorized) {
        this.isAuthorized = isAuthorized;
    }

    protected void requireAuthorization() {
        if (!isAuthorized) {
            throw new MissingAuthorizationException("kakao");
        }
    }
}
