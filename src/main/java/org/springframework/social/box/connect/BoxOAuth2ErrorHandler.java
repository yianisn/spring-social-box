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

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.springframework.http.client.ClientHttpResponse;
import org.springframework.social.InvalidAuthorizationException;
import org.springframework.social.RejectedAuthorizationException;
import org.springframework.social.UncategorizedApiException;
import org.springframework.social.box.connect.domain.BoxOAuth2Error;
import org.springframework.social.box.connect.domain.json.BoxOAuth2ErrorModule;
import org.springframework.web.client.DefaultResponseErrorHandler;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Subclass of {@link DefaultResponseErrorHandler} that handles errors from box's
 * OAuth2 API, interpreting them into appropriate Spring SocialExceptions.
 * @author Ioannis Nikolaou
 */
public class BoxOAuth2ErrorHandler extends DefaultResponseErrorHandler {

    static final String BOX = "box";

    @Override
    public void handleError(ClientHttpResponse response) {
        String errorObject = null;
        BoxOAuth2Error boxOAuth2Error = null;

        try {
            errorObject = readFully(response.getBody());
        } catch (Exception e) {
            throw new UncategorizedApiException(BOX, "No error details from Box", e);
        }

        ObjectMapper mapper = new ObjectMapper(new JsonFactory());
        mapper.registerModule(new BoxOAuth2ErrorModule());

        try {
            boxOAuth2Error = mapper.readValue(errorObject, BoxOAuth2Error.class);
        } catch (Exception e) {
            throw new UncategorizedApiException(BOX, "Could not parse error details from Box - " + errorObject, e);
        }

        if ((boxOAuth2Error.getError() == null) || (boxOAuth2Error.getErrorDescription() == null)) {
            throw new RejectedAuthorizationException(BOX, "Error while performing an OAuth2 operation - " + errorObject);
        }

        handleBoxOAuth2Error(boxOAuth2Error);
    }

    private void handleBoxOAuth2Error(BoxOAuth2Error boxOAuth2Error) {
        if ("invalid_grant".equals(boxOAuth2Error.getError())) {
            throw new InvalidAuthorizationException(BOX, boxOAuth2Error.getErrorDescription());
        }
        // if not otherwise handled, wrap in RejectedAuthorizationException
        else  {
            throw new RejectedAuthorizationException(BOX, "Error while performing an OAuth2 operation. " + boxOAuth2Error.getError() + ": " + boxOAuth2Error.getErrorDescription());
        }
    }

    private String readFully(InputStream in) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));
        StringBuilder sb = new StringBuilder();
        while (reader.ready()) {
            sb.append(reader.readLine());
        }
        return sb.toString();
    }

}
