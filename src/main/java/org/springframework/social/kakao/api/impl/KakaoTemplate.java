package org.springframework.social.kakao.api.impl;

import com.fasterxml.jackson.databind.JsonNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.social.kakao.api.Kakao;
import org.springframework.social.kakao.api.StoryOperations;
import org.springframework.social.kakao.api.UserOperations;
import org.springframework.social.oauth2.AbstractOAuth2ApiBinding;
import org.springframework.social.support.ClientHttpRequestFactorySelector;
import org.springframework.social.support.URIBuilder;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URLEncoder;
import java.util.Collections;

public class KakaoTemplate extends AbstractOAuth2ApiBinding implements Kakao {

    private static final Logger logger = LoggerFactory.getLogger(KakaoTemplate.class);

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
        logger.debug("Kakao template post uri: " + uri.toString());
        return getRestTemplate().postForObject(uri, new LinkedMultiValueMap<>(data), resultType);
    }

    public void delete(URI uri) {
        getRestTemplate().delete(uri);
    }

    @Override
    public JsonNode fetchAccountId() {
        return get(URIBuilder.fromUri(KAKAO_API_URL + "user/me").build(), JsonNode.class);
    }

    @Override
    public JsonNode fetchIsStoryUser() {
        return get(URIBuilder.fromUri(KAKAO_API_URL + "api/story/isstoryuser").build(), JsonNode.class);
    }

    @Override
    public JsonNode fetchStoryProfile() {
        return get(URIBuilder.fromUri(KAKAO_API_URL + "api/story/profile").build(), JsonNode.class);
    }

    @Override
    public JsonNode fetchTalkProfile() {
        return get(URIBuilder.fromUri(KAKAO_API_URL + "api/talk/profile").build(), JsonNode.class);
    }

    @Override
    public JsonNode fetchLinkInfo(String url) {
        try {
            String encodedUrl = URLEncoder.encode(url, "UTF-8");
            return get(URIBuilder.fromUri(KAKAO_API_URL + "api/story/linkinfo?url=" + encodedUrl).build(), JsonNode.class);
        } catch (UnsupportedEncodingException e) {
            return null;
        }

    }

    @Override
    public JsonNode postNote(String content) {
        LinkedMultiValueMap<String, String> parameterMap = new LinkedMultiValueMap<>();
        parameterMap.put("content", Collections.singletonList(content));

        return post(URIBuilder.fromUri(KAKAO_API_URL + "api/story/post/note").build(), parameterMap, JsonNode.class);
    }

    @Override
    public JsonNode postLink(String content, String linkInfo) {
        LinkedMultiValueMap<String, String> parameterMap = new LinkedMultiValueMap<>();
        parameterMap.put("content", Collections.singletonList(content));
        parameterMap.put("link_info", Collections.singletonList(linkInfo));

        return post(URIBuilder.fromUri(KAKAO_API_URL + "api/story/post/link").build(), parameterMap, JsonNode.class);
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
