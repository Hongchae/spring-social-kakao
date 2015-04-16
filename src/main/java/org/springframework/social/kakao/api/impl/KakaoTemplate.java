package org.springframework.social.kakao.api.impl;

import org.springframework.social.kakao.api.Kakao;
import org.springframework.social.kakao.api.StoryOperations;
import org.springframework.social.kakao.api.UserOperations;
import org.springframework.social.oauth2.AbstractOAuth2ApiBinding;
import org.springframework.social.support.ClientHttpRequestFactorySelector;
import org.springframework.social.support.URIBuilder;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.net.URI;
import java.util.Collections;
import java.util.Map;

public class KakaoTemplate extends AbstractOAuth2ApiBinding implements Kakao {

    private StoryOperations storyOperations;

    private UserOperations userOperations;

    public KakaoTemplate(String accessToken) {
        super(accessToken);
        initialize();
    }

    @Override
    public UserOperations userOperations() {
        return userOperations;
    }

    @Override
    public StoryOperations storyOperations() {
        return storyOperations;
    }

    public <T> T get(URI uri, Class<T> resultType) {
        return getRestTemplate().getForObject(uri, resultType);
    }

    public <T> T post(URI uri, MultiValueMap<String, String> data, Class<T> resultType) {
        return getRestTemplate().postForObject(uri, new LinkedMultiValueMap<String, String>(data), resultType);
    }

    public void delete(URI uri) {
        getRestTemplate().delete(uri);
    }

    @Override
    public Map<String, Integer> fetchAccountId() {
        return get(URIBuilder.fromUri(KAKAO_API_URL + "user/me").build(), Map.class);
    }

    @Override
    @SuppressWarnings("unchecked")
    public Map<String, Boolean> fetchIsStoryUser() {
        return get(URIBuilder.fromUri(KAKAO_API_URL + "api/story/isstoryuser").build(), Map.class);
    }

    @Override
    @SuppressWarnings("unchecked")
    public Map<String, String> fetchStoryProfile() {
        return get(URIBuilder.fromUri(KAKAO_API_URL + "api/story/profile").build(), Map.class);
    }

    @Override
    @SuppressWarnings("unchecked")
    public Map<String, String> fetchTalkProfile() {
        return get(URIBuilder.fromUri(KAKAO_API_URL + "api/talk/profile").build(), Map.class);
    }

    @Override
    public String fetchLinkInfo(String url) {
        return get(URIBuilder.fromUri(KAKAO_API_URL + "api/story/linkinfo?url=" + url).build(), String.class);
    }

    @Override
    @SuppressWarnings("unchecked")
    public Map<String, String> postNote(String content) {
        LinkedMultiValueMap<String, String> parameterMap = new LinkedMultiValueMap<String, String>();
        parameterMap.put("content", Collections.singletonList(content));

        return post(URIBuilder.fromUri(KAKAO_API_URL + "api/story/post/note").build(), parameterMap, Map.class);
    }

    @Override
    @SuppressWarnings("unchecked")
    public Map<String, String> postLink(String content, String linkInfo) {
        LinkedMultiValueMap<String, String> parameterMap = new LinkedMultiValueMap<String, String>();
        parameterMap.put("content", Collections.singletonList(content));
        parameterMap.put("link_info", Collections.singletonList(linkInfo));

        return post(URIBuilder.fromUri(KAKAO_API_URL + "api/story/post/link").build(), parameterMap, Map.class);
    }

    @Override
    public void deleteStory(String id) {
        delete(URIBuilder.fromUri(KAKAO_API_URL + "api/story/delete/mystory?id=" + id).build());
    }

    private void initialize() {
        super.setRequestFactory(ClientHttpRequestFactorySelector.bufferRequests(getRestTemplate().getRequestFactory()));
        initSubApis();
    }

    private void initSubApis() {
        userOperations = new UserTemplate(this, isAuthorized());
        storyOperations = new StoryTemplate(this, isAuthorized());
    }
}
