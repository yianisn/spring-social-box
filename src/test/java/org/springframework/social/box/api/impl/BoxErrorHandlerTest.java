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

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;

import java.io.ByteArrayInputStream;

import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.mock.http.client.MockClientHttpResponse;
import org.springframework.social.InvalidAuthorizationException;
import org.springframework.social.UncategorizedApiException;

/**
 *
 * @author Ioannis Nikolaou
 */
public class BoxErrorHandlerTest {

    BoxErrorHandler boxErrorHandler = new BoxErrorHandler();

    @Test
    public void handleUnauthorized() {
        ClientHttpResponse clientHttpResponse = new MockClientHttpResponse((byte[])null,HttpStatus.UNAUTHORIZED);
        try {
            boxErrorHandler.handleError(clientHttpResponse);
        } catch (Exception e) {
            assertTrue(e instanceof InvalidAuthorizationException);
            assertEquals("The access token provided is invalid.", e.getMessage());
        }
    }

    @Test
    public void handleUnsupportedErrorCode() {
        String error = "{\"type\":\"error\", \"status\":418, \"message\":\"message\", \"request_id\":\"requestId\", \"code\":\"bad_request\"}";
        ClientHttpResponse clientHttpResponse = new MockClientHttpResponse(new ByteArrayInputStream((error).getBytes()),HttpStatus.I_AM_A_TEAPOT);
        try {
            boxErrorHandler.handleError(clientHttpResponse);
        } catch (Exception e) {
            assertTrue(e instanceof UncategorizedApiException);
        }
    }

    @Test
    public void handleWrongErrorCode() {
        String error = "{\"type\":\"error\", \"status\":123, \"message\":\"message\", \"request_id\":\"requestId\", \"code\":\"bad_request\"}";
        ClientHttpResponse clientHttpResponse = new MockClientHttpResponse(new ByteArrayInputStream((error).getBytes()),HttpStatus.ACCEPTED);
        try {
            boxErrorHandler.handleError(clientHttpResponse);
        } catch (Exception e) {
            assertTrue(e instanceof UncategorizedApiException);
        }
    }

    @Test
    public void handleUnknownError() {
        String error = "{\"unknown\":\"error\"}";
        ClientHttpResponse clientHttpResponse = new MockClientHttpResponse(new ByteArrayInputStream((error).getBytes()),HttpStatus.BAD_REQUEST);
        try {
            boxErrorHandler.handleError(clientHttpResponse);
        } catch (Exception e) {
            assertTrue(e instanceof UncategorizedApiException);
        }
    }

 }
