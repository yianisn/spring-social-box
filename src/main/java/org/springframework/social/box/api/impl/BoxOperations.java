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

import java.util.List;

import org.springframework.core.io.Resource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.social.support.URIBuilder;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 *
 * @author Ioannis Nikolaou
 */
public class BoxOperations {
    public static final String BOX_PROVIDER_NAME = "box";
    static final String BOX_API_URL = "https://api.box.com/2.0/";
    static final String BOX_FILE_UPLOAD_API_URL = "https://upload.box.com/api/2.0/files/content";

    protected final RestTemplate restTemplate;

    protected BoxOperations(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    protected <T, E extends Enum<E>> T boxOperation(HttpMethod httpMethod, String operation) {
        return boxOperation(httpMethod, operation, null, null, null);
    }

    protected <T, E extends Enum<E>> T boxOperation(HttpMethod httpMethod, String operation, List<E> fields, Class<T> domainClass) {
        return boxOperation(httpMethod, operation, fields, null, domainClass);
    }

    protected <T, E extends Enum<E>> T boxOperation(HttpMethod httpMethod, String operation, List<E> fields, String body, Class<T> domainClass) {
        URIBuilder uriBuilder = URIBuilder.fromUri(BOX_API_URL + operation);
        appendFieldsParameter(fields, uriBuilder);

        switch (httpMethod) {
            case GET:
                return restTemplate.getForObject(uriBuilder.build(), domainClass);
            case POST:
                return restTemplate.postForObject(uriBuilder.build(), body, domainClass);
            case PUT:
                return restTemplate.exchange(uriBuilder.build(), HttpMethod.PUT, new HttpEntity<String>(body), domainClass).getBody();
            case DELETE:
                restTemplate.delete(uriBuilder.build());
                return null;
            default:
                throw new UnsupportedOperationException("This http method is not supported by spring-social-box");
        }
    }

    protected <T, E extends Enum<E>> T boxFileUploadOperation(String attributes, Resource file, List<E> fields, Class<T> domainClass) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.MULTIPART_FORM_DATA);

        MultiValueMap<String,Object> form = new LinkedMultiValueMap<String,Object>();
        form.add("attributes", attributes);
        form.add("file", file);
        HttpEntity<MultiValueMap<String,Object>> httpEntity = new HttpEntity<MultiValueMap<String,Object>>(form, httpHeaders);

        URIBuilder uriBuilder = URIBuilder.fromUri(BOX_FILE_UPLOAD_API_URL);
        appendFieldsParameter(fields, uriBuilder);

        return restTemplate.postForObject(uriBuilder.build(), httpEntity, domainClass);
    }

    private <E extends Enum<E>> void appendFieldsParameter(List<E> fields, URIBuilder uriBuilder) {
        if (fields != null && !fields.isEmpty()) {
            StringBuilder fieldsCSV = new StringBuilder();
            for (E e: fields) {
                fieldsCSV.append(e.toString().toLowerCase()).append(",");
            }
            fieldsCSV.setLength(fieldsCSV.length()-1);
            uriBuilder.queryParam("fields", fieldsCSV.toString());
        }
    }

    protected class BoxParentItem {
        @JsonProperty("id")
        String id;

        public BoxParentItem(String id) {
            this.id = id;
        }
    }

    protected class BoxNewItem {
        @JsonProperty("name")
        String name;
        @JsonProperty("parent")
        BoxParentItem parent;

        public BoxNewItem(String name, BoxParentItem parent) {
            this.name = name;
            this.parent = parent;
        }
    }
}
