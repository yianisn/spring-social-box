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
package org.springframework.social.oauth2;

import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.springframework.http.HttpMethod.POST;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withBadRequest;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.social.InvalidAuthorizationException;
import org.springframework.social.RejectedAuthorizationException;
import org.springframework.social.UncategorizedApiException;
import org.springframework.social.box.AbstractBoxTest;
import org.springframework.social.box.connect.BoxOAuth2Template;
import org.springframework.test.web.client.MockRestServiceServer;

/**
 *
 * @author Ioannis Nikolaou
 */
public class BoxOAuth2TemplateTest extends AbstractBoxTest{
    BoxOAuth2Template boxOAuth2Template = new BoxOAuth2Template("myClientId", "myClientSecret");
    MockRestServiceServer mockRestServiceServer = MockRestServiceServer.createServer(boxOAuth2Template.getRestTemplate());

    @Test
    public void buildAuthorizeUrl() {
        String authorizeUrl = boxOAuth2Template.buildAuthorizeUrl(new OAuth2Parameters());
        assertTrue(authorizeUrl.startsWith("https://app.box.com/api/oauth2/authorize"));
    }

    @Test
    public void exchangeForAccess() {
        mockRestServiceServer.expect(requestTo("https://api.box.com/oauth2/token"))
                             .andExpect(method(POST))
                             .andExpect(content().string(containsString("client_id=myClientId")))
                             .andExpect(content().string(containsString("client_secret=myClientSecret")))
                             .andExpect(content().string(containsString("code=myAuthorizationCode")))
                             .andExpect(content().string(containsString("redirect_uri=myRedirectUri")))
                             .andExpect(content().string(containsString("grant_type=authorization_code")))
                             .andRespond(withSuccess(jsonResource("access_token"), MediaType.APPLICATION_JSON));
        AccessGrant accessGrant = boxOAuth2Template.exchangeForAccess("myAuthorizationCode", "myRedirectUri", null);

        mockRestServiceServer.verify();
        assertEquals("T9cE5asGnuyYCCqIZFoWjFHvNbvVqHjl", accessGrant.getAccessToken());
    }

    @Test
    public void badRequest() {
        mockRestServiceServer.expect(requestTo("https://api.box.com/oauth2/token"))
        .andExpect(method(POST))
        .andRespond(withBadRequest());

        try {
            boxOAuth2Template.exchangeForAccess("authorizationCode",
                    "redirectUri", null);
        } catch (UncategorizedApiException e) {
            assertEquals("400 Bad Request - No error details from Box", e.getMessage());
        }
    }

    @Test
    public void invalidGrant() {
        final String boxJsonError = "{\"error\":\"invalid_grant\", \"error_description\":\"box grant error description\"}";
        mockRestServiceServer.expect(requestTo("https://api.box.com/oauth2/token"))
        .andExpect(method(POST))
        .andRespond(withBadRequest()
                    .body(boxJsonError)
                    .contentType(MediaType.APPLICATION_JSON));
        try {
            boxOAuth2Template.exchangeForAccess("authorizationCode", "redirectUri", null);
        } catch (InvalidAuthorizationException e) {
            assertEquals("box grant error description", e.getMessage());
        }
    }

    @Test
    public void invalidGrantInExtendedErrorObject() {
        final String boxJsonError =
                "{\"error\":\"invalid_grant\", "
              + "\"error_description\":\"box grant error description\","
              + "\"additional\":\"box data\"}";
        mockRestServiceServer.expect(requestTo("https://api.box.com/oauth2/token"))
        .andExpect(method(POST))
        .andRespond(withBadRequest()
                    .body(boxJsonError)
                    .contentType(MediaType.APPLICATION_JSON));
        try {
            boxOAuth2Template.exchangeForAccess("authorizationCode", "redirectUri", null);
        } catch (InvalidAuthorizationException e) {
            assertEquals("box grant error description", e.getMessage());
        }
    }

    @Test
    public void unknownBoxError() {
        final String boxJsonError = "{\"error\":\"something_we_do_not_know_about\", \"error_description\":\"box grant error description\"}";
        mockRestServiceServer.expect(requestTo("https://api.box.com/oauth2/token"))
        .andExpect(method(POST))
        .andRespond(withBadRequest()
                    .body(boxJsonError)
                    .contentType(MediaType.APPLICATION_JSON));
        try {
            boxOAuth2Template.exchangeForAccess("authorizationCode", "redirectUri", null);
        } catch (RejectedAuthorizationException e) {
            assertEquals("Error while performing an OAuth2 operation. something_we_do_not_know_about: box grant error description", e.getMessage());
        }
    }

    @Test
    public void unknownErrorObject1() {
        final String boxJsonError = "{\"unknown\":\"error object\"}";
        mockRestServiceServer.expect(requestTo("https://api.box.com/oauth2/token"))
        .andExpect(method(POST))
        .andRespond(withBadRequest()
                    .body(boxJsonError)
                    .contentType(MediaType.APPLICATION_JSON));
        try {
            boxOAuth2Template.exchangeForAccess("authorizationCode", "redirectUri", null);
        } catch (RejectedAuthorizationException e) {
            assertEquals("Error while performing an OAuth2 operation - " + boxJsonError, e.getMessage());
        }
    }

    @Test
    public void unknownErrorObject2() {
        final String boxJsonError = "{\"error\":\"invalid_grant\", \"unknown\":\"error object\"}";
        mockRestServiceServer.expect(requestTo("https://api.box.com/oauth2/token"))
        .andExpect(method(POST))
        .andRespond(withBadRequest()
                    .body(boxJsonError)
                    .contentType(MediaType.APPLICATION_JSON));
        try {
            boxOAuth2Template.exchangeForAccess("authorizationCode", "redirectUri", null);
        } catch (RejectedAuthorizationException e) {
            assertEquals("Error while performing an OAuth2 operation - " + boxJsonError, e.getMessage());
        }
    }

    @Test
    public void invalidErrorObject() {
        final String boxJsonError = "{\"invalid\":\"error object\"";
        mockRestServiceServer.expect(requestTo("https://api.box.com/oauth2/token"))
        .andExpect(method(POST))
        .andRespond(withBadRequest()
                    .body(boxJsonError)
                    .contentType(MediaType.APPLICATION_JSON));
        try {
            boxOAuth2Template.exchangeForAccess("authorizationCode", "redirectUri", null);
        } catch (UncategorizedApiException e) {
            assertEquals("Could not parse error details from Box - " + boxJsonError, e.getMessage());
        }
    }

}
