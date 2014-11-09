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

import org.springframework.http.HttpMethod;
import org.springframework.social.support.URIBuilder;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author Ioannis Nikolaou
 */
public class BoxOperations {
    public static final String BOX_PROVIDER_NAME = "box";
    static final String BOX_API_URL = "https://api.box.com/2.0/";

    protected final RestTemplate restTemplate;

    protected BoxOperations(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    protected <T, E extends Enum<E>> T boxOperation(HttpMethod httpMethod, String operation, List<E> fields, Class<T> domainClass) {
        URIBuilder uri = URIBuilder.fromUri(BOX_API_URL + operation);
        if (fields != null && !fields.isEmpty()) {
            StringBuilder fieldsCSV = new StringBuilder();
            for (E e: fields) {
                fieldsCSV.append(e.toString().toLowerCase()).append(",");
            }
            fieldsCSV.setLength(fieldsCSV.length()-1);
            uri.queryParam("fields", fieldsCSV.toString());
        }

        switch (httpMethod) {
            case GET:
                return restTemplate.getForObject(uri.build(), domainClass);
            default:
                throw new UnsupportedOperationException("This http method is not supported by spring-social-box");
        }
    }

}
