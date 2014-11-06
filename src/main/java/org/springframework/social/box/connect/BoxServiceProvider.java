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
package org.springframework.social.box.connect;

import org.springframework.social.box.api.Box;
import org.springframework.social.box.api.impl.BoxTemplate;
import org.springframework.social.oauth2.AbstractOAuth2ServiceProvider;

/**
 * box ServiceProvider implementation.
 * @author Ioannis Nikolaou
 */
public class BoxServiceProvider extends AbstractOAuth2ServiceProvider<Box>{

    /**
     * Creates a BoxServiceProvider for the given client ID and secret.
     * @param clientId The application's client_id assigned by box
     * @param appSecret The application's client_secret assigned by box
     */
    public BoxServiceProvider(String clientId, String clientSecret) {
        super(new BoxOAuth2Template(clientId, clientSecret));
    }

    @Override
    public Box getApi(String accessToken) {
        return new BoxTemplate(accessToken);
    }

}
