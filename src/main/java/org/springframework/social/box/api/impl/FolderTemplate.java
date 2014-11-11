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

import org.springframework.http.HttpMethod;
import org.springframework.social.box.api.FolderOperations;
import org.springframework.social.box.domain.BoxFolder;
import org.springframework.social.box.domain.BoxFolderItems;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;

/**
 *
 * @author Ioannis Nikolaou
 */
public class FolderTemplate extends BoxOperations implements FolderOperations {

    public FolderTemplate(RestTemplate restTemplate) {
        super(restTemplate);
    }

    /* (non-Javadoc)
     * @see org.springframework.social.box.api.FolderOperations#getFolderItems()
     */
    @Override
    public BoxFolderItems getFolderItems(String folderId) {
        return boxOperation(HttpMethod.GET, "folders/"+folderId+"/items", null, BoxFolderItems.class);
    }

    /* (non-Javadoc)
     * @see org.springframework.social.box.api.FolderOperations#createFolder(java.lang.String, java.lang.String)
     */
    @Override
    public BoxFolder createFolder(String name, String parentId) {
        JsonNodeFactory nodeFactory = new JsonNodeFactory(false);
        ObjectNode jsonBody = nodeFactory.objectNode();
        ObjectNode parent = nodeFactory.objectNode();
        parent.put("id", parentId);
        jsonBody.put("name", name);
        jsonBody.put("parent", parent);

        return boxOperation(HttpMethod.POST, "folders", null, jsonBody.toString(), BoxFolder.class);
    }



}
