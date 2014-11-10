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
import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.social.box.AbstractBoxTest;
import org.springframework.social.box.api.impl.BoxTemplate;
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
    public void getFolderItemsBoxExampleData() {
        mockRestServiceServer.expect(requestTo("https://api.box.com/2.0/folders/0/items"))
        .andExpect(method(GET))
        .andRespond(withSuccess(jsonResource("folderItemsBoxExample"), MediaType.APPLICATION_JSON));

        BoxFolderItems boxFolderItems = boxTemplate.folderOperations().getFolderItems("0");

        assertEquals((Integer)24, boxFolderItems.getTotalCount());
        assertEquals((Integer)0, boxFolderItems.getOffset());
        assertEquals((Integer)2, boxFolderItems.getLimit());
        assertEquals((int)2, boxFolderItems.getEntries().size());
        assertEquals(BoxItemType.FOLDER, boxFolderItems.getEntries().get(0).getType());
        assertEquals(BoxItemType.FILE, boxFolderItems.getEntries().get(1).getType());
        assertEquals((int)2, boxFolderItems.getOrder().size());
    }
}

