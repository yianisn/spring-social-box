package org.springframework.social.box.api.impl;

import java.io.IOException;

import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.social.ApiException;
import org.springframework.social.DuplicateStatusException;
import org.springframework.social.InternalServerErrorException;
import org.springframework.social.InvalidAuthorizationException;
import org.springframework.social.OperationNotPermittedException;
import org.springframework.social.RateLimitExceededException;
import org.springframework.social.ResourceNotFoundException;
import org.springframework.social.ServerDownException;
import org.springframework.social.UncategorizedApiException;
import org.springframework.social.box.api.Box;
import org.springframework.social.box.domain.BoxError;
import org.springframework.social.box.domain.json.BoxErrorMixin;
import org.springframework.web.client.DefaultResponseErrorHandler;
import org.springframework.web.client.ResponseErrorHandler;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.databind.ObjectMapper;

public class BoxErrorHandler extends DefaultResponseErrorHandler implements
        ResponseErrorHandler {
    private ObjectMapper mapper;

    public BoxErrorHandler() {
        mapper = new ObjectMapper(new JsonFactory());
        mapper.addMixInAnnotations(BoxError.class, BoxErrorMixin.class);
    }

    @Override
    public void handleError(ClientHttpResponse response) throws IOException {
        HttpStatus statusCode = response.getStatusCode();
        if (statusCode == HttpStatus.UNAUTHORIZED) {
            // The api.box.com endpoint returns the error object for the unauthorized access
            // in the response header instead of the response body...
            // < WWW-Authenticate: Bearer realm="Service",  error="invalid_token", error_description="The access token provided is invalid."
            throw new InvalidAuthorizationException(Box.BOX_PROVIDER_NAME, "The access token provided is invalid.");
        } else {
            BoxError boxError = null;
            try {
                boxError = mapper.readValue(response.getBody(), BoxError.class);
            } catch (Exception e) {
                throw new UncategorizedApiException(Box.BOX_PROVIDER_NAME,
                        e.getMessage(), e);
            }
            Integer status = boxError.getStatus();
            String code = boxError.getCode();
            StringBuilder sb = new StringBuilder(boxError.getMessage()).
                    append(". Status: ").append(status).
                    append(". Error code: ").append(code);

            if (boxError.getHelpUrl() != null)
                sb.append(". More information about this error at ").
                   append(boxError.getHelpUrl());
            String message = sb.toString();

            switch (status) {
            case 400: // 400 bad_request
                handleBoxError400(code, message);
                break;
            case 403: // 403 forbidden
                handleBoxError403(code, message);
                break;
            case 404: // 404 not_found
                handleBoxError404(code, message);
                break;
            case 405: // 405 method_not_allowed
                handleBoxError405(code, message);
                break;
            case 409: // 409 conflict
                handleBoxError409(code, message);
                break;
            case 412: // 412 precondition_failed
                handleBoxError412(code, message);
                break;
            case 429: // 429 too_many_requests
                handleBoxError429(code, message);
                break;
            case 500: // 500 internal_server_error
                handleBoxError500(code, message);
                break;
            case 503: // 503 unavailable
                handleBoxError503(code, message);
                break;
            default:
                throw new UncategorizedApiException(Box.BOX_PROVIDER_NAME, message, new ApiException(Box.BOX_PROVIDER_NAME, message));
            }
        }
    }

    // 400 bad_request
    private void handleBoxError400(String code, String message) {
        if ("bad_request".equals(code)) {
            throw new ApiException(Box.BOX_PROVIDER_NAME, message);
        } else if (code.matches(
                "item_name_invalid|"
                + "terms_of_service_required|"
                + "requested_preview_unavailable|"
                + "folder_not_empty|"
                + "invalid_request_parameters|"
                + "user_already_collaborator|"
                + "cannot_make_collaborated_subfolder_private|"
                + "item_name_too_long|"
                + "collaborations_not_available_on_root_folder|"
                + "sync_item_move_failure|"
                + "requested_page_out_of_range|"
                + "cyclical_folder_structure|"
                + "bad_digest|"
                + "invalid_collaboration_item|"
                + "task_assignee_not_allowed|"
                + "invalid_status")) {
            throw new OperationNotPermittedException(Box.BOX_PROVIDER_NAME,
                    message);
        } else {
            throw new UncategorizedApiException(Box.BOX_PROVIDER_NAME, message,
                    new ApiException(Box.BOX_PROVIDER_NAME, message));
        }
    }

    // 403 forbidden
    private void handleBoxError403(String code, String message) {
        if (code.matches(
                  "forbidden|"
                + "storage_limit_exceeded|"
                + "access_denied_insufficient_permissions|"
                + "access_denied_item_locked|"
                + "file_size_limit_exceeded|"
                + "incorrect_shared_item_password")) {
            throw new OperationNotPermittedException(Box.BOX_PROVIDER_NAME, message);
        } else {
            throw new UncategorizedApiException(Box.BOX_PROVIDER_NAME, message,
                    new ApiException(Box.BOX_PROVIDER_NAME, message));
        }
    }

    // 404 not_found
    private void handleBoxError404(String code, String message) {
        if (code.matches(
                "not_found|"
                + "preview_cannot_be_generated|"
                + "trashed|"
                + "not_trashed")) {
            throw new ResourceNotFoundException(Box.BOX_PROVIDER_NAME, message);
        } else {
            throw new UncategorizedApiException(Box.BOX_PROVIDER_NAME, message,
                    new ApiException(Box.BOX_PROVIDER_NAME, message));
        }
    }

    // 405 method_not_allowed
    private void handleBoxError405(String code, String message) {
        if ("method_not_allowed".equals(code)) {
            throw new OperationNotPermittedException(Box.BOX_PROVIDER_NAME,
                    message);
        } else {
            throw new UncategorizedApiException(Box.BOX_PROVIDER_NAME, message,
                    new ApiException(Box.BOX_PROVIDER_NAME, message));
        }
    }

    // 409 conflict
    private void handleBoxError409(String code, String message) {
        if (code.matches(""
                + "item_name_in_use|"
                + "conflict|"
                + "user_login_already_used|"
                + "recent_similar_comment")) {
            throw new DuplicateStatusException(Box.BOX_PROVIDER_NAME, message);
        } else {
            throw new UncategorizedApiException(Box.BOX_PROVIDER_NAME, message,
                    new ApiException(Box.BOX_PROVIDER_NAME, message));
        }
    }

    // 412 precondition_failed
    private void handleBoxError412(String code, String message) {
        if ("precondition_failed".equals(code)) {
            throw new OperationNotPermittedException(Box.BOX_PROVIDER_NAME,
                    message);
        } else {
            throw new UncategorizedApiException(Box.BOX_PROVIDER_NAME, message,
                    new ApiException(Box.BOX_PROVIDER_NAME, message));
        }
    }

    // 429 too_many_requests
    private void handleBoxError429(String code, String message) {
        if ("rate_limit_exceeded".equals(code)) {
            throw new RateLimitExceededException(Box.BOX_PROVIDER_NAME);
        } else {
            throw new UncategorizedApiException(Box.BOX_PROVIDER_NAME, message,
                    new ApiException(Box.BOX_PROVIDER_NAME, message));
        }
    }

    // 500 internal_server_error
    private void handleBoxError500(String code, String message) {
        if ("internal_server_error".equals(code)) {
            throw new InternalServerErrorException(Box.BOX_PROVIDER_NAME,
                    message);
        } else {
            throw new UncategorizedApiException(Box.BOX_PROVIDER_NAME, message,
                    new ApiException(Box.BOX_PROVIDER_NAME, message));
        }
    }

    // 503 unavailable
    private void handleBoxError503(String code, String message) {
        if ("unavailable".equals(code)) {
            throw new ServerDownException(Box.BOX_PROVIDER_NAME, message);
        } else {
            throw new UncategorizedApiException(Box.BOX_PROVIDER_NAME, message,
                    new ApiException(Box.BOX_PROVIDER_NAME, message));
        }
    }
}
