/*
 * Copyright 2014 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.springframework.social.box.api.impl;

import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.social.box.api.Box;
import org.springframework.social.box.api.FolderOperations;
import org.springframework.social.box.api.UserOperations;
import org.springframework.social.box.domain.BoxModule;
import org.springframework.social.oauth2.AbstractOAuth2ApiBinding;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * This is the central class for interacting with box.
 *
 * @author Ioannis Nikolaou
 */
public class BoxTemplate extends AbstractOAuth2ApiBinding implements Box {

    private ObjectMapper objectMapper;

    private UserOperations userOperations;

    private FolderOperations folderOperations;

    /**
     * Create a new instance of BoxTemplate using a given access token.
     * @param accessToken An access token given by box after a successful OAuth 2 authentication
     */
    public BoxTemplate(String accessToken) {
        super(accessToken);
        initSubApis();
    }

    /* (non-Javadoc)
     * @see org.springframework.social.oauth2.AbstractOAuth2ApiBinding#getJsonMessageConverter()
     */
    @Override
    public UserOperations userOperations() {
        return userOperations;
    }

    /* (non-Javadoc)
     * @see org.springframework.social.box.api.Box#folderOperations()
     */
    @Override
    public FolderOperations folderOperations() {
        return folderOperations;
    }

    /* (non-Javadoc)
     * @see org.springframework.social.oauth2.AbstractOAuth2ApiBinding#getJsonMessageConverter()
     */
    @Override
    protected MappingJackson2HttpMessageConverter getJsonMessageConverter() {
        MappingJackson2HttpMessageConverter converter = super.getJsonMessageConverter();
        objectMapper = new ObjectMapper();
        objectMapper.registerModule(new BoxModule());
        converter.setObjectMapper(objectMapper);
        return converter;
    }

    /* (non-Javadoc)
     * @see org.springframework.social.oauth2.AbstractOAuth2ApiBinding#configureRestTemplate(org.springframework.web.client.RestTemplate)
     */
    @Override
    protected void configureRestTemplate(RestTemplate restTemplate) {
        restTemplate.setErrorHandler(new BoxErrorHandler());
    }

    private void initSubApis() {
        userOperations = new UserTemplate(getRestTemplate());
        folderOperations = new FolderTemplate(getRestTemplate());
    }

}
