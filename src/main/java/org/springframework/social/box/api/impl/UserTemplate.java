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

import java.net.URI;

import org.springframework.social.box.api.UserOperations;
import org.springframework.social.box.domain.BoxUser;
import org.springframework.social.support.URIBuilder;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author Ioannis Nikolaou
 */
public class UserTemplate extends AbstractBoxOperations implements UserOperations {

    private final RestTemplate restTemplate;

    public UserTemplate(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    /* (non-Javadoc)
     * @see org.springframework.social.box.api.UserOperations#getUserInformation()
     */
    public BoxUser getUserInformation() {
        URI uri = URIBuilder.fromUri(BOX_API_URL + "users/me").build();
        return restTemplate.getForObject(uri, BoxUser.class);
    }

}
