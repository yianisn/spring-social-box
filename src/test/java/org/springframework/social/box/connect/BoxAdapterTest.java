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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.anyListOf;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.springframework.social.ApiException;
import org.springframework.social.box.AbstractBoxTest;
import org.springframework.social.box.api.Box;
import org.springframework.social.box.api.UserOperations;
import org.springframework.social.box.api.impl.UserTemplate.BoxUserFields;
import org.springframework.social.box.domain.BoxUser;
import org.springframework.social.connect.ConnectionValues;
import org.springframework.social.connect.UserProfile;

/**
 *
 * @author Ioannis Nikolaou
 */
public class BoxAdapterTest extends AbstractBoxTest {

    private BoxAdapter apiAdapter = new BoxAdapter();

    private Box mockBox = mock(Box.class);

    @Test
    public void testOk() {
        BoxUser mockBoxUser = mock(BoxUser.class);
        UserOperations mockUserOperations = mock(UserOperations.class);
        when(mockBox.userOperations()).thenReturn(mockUserOperations);
        when(mockUserOperations.getUserInformation(anyListOf(BoxUserFields.class))).thenReturn(mockBoxUser);

        assertTrue(apiAdapter.test(mockBox));
    }

    @Test
    public void testNotOk() {
        UserOperations mockUserOperations = mock(UserOperations.class);
        when(mockBox.userOperations()).thenReturn(mockUserOperations);
        when(mockUserOperations.getUserInformation(anyListOf(BoxUserFields.class))).thenThrow(new ApiException("boxId", "message"));

        assertFalse(apiAdapter.test(mockBox));
    }

    @Test
    public void fetchUserProfile() {
        BoxUser mockBoxUser = mock(BoxUser.class);
        when(mockBoxUser.getLogin()).thenReturn("john.doe@that.com");
        when(mockBoxUser.getName()).thenReturn("John Doe");

        UserOperations mockUserOperations = mock(UserOperations.class);
        when(mockBox.userOperations()).thenReturn(mockUserOperations);
        when(mockUserOperations.getUserInformation(anyListOf(BoxUserFields.class))).thenReturn(mockBoxUser);
        UserProfile profile = apiAdapter.fetchUserProfile(mockBox);
        assertEquals("John Doe", profile.getName());
        assertEquals("john.doe@that.com", profile.getUsername());
        assertEquals("john.doe@that.com", profile.getEmail());
        assertEquals("John", profile.getFirstName());
        assertEquals("Doe", profile.getLastName());
    }

    @Test
    public void setConnectionValues() {
        BoxUser mockBoxUser = mock(BoxUser.class);
        when(mockBoxUser.getId()).thenReturn("john.doe@that.com");
        when(mockBoxUser.getName()).thenReturn("John Doe");
        when(mockBoxUser.getAvatarUrl()).thenReturn("http://...");

        UserOperations mockUserOperations = mock(UserOperations.class);
        when(mockBox.userOperations()).thenReturn(mockUserOperations);
        when(mockUserOperations.getUserInformation(anyListOf(BoxUserFields.class))).thenReturn(mockBoxUser);
        TestConnectionValues connectionValues = new TestConnectionValues();
        apiAdapter.setConnectionValues(mockBox, connectionValues);
        assertEquals("john.doe@that.com", connectionValues.getProviderUserId());
        assertEquals("John Doe", connectionValues.getDisplayName());
        assertEquals("http://...", connectionValues.getImageUrl());
        assertNull(connectionValues.getProfileUrl());
    }

    private static class TestConnectionValues implements ConnectionValues {

        private String displayName;
        private String imageUrl;
        private String profileUrl;
        private String providerUserId;

        public String getDisplayName() {
            return displayName;
        }

        public void setDisplayName(String displayName) {
            this.displayName = displayName;
        }

        public String getImageUrl() {
            return imageUrl;
        }

        public void setImageUrl(String imageUrl) {
            this.imageUrl = imageUrl;
        }

        public String getProfileUrl() {
            return profileUrl;
        }

        public void setProfileUrl(String profileUrl) {
            this.profileUrl = profileUrl;
        }

        public String getProviderUserId() {
            return providerUserId;
        }

        public void setProviderUserId(String providerUserId) {
            this.providerUserId = providerUserId;
        }

    }
}
