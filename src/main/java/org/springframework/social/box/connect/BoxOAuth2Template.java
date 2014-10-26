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

import org.springframework.social.oauth2.OAuth2Template;

/**
 * box specific OAuth2Operations implementation that uses REST-template to make the OAuth calls
 * and throws Spring SocialException exceptions when something goes wrong
 *
 * @throws InvalidAuthorizationException when the OAuth2 operation fails with a box error object {"error":"invalid_grant", "error_description":"..."}'
 * @throws RejectedAuthorizationException when the OAuth2 operation fails with a box error object that is not understood
 * @throws UncategorizedApiException when the OAuth2 operation fails without a box error object or a box error object that cannot be parsed
 *
 * @author Ioannis Nikolaou
 */
public class BoxOAuth2Template extends OAuth2Template {

    private static final String BOX_AUTHORIZE_URL = "https://app.box.com/api/oauth2/authorize";
    private static final String BOX_ACCESS_TOKEN_URL = "https://api.box.com/oauth2/token";

    /**
     * Constructs an BoxOAuth2Template for a given set of client credentials.
     * The authorizeUrl and accessTokenUrl is set to the ones specified in https://developers.box.com/docs/#oauth-2
     *
     * @param clientId the client ID
     * @param clientSecret the client secret
     */
    public BoxOAuth2Template(String clientId, String clientSecret) {
        super(clientId, clientSecret, BOX_AUTHORIZE_URL, BOX_ACCESS_TOKEN_URL);
        setUseParametersForClientAuthentication(true);
        getRestTemplate().setErrorHandler(new BoxOAuth2ErrorHandler());
    }

}
