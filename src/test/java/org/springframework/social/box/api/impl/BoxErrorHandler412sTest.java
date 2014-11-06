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

import java.io.ByteArrayInputStream;

import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.mock.http.client.MockClientHttpResponse;
import org.springframework.social.OperationNotPermittedException;
import org.springframework.social.UncategorizedApiException;

/**
 *
 * @author Ioannis Nikolaou
 */
public class BoxErrorHandler412sTest {

    BoxErrorHandler boxErrorHandler = new BoxErrorHandler();
    String error = "{\"type\":\"error\", \"status\":412, \"help_url\":\"helpUrl\", \"message\":\"message\", \"request_id\":\"requestId\", \"code\":";

    @Test
    public void handlePreconditionFailed() {
        ClientHttpResponse clientHttpResponse = new MockClientHttpResponse(new ByteArrayInputStream((error + "\"precondition_failed\"}").getBytes()),HttpStatus.PRECONDITION_FAILED);
        try {
            boxErrorHandler.handleError(clientHttpResponse);
        } catch (Exception e) {
            assertTrue(e instanceof OperationNotPermittedException);
        }
    }

    @Test
    public void handleUnknownStatus() {
        ClientHttpResponse clientHttpResponse = new MockClientHttpResponse(new ByteArrayInputStream((error + "\"unknown_status\"}").getBytes()),HttpStatus.PRECONDITION_FAILED);
        try {
            boxErrorHandler.handleError(clientHttpResponse);
        } catch (Exception e) {
            assertTrue(e instanceof UncategorizedApiException);
        }
    }
}
