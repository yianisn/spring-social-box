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
import org.springframework.social.box.domain.BoxError;
import org.springframework.social.box.domain.json.BoxErrorMixin;
import org.springframework.web.client.DefaultResponseErrorHandler;
import org.springframework.web.client.ResponseErrorHandler;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.databind.ObjectMapper;

public class BoxErrorHandler extends DefaultResponseErrorHandler implements ResponseErrorHandler {
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
            throw new InvalidAuthorizationException(BoxOperations.BOX_PROVIDER_NAME, "The access token provided is invalid.");
        } else {
            BoxError boxError = null;
            try {
                boxError = mapper.readValue(response.getBody(), BoxError.class);
            } catch (Exception e) {
                throw new UncategorizedApiException(BoxOperations.BOX_PROVIDER_NAME, e.getMessage(), e);
            }

            String message = buildErrorMessage(boxError);
            String code = boxError.getCode();

            switch (convertErrorStatusToHttpStatus(boxError.getStatus())) {
            // 400 bad_request
            case BAD_REQUEST:
                handleBoxError400(code, message);
                break;
            // 403 forbidden
            case FORBIDDEN:
                handleBoxError403(code, message);
                break;
            // 404 not_found
            case NOT_FOUND:
                handleBoxError404(code, message);
                break;
            // 405 method_not_allowed
            case METHOD_NOT_ALLOWED:
                handleBoxError405(code, message);
                break;
            // 409 conflict
            case CONFLICT:
                handleBoxError409(code, message);
                break;
            // 412 precondition_failed
            case PRECONDITION_FAILED:
                handleBoxError412(code, message);
                break;
            // 429 too_many_requests
            case TOO_MANY_REQUESTS:
                handleBoxError429(code, message);
                break;
            // 500 internal_server_error
            case INTERNAL_SERVER_ERROR:
                handleBoxError500(code, message);
                break;
            // 503 unavailable
            case SERVICE_UNAVAILABLE:
                handleBoxError503(code, message);
                break;
            default:
                throw new UncategorizedApiException(BoxOperations.BOX_PROVIDER_NAME, message, new ApiException(BoxOperations.BOX_PROVIDER_NAME, message));
            }
        }
    }

    // 400 bad_request
    private void handleBoxError400(String code, String message) {
        if ("bad_request".equals(code)) {
            throw new ApiException(BoxOperations.BOX_PROVIDER_NAME, message);
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
            throw new OperationNotPermittedException(BoxOperations.BOX_PROVIDER_NAME,
                    message);
        } else {
            throw new UncategorizedApiException(BoxOperations.BOX_PROVIDER_NAME, message,
                    new ApiException(BoxOperations.BOX_PROVIDER_NAME, message));
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
            throw new OperationNotPermittedException(BoxOperations.BOX_PROVIDER_NAME, message);
        } else {
            throw new UncategorizedApiException(BoxOperations.BOX_PROVIDER_NAME, message,
                    new ApiException(BoxOperations.BOX_PROVIDER_NAME, message));
        }
    }

    // 404 not_found
    private void handleBoxError404(String code, String message) {
        if (code.matches(
                "not_found|"
                + "preview_cannot_be_generated|"
                + "trashed|"
                + "not_trashed")) {
            throw new ResourceNotFoundException(BoxOperations.BOX_PROVIDER_NAME, message);
        } else {
            throw new UncategorizedApiException(BoxOperations.BOX_PROVIDER_NAME, message,
                    new ApiException(BoxOperations.BOX_PROVIDER_NAME, message));
        }
    }

    // 405 method_not_allowed
    private void handleBoxError405(String code, String message) {
        if ("method_not_allowed".equals(code)) {
            throw new OperationNotPermittedException(BoxOperations.BOX_PROVIDER_NAME,
                    message);
        } else {
            throw new UncategorizedApiException(BoxOperations.BOX_PROVIDER_NAME, message,
                    new ApiException(BoxOperations.BOX_PROVIDER_NAME, message));
        }
    }

    // 409 conflict
    private void handleBoxError409(String code, String message) {
        if (code.matches(""
                + "item_name_in_use|"
                + "conflict|"
                + "user_login_already_used|"
                + "recent_similar_comment")) {
            throw new DuplicateStatusException(BoxOperations.BOX_PROVIDER_NAME, message);
        } else {
            throw new UncategorizedApiException(BoxOperations.BOX_PROVIDER_NAME, message,
                    new ApiException(BoxOperations.BOX_PROVIDER_NAME, message));
        }
    }

    // 412 precondition_failed
    private void handleBoxError412(String code, String message) {
        if ("precondition_failed".equals(code)) {
            throw new OperationNotPermittedException(BoxOperations.BOX_PROVIDER_NAME,
                    message);
        } else {
            throw new UncategorizedApiException(BoxOperations.BOX_PROVIDER_NAME, message,
                    new ApiException(BoxOperations.BOX_PROVIDER_NAME, message));
        }
    }

    // 429 too_many_requests
    private void handleBoxError429(String code, String message) {
        if ("rate_limit_exceeded".equals(code)) {
            throw new RateLimitExceededException(BoxOperations.BOX_PROVIDER_NAME);
        } else {
            throw new UncategorizedApiException(BoxOperations.BOX_PROVIDER_NAME, message,
                    new ApiException(BoxOperations.BOX_PROVIDER_NAME, message));
        }
    }

    // 500 internal_server_error
    private void handleBoxError500(String code, String message) {
        if ("internal_server_error".equals(code)) {
            throw new InternalServerErrorException(BoxOperations.BOX_PROVIDER_NAME,
                    message);
        } else {
            throw new UncategorizedApiException(BoxOperations.BOX_PROVIDER_NAME, message,
                    new ApiException(BoxOperations.BOX_PROVIDER_NAME, message));
        }
    }

    // 503 unavailable
    private void handleBoxError503(String code, String message) {
        if ("unavailable".equals(code)) {
            throw new ServerDownException(BoxOperations.BOX_PROVIDER_NAME, message);
        } else {
            throw new UncategorizedApiException(BoxOperations.BOX_PROVIDER_NAME, message,
                    new ApiException(BoxOperations.BOX_PROVIDER_NAME, message));
        }
    }

    private String buildErrorMessage(BoxError boxError) {
        String code = boxError.getCode();
        StringBuilder sb = new StringBuilder(boxError.getMessage()).
                append(". Status: ").append(boxError.getStatus()).
                append(". Error code: ").append(code);

        if (boxError.getHelpUrl() != null) {
            sb.append(". More information about this error at ").
               append(boxError.getHelpUrl());
        }
        return sb.toString();
    }

    private HttpStatus convertErrorStatusToHttpStatus(Integer status) {
        try {
            return HttpStatus.valueOf(status);
        } catch (IllegalArgumentException e) {
            throw new UncategorizedApiException(BoxOperations.BOX_PROVIDER_NAME, "Unknown http error code.", new ApiException(BoxOperations.BOX_PROVIDER_NAME, "Unknown http error code."));
        }
    }
}
