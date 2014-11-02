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

import java.util.Arrays;

import org.springframework.social.ApiException;
import org.springframework.social.box.api.Box;
import org.springframework.social.box.api.impl.UserTemplate.BoxUserFields;
import org.springframework.social.box.domain.BoxUser;
import org.springframework.social.connect.ApiAdapter;
import org.springframework.social.connect.ConnectionValues;
import org.springframework.social.connect.UserProfile;
import org.springframework.social.connect.UserProfileBuilder;

/**
 * box ApiAdapter implementation.
 * @author Ioannis Nikolaou
 */
public class BoxAdapter implements ApiAdapter<Box>{

    /* (non-Javadoc)
     * @see org.springframework.social.connect.ApiAdapter#test(java.lang.Object)
     */
    public boolean test(Box api) {
        try {
            api.userOperations().getUserInformation(Arrays.asList(BoxUserFields.ID));
            return true;
        } catch (ApiException e) {
            return false;
        }
    }

    /* (non-Javadoc)
     * @see org.springframework.social.connect.ApiAdapter#setConnectionValues(java.lang.Object, org.springframework.social.connect.ConnectionValues)
     */
    public void setConnectionValues(Box api, ConnectionValues values) {
        BoxUser boxUser = api.userOperations().getUserInformation(
                Arrays.asList(BoxUserFields.ID,
                              BoxUserFields.NAME,
                              BoxUserFields.AVATAR_URL));
        values.setProviderUserId(boxUser.getId());
        values.setDisplayName(boxUser.getName());
        values.setProfileUrl(null);
        values.setImageUrl(boxUser.getAvatarUrl());
    }

    /* (non-Javadoc)
     * @see org.springframework.social.connect.ApiAdapter#fetchUserProfile(java.lang.Object)
     */
    public UserProfile fetchUserProfile(Box api) {
        BoxUser boxUser = api.userOperations().getUserInformation(
                Arrays.asList(BoxUserFields.LOGIN,
                              BoxUserFields.NAME));
        return new UserProfileBuilder()
                .setUsername(boxUser.getLogin())
                .setName(boxUser.getName())
                .setEmail(boxUser.getLogin()).build();
    }

    /* (non-Javadoc)
     * @see org.springframework.social.connect.ApiAdapter#updateStatus(java.lang.Object, java.lang.String)
     */
    public void updateStatus(Box api, String message) {
        return;
    }

}
