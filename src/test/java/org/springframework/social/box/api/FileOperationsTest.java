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

import static org.hamcrest.core.StringContains.containsString;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.springframework.http.HttpMethod.DELETE;
import static org.springframework.http.HttpMethod.GET;
import static org.springframework.http.HttpMethod.POST;
import static org.springframework.http.HttpMethod.PUT;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.header;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withNoContent;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

import java.util.Arrays;

import org.junit.Test;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.social.box.BoxTest;
import org.springframework.social.box.api.FileOperations.BoxFileFields;
import org.springframework.social.box.api.impl.BoxTemplate;
import org.springframework.social.box.domain.BoxFile;
import org.springframework.social.box.domain.enums.BoxItemType;
import org.springframework.test.web.client.MockRestServiceServer;

/**
 *
 * @author Ioannis Nikolaou
 */
public class FileOperationsTest extends BoxTest {

    BoxTemplate boxTemplate = new BoxTemplate("accessToken");
    MockRestServiceServer mockRestServiceServer = MockRestServiceServer.createServer(boxTemplate.getRestTemplate());

    @Test
    public void getFileItemsBoxExampleData() {
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


    @Test
    public void updateFileName() {
        mockRestServiceServer.expect(requestTo("https://api.box.com/2.0/files/123"))
        .andExpect(content().string("{\"name\":\"new name\"}"))
        .andExpect(method(PUT))
        .andRespond(withSuccess(jsonResource("fileInformationBoxExample"), MediaType.APPLICATION_JSON));

        boxTemplate.fileOperations().updateFileName("123", "new name");

        mockRestServiceServer.verify();
    }

    @Test
    public void updateFileDescription() {
        mockRestServiceServer.expect(requestTo("https://api.box.com/2.0/files/123"))
        .andExpect(content().string("{\"description\":\"new description\"}"))
        .andExpect(method(PUT))
        .andRespond(withSuccess(jsonResource("fileInformationBoxExample"), MediaType.APPLICATION_JSON));

        boxTemplate.fileOperations().updateFileDescription("123", "new description");

        mockRestServiceServer.verify();
    }

    @Test
    public void updateFileTags() {
        mockRestServiceServer.expect(requestTo("https://api.box.com/2.0/files/123"))
        .andExpect(content().string("{\"tags\":[\"tag 1\",\"tag 2\"]}"))
        .andExpect(method(PUT))
        .andRespond(withSuccess(jsonResource("fileInformationBoxExample"), MediaType.APPLICATION_JSON));

        boxTemplate.fileOperations().updateFileTags("123", Arrays.asList("tag 1", "tag 2"));

        mockRestServiceServer.verify();
    }

    @Test
    public void updateFileLimitResponse() {
        mockRestServiceServer.expect(requestTo("https://api.box.com/2.0/files/123?fields=content_modified_at"))
        .andExpect(content().string("{\"name\":\"new name\",\"description\":\"\"}"))
        .andExpect(method(PUT))
        .andRespond(withSuccess(jsonResource("fileInformationBoxExample"), MediaType.APPLICATION_JSON));

        boxTemplate.fileOperations().updateFile("123", "new name", "", null, Arrays.asList(BoxFileFields.CONTENT_MODIFIED_AT));

        mockRestServiceServer.verify();
    }

    @SuppressWarnings("unchecked")
    @Test
    public void uploadFile() {
        mockRestServiceServer.expect(requestTo("https://upload.box.com/api/2.0/files/content"))
        .andExpect(header("Content-Type", containsString("multipart/form-data")))
        .andExpect(content().string(containsString("{"
                                                + "\"parent\":{\"id\":\"123\"},"
                                                + "\"name\":\"filename\""
                                                + "}")))
        .andExpect(method(POST))
        .andRespond(withSuccess(jsonResource("uploadFileBoxExample"), MediaType.APPLICATION_JSON));

        Resource file = null;
        BoxFile boxFile = boxTemplate.fileOperations().uploadFile("filename", "123", file);

        mockRestServiceServer.verify();
        assertEquals("3", boxFile.getEtag());
    }

    @SuppressWarnings("unchecked")
    @Test
    public void uploadFileLimitResponse() {
        mockRestServiceServer.expect(requestTo("https://upload.box.com/api/2.0/files/content?fields=id"))
        .andExpect(header("Content-Type", containsString("multipart/form-data")))
        .andExpect(content().string(containsString("{"
                                                 + "\"parent\":{\"id\":\"123\"},"
                                                 + "\"name\":\"filename\""
                                                 + "}")))
        .andExpect(method(POST))
        .andRespond(withSuccess(jsonResource("uploadFileBoxExample"), MediaType.APPLICATION_JSON));

        Resource file = null;
        BoxFile boxFile = boxTemplate.fileOperations().uploadFile("filename", "123", file, Arrays.asList(BoxFileFields.ID));

        mockRestServiceServer.verify();
        assertEquals("3", boxFile.getSequenceId());
    }

    @Test
    public void deleteFile() {
        mockRestServiceServer.expect(requestTo("https://api.box.com/2.0/files/123"))
        .andExpect(method(DELETE))
        .andRespond(withNoContent());

        boxTemplate.fileOperations().deleteFile("123");

        mockRestServiceServer.verify();
    }

    @Test
    public void copyFileBoxExampleData() {
        mockRestServiceServer.expect(requestTo("https://api.box.com/2.0/files/fileId/copy"))
        .andExpect(content().string("{\"parent\":{\"id\":\"folderId\"}}"))
        .andExpect(method(POST))
        .andRespond(withSuccess(jsonResource("copyFileBoxExample"), MediaType.APPLICATION_JSON));

        boxTemplate.fileOperations().copyFile("fileId", "folderId");

        mockRestServiceServer.verify();
    }

    @Test
    public void copyFileNewName() {
        mockRestServiceServer.expect(requestTo("https://api.box.com/2.0/files/fileId/copy"))
        .andExpect(content().string("{\"parent\":{\"id\":\"folderId\"},\"name\":\"new Name\"}"))
        .andExpect(method(POST))
        .andRespond(withSuccess(jsonResource("copyFileBoxExample"), MediaType.APPLICATION_JSON));

        boxTemplate.fileOperations().copyFile("fileId", "folderId", "new Name");

        mockRestServiceServer.verify();
    }

    @Test
    public void copyFileSpecificFields() {
        mockRestServiceServer.expect(requestTo("https://api.box.com/2.0/files/fileId/copy?fields=id"))
        .andExpect(content().string("{\"parent\":{\"id\":\"folderId\"},\"name\":\"new Name\"}"))
        .andExpect(method(POST))
        .andRespond(withSuccess(jsonResource("copyFileBoxExample"), MediaType.APPLICATION_JSON));

        boxTemplate.fileOperations().copyFile("fileId", "folderId", "new Name", Arrays.asList(BoxFileFields.ID));

        mockRestServiceServer.verify();
    }

    @Test
    public void moveFileBoxExampleData() {
        mockRestServiceServer.expect(requestTo("https://api.box.com/2.0/files/fileId"))
        .andExpect(content().string("{\"parent\":{\"id\":\"folderId\"}}"))
        .andExpect(method(PUT))
        .andRespond(withSuccess(jsonResource("fileInformationBoxExample"), MediaType.APPLICATION_JSON));

        boxTemplate.fileOperations().moveFile("fileId", "folderId");

        mockRestServiceServer.verify();
    }

    @Test
    public void moveFileSpecificFields() {
        mockRestServiceServer.expect(requestTo("https://api.box.com/2.0/files/fileId?fields=id"))
        .andExpect(content().string("{\"parent\":{\"id\":\"folderId\"}}"))
        .andExpect(method(PUT))
        .andRespond(withSuccess(jsonResource("fileInformationBoxExample"), MediaType.APPLICATION_JSON));

        boxTemplate.fileOperations().moveFile("fileId", "folderId", Arrays.asList(BoxFileFields.ID));

        mockRestServiceServer.verify();
    }
}

