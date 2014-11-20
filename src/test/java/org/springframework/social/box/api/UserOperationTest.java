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
package org.springframework.social.box.api;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.springframework.http.HttpMethod.GET;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

import java.util.Arrays;

import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.social.box.BoxTest;
import org.springframework.social.box.api.UserOperations.BoxUserFields;
import org.springframework.social.box.api.impl.BoxTemplate;
import org.springframework.social.box.domain.BoxUser;
import org.springframework.social.box.domain.enums.BoxItemType;
import org.springframework.social.box.domain.enums.BoxUserRole;
import org.springframework.social.box.domain.enums.BoxUserStatus;
import org.springframework.test.web.client.MockRestServiceServer;

/**
 *
 * @author Ioannis Nikolaou
 */
public class UserOperationTest extends BoxTest {

    BoxTemplate boxTemplate = new BoxTemplate("accessToken");
    MockRestServiceServer mockRestServiceServer = MockRestServiceServer.createServer(boxTemplate.getRestTemplate());

    @Test
    public void userInformation() {
        mockRestServiceServer.expect(requestTo("https://api.box.com/2.0/users/me"))
            .andExpect(method(GET))
            .andRespond(withSuccess(jsonResource("userInformation"), MediaType.APPLICATION_JSON));

        BoxUser boxUser = boxTemplate.userOperations().getUserInformation();
        assertEquals("John Doe", boxUser.getName());
        assertEquals(BoxItemType.USER, boxUser.getType());
        assertEquals(BoxUserStatus.ACTIVE, boxUser.getStatus());
    }

    @Test
    public void userInformationBoxExampleData() {
        mockRestServiceServer.expect(requestTo("https://api.box.com/2.0/users/me"))
            .andExpect(method(GET))
            .andRespond(withSuccess(jsonResource("userInformationBoxExample"), MediaType.APPLICATION_JSON));

        BoxUser boxUser = boxTemplate.userOperations().getUserInformation();
        assertEquals(BoxUserRole.ADMIN, boxUser.getRole());
        assertEquals(BoxItemType.ENTERPRISE, boxUser.getEnterprise().getType());
        assertTrue(boxUser.getMyTags().contains("needs review"));
    }

    @Test
    public void userInformationSpecificFields() {
        mockRestServiceServer.expect(requestTo("https://api.box.com/2.0/users/me?fields=name%2Ccreated_at"))
        .andExpect(method(GET))
        .andRespond(withSuccess(jsonResource("userInformationSubset"), MediaType.APPLICATION_JSON));

        BoxUser boxUser = boxTemplate.userOperations().getUserInformation(Arrays.asList(BoxUserFields.NAME, BoxUserFields.CREATED_AT));
        assertEquals("John Doe", boxUser.getName());
        assertNotNull(boxUser.getCreatedAt());
        assertNull(boxUser.getRole());
    }
}
