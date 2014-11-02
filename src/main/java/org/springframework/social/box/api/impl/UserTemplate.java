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

import java.util.List;

import org.springframework.http.HttpMethod;
import org.springframework.social.box.api.UserOperations;
import org.springframework.social.box.domain.BoxUser;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author Ioannis Nikolaou
 */
public class UserTemplate extends BoxOperations implements UserOperations {

    public UserTemplate(RestTemplate restTemplate) {
        super(restTemplate);
    }

    /* (non-Javadoc)
     * @see org.springframework.social.box.api.UserOperations#getUserInformation()
     */
    public BoxUser getUserInformation() {
        return getUserInformation(null);
    }

    /* (non-Javadoc)
     * @see org.springframework.social.box.api.UserOperations#getUserInformation(java.util.List)
     */
    public BoxUser getUserInformation(List<BoxUserFields> fields) {
        return boxOperation(HttpMethod.GET, "users/me", fields, BoxUser.class);
    }

    /**
     * The available fields that can be used to define the subset of the user information  data ({@link BoxUser})
     * that will be retrieved from box.
     * @author Ioannis Nikolaou
     */
    public enum BoxUserFields {
        /**
         * For users is ‘user’
         */
        TYPE,
        /**
         * A unique string identifying this user.
         */
        ID,
        /**
         * The name of this user.
         */
        NAME,
        /**
         * The email address this user uses to login.
         */
        LOGIN,
        /**
         * The time this user was created.
         */
        CREATED_AT,
        /**
         * The time this user was last modified.
         */
        MODIFIED_AT,
        /**
         * This user’s enterprise role. Can be admin, coadmin, or user.
         */
        ROLE,
        /**
         * The language of this user.
         */
        LANGUAGE,
        /**
         * The timezone of this user.
         */
        TIMEZONE,
        /**
         * The user’s total available space amount in bytes.
         */
        SPACE_AMOUNT,
        /**
         * The amount of space in use by the user.
         */
        SPACE_USED,
        /**
         * The maximum individual file size in bytes this user can have.
         */
        MAX_UPLOAD_SIZE,
        /**
         * An array of key/value pairs set by the user’s admin.
         */
        TRACKING_CODES,
        /**
         * Whether this user can see other enterprise users in her contact list.
         */
        CAN_SEE_MANAGED_USERS,
        /**
         * Whether or not this user can use Box Sync.
         */
        IS_SYNC_ENABLED,
        /**
         * Whether this user is allowed to collaborate with users outside her enterprise.
         */
        IS_EXTERNAL_COLLAB_RESTRICTED,
        /**
         * Can be active, inactive. cannot_delete_edit, or cannot_delete_edit_upload.
         */
        STATUS,
        /**
         * The user’s job title.
         */
        JOB_TITLE,
        /**
         * The user’s phone number.
         */
        PHONE,
        /**
         * The user’s address.
         */
        ADDRESS,
        /**
         * URL of this user’s avatar image.
         */
        AVATAR_URL,
        /**
         * Whether to exempt this user from Enterprise device limits.
         */
        IS_EXEMPT_FROM_DEVICE_LIMITS,
        /**
         * Whether or not this user must use two-factor authentication.
         */
        IS_EXEMPT_FROM_LOGIN_VERIFICATION,
        /**
         * Mini representation of this user’s enterprise, including the ID of its enterprise.
         */
        ENTERPRISE,
        /**
         * Tags for all files and folders owned by this user.
         */
        MY_TAGS;
    }
}
