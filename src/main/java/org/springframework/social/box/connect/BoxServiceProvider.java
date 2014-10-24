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
import org.springframework.social.oauth2.OAuth2Template;

/**
 *
 * @author Ioannis Nikolaou
 */
public class BoxServiceProvider extends AbstractOAuth2ServiceProvider<Box>{

    private static final String BOX_AUTHORIZE_URL = "https://app.box.com/api/oauth2/authorize";
    private static final String BOX_ACCESS_TOKEN_URL = "https://app.box.com/api/oauth2/token";

    /**
     * Creates a BoxServiceProvider for the given client ID and secret.
     * @param clientId The application's client_id assigned by box
     * @param appSecret The application's client_secret assigned by box
     */
    public BoxServiceProvider(String clientId, String clientSecret) {
        super(new OAuth2Template(clientId, clientSecret, BOX_AUTHORIZE_URL, BOX_ACCESS_TOKEN_URL));
        ((OAuth2Template)getOAuthOperations()).setUseParametersForClientAuthentication(true);
    }

    @Override
    public Box getApi(String accessToken) {
        return new BoxTemplate(accessToken);
    }

}
