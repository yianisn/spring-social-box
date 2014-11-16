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
import static org.junit.Assert.assertTrue;
import static org.springframework.http.HttpMethod.GET;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

import java.util.Arrays;

import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.social.box.AbstractBoxTest;
import org.springframework.social.box.api.FileOperations.BoxFileFields;
import org.springframework.social.box.api.impl.BoxTemplate;
import org.springframework.social.box.domain.BoxFile;
import org.springframework.social.box.domain.enums.BoxItemType;
import org.springframework.test.web.client.MockRestServiceServer;

/**
 *
 * @author Ioannis Nikolaou
 */
public class FileOperationsTest extends AbstractBoxTest {

    BoxTemplate boxTemplate = new BoxTemplate("accessToken");
    MockRestServiceServer mockRestServiceServer = MockRestServiceServer.createServer(boxTemplate.getRestTemplate());

    @Test
    public void getFolderItemsBoxExampleData() {
        mockRestServiceServer.expect(requestTo("https://api.box.com/2.0/files/5000948880"))
        .andExpect(method(GET))
        .andRespond(withSuccess(jsonResource("fileInformationBoxExample"), MediaType.APPLICATION_JSON));

        BoxFile boxFile = boxTemplate.fileOperations().getFileInformation("5000948880");

        mockRestServiceServer.verify();
        assertEquals("134b65991ed521fcfe4724b7d814ab8ded5185dc", boxFile.getSha1());
        assertEquals((Integer)2, boxFile.getPathCollection().getTotalCount());
        assertEquals(BoxItemType.FILE, boxFile.getType());
        assertTrue(boxFile.getSharedLink().getPermissions().getCanDownload());
    }

    @Test
    public void getFileInformationSpecificFields() {
        mockRestServiceServer.expect(requestTo("https://api.box.com/2.0/files/5000948880?fields=id"))
        .andExpect(method(GET))
        .andRespond(withSuccess(jsonResource("fileInformationBoxExample"), MediaType.APPLICATION_JSON));

        boxTemplate.fileOperations().getFileInformation("5000948880", Arrays.asList(BoxFileFields.ID));
    }
}

