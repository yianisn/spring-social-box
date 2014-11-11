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
import org.springframework.social.box.api.UserOperations;
import org.springframework.social.box.domain.BoxUser;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author Ioannis Nikolaou
 */
public class UserTemplate extends BoxOperations implements UserOperations {

    public UserTemplate(RestTemplate restTemplate) {
        super(restTemplate);
    }

    /* (non-Javadoc)
     * @see org.springframework.social.box.api.UserOperations#getUserInformation()
     */
    @Override
    public BoxUser getUserInformation() {
        return getUserInformation(null);
    }

    /* (non-Javadoc)
     * @see org.springframework.social.box.api.UserOperations#getUserInformation(java.util.List)
     */
    @Override
    public BoxUser getUserInformation(List<BoxUserFields> fields) {
        return boxOperation(HttpMethod.GET, "users/me", fields, BoxUser.class);
    }
}
