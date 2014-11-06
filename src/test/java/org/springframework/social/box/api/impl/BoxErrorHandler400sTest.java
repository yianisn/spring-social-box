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
import org.springframework.social.ApiException;
import org.springframework.social.OperationNotPermittedException;
import org.springframework.social.UncategorizedApiException;

/**
 *
 * @author Ioannis Nikolaou
 */
public class BoxErrorHandler400sTest {

    BoxErrorHandler boxErrorHandler = new BoxErrorHandler();
    String error = "{\"type\":\"error\", \"status\":400, \"help_url\":\"helpUrl\", \"message\":\"message\", \"request_id\":\"requestId\", \"code\":";

    @Test
    public void handleBadRequest() {
        ClientHttpResponse clientHttpResponse = new MockClientHttpResponse(new ByteArrayInputStream((error + "\"bad_request\"}").getBytes()),HttpStatus.BAD_REQUEST);
        try {
            boxErrorHandler.handleError(clientHttpResponse);
        } catch (Exception e) {
            assertTrue(e instanceof ApiException);
        }
    }

    @Test
    public void handleItemNameInvalid() {
        ClientHttpResponse clientHttpResponse = new MockClientHttpResponse(new ByteArrayInputStream((error + "\"item_name_invalid\"}").getBytes()),HttpStatus.BAD_REQUEST);
        try {
            boxErrorHandler.handleError(clientHttpResponse);
        } catch (Exception e) {
            assertTrue(e instanceof OperationNotPermittedException);
        }
    }

    @Test
    public void handleTermsOfServiceRequired() {
        ClientHttpResponse clientHttpResponse = new MockClientHttpResponse(new ByteArrayInputStream((error + "\"terms_of_service_required\"}").getBytes()),HttpStatus.BAD_REQUEST);
        try {
            boxErrorHandler.handleError(clientHttpResponse);
        } catch (Exception e) {
            assertTrue(e instanceof OperationNotPermittedException);
        }
    }

    @Test
    public void handleRequestedPreviewUnavailable() {
        ClientHttpResponse clientHttpResponse = new MockClientHttpResponse(new ByteArrayInputStream((error + "\"requested_preview_unavailable\"}").getBytes()),HttpStatus.BAD_REQUEST);
        try {
            boxErrorHandler.handleError(clientHttpResponse);
        } catch (Exception e) {
            assertTrue(e instanceof OperationNotPermittedException);
        }
    }

    @Test
    public void handleFolderNotEmpty() {
        ClientHttpResponse clientHttpResponse = new MockClientHttpResponse(new ByteArrayInputStream((error + "\"folder_not_empty\"}").getBytes()),HttpStatus.BAD_REQUEST);
        try {
            boxErrorHandler.handleError(clientHttpResponse);
        } catch (Exception e) {
            assertTrue(e instanceof OperationNotPermittedException);
        }
    }

    @Test
    public void handleInvalidRequestParameters() {
        ClientHttpResponse clientHttpResponse = new MockClientHttpResponse(new ByteArrayInputStream((error + "\"invalid_request_parameters\"}").getBytes()),HttpStatus.BAD_REQUEST);
        try {
            boxErrorHandler.handleError(clientHttpResponse);
        } catch (Exception e) {
            assertTrue(e instanceof OperationNotPermittedException);
        }
    }

    @Test
    public void handleUserAlreadyCollaborator() {
        ClientHttpResponse clientHttpResponse = new MockClientHttpResponse(new ByteArrayInputStream((error + "\"user_already_collaborator\"}").getBytes()),HttpStatus.BAD_REQUEST);
        try {
            boxErrorHandler.handleError(clientHttpResponse);
        } catch (Exception e) {
            assertTrue(e instanceof OperationNotPermittedException);
        }
    }

    @Test
    public void handleCannotMakeVollaboratedSubfolderPrivate() {
        ClientHttpResponse clientHttpResponse = new MockClientHttpResponse(new ByteArrayInputStream((error + "\"cannot_make_collaborated_subfolder_private\"}").getBytes()),HttpStatus.BAD_REQUEST);
        try {
            boxErrorHandler.handleError(clientHttpResponse);
        } catch (Exception e) {
            assertTrue(e instanceof OperationNotPermittedException);
        }
    }

    @Test
    public void handleItemNameTooLong() {
        ClientHttpResponse clientHttpResponse = new MockClientHttpResponse(new ByteArrayInputStream((error + "\"item_name_too_long\"}").getBytes()),HttpStatus.BAD_REQUEST);
        try {
            boxErrorHandler.handleError(clientHttpResponse);
        } catch (Exception e) {
            assertTrue(e instanceof OperationNotPermittedException);
        }
    }

    @Test
    public void handleCollaborationsNotAvailableOnRootFolder() {
        ClientHttpResponse clientHttpResponse = new MockClientHttpResponse(new ByteArrayInputStream((error + "\"collaborations_not_available_on_root_folder\"}").getBytes()),HttpStatus.BAD_REQUEST);
        try {
            boxErrorHandler.handleError(clientHttpResponse);
        } catch (Exception e) {
            assertTrue(e instanceof OperationNotPermittedException);
        }
    }

    @Test
    public void handleSyncItemMoveFailure() {
        ClientHttpResponse clientHttpResponse = new MockClientHttpResponse(new ByteArrayInputStream((error + "\"sync_item_move_failure\"}").getBytes()),HttpStatus.BAD_REQUEST);
        try {
            boxErrorHandler.handleError(clientHttpResponse);
        } catch (Exception e) {
            assertTrue(e instanceof OperationNotPermittedException);
        }
    }

    @Test
    public void handleRequestedPageOutOfRange() {
        ClientHttpResponse clientHttpResponse = new MockClientHttpResponse(new ByteArrayInputStream((error + "\"requested_page_out_of_range\"}").getBytes()),HttpStatus.BAD_REQUEST);
        try {
            boxErrorHandler.handleError(clientHttpResponse);
        } catch (Exception e) {
            assertTrue(e instanceof OperationNotPermittedException);
        }
    }

    @Test
    public void handleCyclicalFolderStructure() {
        ClientHttpResponse clientHttpResponse = new MockClientHttpResponse(new ByteArrayInputStream((error + "\"cyclical_folder_structure\"}").getBytes()),HttpStatus.BAD_REQUEST);
        try {
            boxErrorHandler.handleError(clientHttpResponse);
        } catch (Exception e) {
            assertTrue(e instanceof OperationNotPermittedException);
        }
    }

    @Test
    public void handleBadDigest() {
        ClientHttpResponse clientHttpResponse = new MockClientHttpResponse(new ByteArrayInputStream((error + "\"bad_digest\"}").getBytes()),HttpStatus.BAD_REQUEST);
        try {
            boxErrorHandler.handleError(clientHttpResponse);
        } catch (Exception e) {
            assertTrue(e instanceof OperationNotPermittedException);
        }
    }

    @Test
    public void handleInvalidCollaborationItem() {
        ClientHttpResponse clientHttpResponse = new MockClientHttpResponse(new ByteArrayInputStream((error + "\"invalid_collaboration_item\"}").getBytes()),HttpStatus.BAD_REQUEST);
        try {
            boxErrorHandler.handleError(clientHttpResponse);
        } catch (Exception e) {
            assertTrue(e instanceof OperationNotPermittedException);
        }
    }

    @Test
    public void handleTaskAssigneeNotAllowed() {
        ClientHttpResponse clientHttpResponse = new MockClientHttpResponse(new ByteArrayInputStream((error + "\"task_assignee_not_allowed\"}").getBytes()),HttpStatus.BAD_REQUEST);
        try {
            boxErrorHandler.handleError(clientHttpResponse);
        } catch (Exception e) {
            assertTrue(e instanceof OperationNotPermittedException);
        }
    }

    @Test
    public void handleInvalidStatus() {
        ClientHttpResponse clientHttpResponse = new MockClientHttpResponse(new ByteArrayInputStream((error + "\"invalid_status\"}").getBytes()),HttpStatus.BAD_REQUEST);
        try {
            boxErrorHandler.handleError(clientHttpResponse);
        } catch (Exception e) {
            assertTrue(e instanceof OperationNotPermittedException);
        }
    }

    @Test
    public void handleUnknownStatus() {
        ClientHttpResponse clientHttpResponse = new MockClientHttpResponse(new ByteArrayInputStream((error + "\"unknown_status\"}").getBytes()),HttpStatus.BAD_REQUEST);
        try {
            boxErrorHandler.handleError(clientHttpResponse);
        } catch (Exception e) {
            assertTrue(e instanceof UncategorizedApiException);
        }
    }
}
