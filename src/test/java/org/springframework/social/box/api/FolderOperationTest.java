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
import static org.springframework.http.HttpMethod.GET;
import static org.springframework.http.HttpMethod.POST;
import static org.springframework.http.HttpMethod.DELETE;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withNoContent;

import java.util.Arrays;

import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.social.box.AbstractBoxTest;
import org.springframework.social.box.api.FolderOperations.BoxFolderFields;
import org.springframework.social.box.api.FolderOperations.BoxFolderItemsFields;
import org.springframework.social.box.api.impl.BoxTemplate;
import org.springframework.social.box.domain.BoxFolder;
import org.springframework.social.box.domain.BoxFolderItems;
import org.springframework.social.box.domain.enums.BoxItemType;
import org.springframework.test.web.client.MockRestServiceServer;

/**
 *
 * @author Ioannis Nikolaou
 */
public class FolderOperationTest extends AbstractBoxTest {

    BoxTemplate boxTemplate = new BoxTemplate("accessToken");
    MockRestServiceServer mockRestServiceServer = MockRestServiceServer.createServer(boxTemplate.getRestTemplate());

    @Test
    public void getFolderInformationBoxExampleData() {
        mockRestServiceServer.expect(requestTo("https://api.box.com/2.0/folders/0"))
        .andExpect(method(GET))
        .andRespond(withSuccess(jsonResource("folderInformationBoxExample"), MediaType.APPLICATION_JSON));

        BoxFolder boxFolder = boxTemplate.folderOperations().getFolderInformation("0");

        mockRestServiceServer.verify();
        assertEquals(BoxItemType.FOLDER, boxFolder.getType());
        assertEquals("1", boxFolder.getEtag());
        assertEquals("active", boxFolder.getItemStatus());
        assertEquals((Integer)100, boxFolder.getItemCollection().getLimit());
    }

    @Test
    public void getFolderInformationSpecificFields() {
        mockRestServiceServer.expect(requestTo("https://api.box.com/2.0/folders/123?fields=id"))
        .andExpect(method(GET))
        .andRespond(withSuccess(jsonResource("folderItemsBoxExample"), MediaType.APPLICATION_JSON));

        boxTemplate.folderOperations().getFolderInformation("123", Arrays.asList(BoxFolderFields.ID));

        mockRestServiceServer.verify();
    }

    @Test
    public void getFolderItemsBoxExampleData() {
        mockRestServiceServer.expect(requestTo("https://api.box.com/2.0/folders/0/items"))
        .andExpect(method(GET))
        .andRespond(withSuccess(jsonResource("folderItemsBoxExample"), MediaType.APPLICATION_JSON));

        BoxFolderItems boxFolderItems = boxTemplate.folderOperations().getFolderItems("0");

        mockRestServiceServer.verify();
        assertEquals((Integer)24, boxFolderItems.getTotalCount());
        assertEquals((Integer)0, boxFolderItems.getOffset());
        assertEquals((Integer)2, boxFolderItems.getLimit());
        assertEquals((int)2, boxFolderItems.getEntries().size());
        assertEquals(BoxItemType.FOLDER, boxFolderItems.getEntries().get(0).getType());
        assertEquals(BoxItemType.FILE, boxFolderItems.getEntries().get(1).getType());
        assertEquals((int)2, boxFolderItems.getOrder().size());
    }

    @Test
    public void getFolderItemsSpecificFields() {
        mockRestServiceServer.expect(requestTo("https://api.box.com/2.0/folders/0/items?fields=sha1%2Ccan_non_owners_invite"))
        .andExpect(method(GET))
        .andRespond(withSuccess(jsonResource("folderItemsBoxExample"), MediaType.APPLICATION_JSON));

        boxTemplate.folderOperations().getFolderItems("0",
                Arrays.asList(BoxFolderItemsFields.SHA1, BoxFolderItemsFields.CAN_NON_OWNERS_INVITE));

        mockRestServiceServer.verify();
    }

    @Test
    public void createFolderBoxExampleData() {
        mockRestServiceServer.expect(requestTo("https://api.box.com/2.0/folders"))
        .andExpect(content().string("{\"name\":\"A folder\",\"parent\":{\"id\":\"123\"}}"))
        .andExpect(method(POST))
        .andRespond(withSuccess(jsonResource("createFolderBoxExample"), MediaType.APPLICATION_JSON));

        boxTemplate.folderOperations().createFolder("A folder", "123");

        mockRestServiceServer.verify();
    }

    @Test
    public void createFolderSpecificFields() {
        mockRestServiceServer.expect(requestTo("https://api.box.com/2.0/folders?fields=path_collection"))
        .andExpect(content().string("{\"name\":\"A folder\",\"parent\":{\"id\":\"123\"}}"))
        .andExpect(method(POST))
        .andRespond(withSuccess(jsonResource("folderItemsBoxExample"), MediaType.APPLICATION_JSON));

        boxTemplate.folderOperations().createFolder("A folder", "123", Arrays.asList(BoxFolderFields.PATH_COLLECTION));

        mockRestServiceServer.verify();
    }

    @Test
    public void deleteFolder() {
        mockRestServiceServer.expect(requestTo("https://api.box.com/2.0/folders/123?recursive=false"))
        .andExpect(method(DELETE))
        .andRespond(withNoContent());

        boxTemplate.folderOperations().deleteFolder("123", null);

        mockRestServiceServer.verify();
    }

    @Test
    public void deleteFolderRecursive() {
        mockRestServiceServer.expect(requestTo("https://api.box.com/2.0/folders/123?recursive=true"))
        .andExpect(method(DELETE))
        .andRespond(withNoContent());

        boxTemplate.folderOperations().deleteFolder("123", true);

        mockRestServiceServer.verify();
    }

}

