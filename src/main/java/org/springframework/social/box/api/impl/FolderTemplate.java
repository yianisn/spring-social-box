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
import org.springframework.social.UncategorizedApiException;
import org.springframework.social.box.api.FolderOperations;
import org.springframework.social.box.domain.BoxFolder;
import org.springframework.social.box.domain.BoxFolderItems;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;

/**
 *
 * @author Ioannis Nikolaou
 */
public class FolderTemplate extends BoxOperations implements FolderOperations {

    private static final String FOLDER_OPERATION = "folders/";

    public FolderTemplate(RestTemplate restTemplate) {
        super(restTemplate);
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * org.springframework.social.box.api.FolderOperations#getFolderInformation
     * (java.lang.String)
     */
    @Override
    public BoxFolder getFolderInformation(String folderId) {
        return getFolderInformation(folderId, null);
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * org.springframework.social.box.api.FolderOperations#getFolderInformation
     * (java.lang.String, java.util.List)
     */
    @Override
    public BoxFolder getFolderInformation(String folderId,
            List<BoxFolderFields> fields) {
        return boxOperation(HttpMethod.GET, FOLDER_OPERATION + folderId,
                fields, BoxFolder.class);
    }

    /*
     * (non-Javadoc)
     *
     * @see org.springframework.social.box.api.FolderOperations#getFolderItems()
     */
    @Override
    public BoxFolderItems getFolderItems(String folderId) {
        return getFolderItems(folderId, null);
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * org.springframework.social.box.api.FolderOperations#getFolderItems(java
     * .lang.String, java.util.List)
     */
    @Override
    public BoxFolderItems getFolderItems(String folderId,
            List<BoxFolderItemsFields> fields) {
        return boxOperation(HttpMethod.GET, FOLDER_OPERATION + folderId
                + "/items", fields, BoxFolderItems.class);
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * org.springframework.social.box.api.FolderOperations#createFolder(java
     * .lang.String, java.lang.String)
     */
    @Override
    public BoxFolder createFolder(String name, String parentId) {
        return createFolder(name, parentId, null);
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * org.springframework.social.box.api.FolderOperations#createFolder(java
     * .lang.String, java.lang.String, java.util.List)
     */
    @Override
    public BoxFolder createFolder(String name, String parentId,
            List<BoxFolderFields> fields) {
        try {
            return boxOperation(HttpMethod.POST, "folders", fields,
                    mapper.writeValueAsString(new BoxNewItem(name, parentId)),
                    BoxFolder.class);
        } catch (JsonProcessingException e) {
            throw new UncategorizedApiException(BOX_PROVIDER_NAME,
                    "spring-social-bx internal error", e);
        }
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * org.springframework.social.box.api.FolderOperations#updateFolderName(
     * java.lang.String, java.lang.String)
     */
    @Override
    public BoxFolder updateFolderName(String folderId, String newName) {
        return updateFolder(folderId, newName, null, null, null);
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * org.springframework.social.box.api.FolderOperations#updateFolderName(
     * java.lang.String, java.lang.String, java.util.List)
     */
    @Override
    public BoxFolder updateFolderName(String folderId, String newName,
            List<BoxFolderFields> fields) {
        return updateFolder(folderId, newName, null, null, fields);
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * org.springframework.social.box.api.FolderOperations#updateFolderDescription
     * (java.lang.String, java.lang.String)
     */
    @Override
    public BoxFolder updateFolderDescription(String folderId,
            String newDescription) {
        return updateFolder(folderId, null, newDescription, null, null);
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * org.springframework.social.box.api.FolderOperations#updateFolderDescription
     * (java.lang.String, java.lang.String, java.util.List)
     */
    @Override
    public BoxFolder updateFolderDescription(String folderId,
            String newDescription, List<BoxFolderFields> fields) {
        return updateFolder(folderId, null, newDescription, null, fields);
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * org.springframework.social.box.api.FolderOperations#updateFolderTags(
     * java.lang.String, java.util.List)
     */
    @Override
    public BoxFolder updateFolderTags(String folderId, List<String> newTags) {
        return updateFolder(folderId, null, null, newTags, null);
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * org.springframework.social.box.api.FolderOperations#updateFolderTags(
     * java.lang.String, java.util.List, java.util.List)
     */
    @Override
    public BoxFolder updateFolderTags(String folderId, List<String> newTags,
            List<BoxFolderFields> fields) {
        return updateFolder(folderId, null, null, newTags, fields);
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * org.springframework.social.box.api.FolderOperations#updateFolder(java
     * .lang.String, org.springframework.social.box.domain.BoxFolderUpdate)
     */
    @Override
    public BoxFolder updateFolder(String folderId, String newName,
            String newDescription, List<String> newTags,
            List<BoxFolderFields> fields) {
        try {
            return boxOperation(HttpMethod.PUT, FOLDER_OPERATION + folderId, fields,
                    mapper.writeValueAsString(new BoxFolderUpdate(newName, newDescription, newTags)),
                    BoxFolder.class);
        } catch (JsonProcessingException e) {
            throw new UncategorizedApiException(BOX_PROVIDER_NAME, "spring-social-box internal error", e);
        }
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * org.springframework.social.box.api.FolderOperations#deleteFolder(java
     * .lang.String, java.lang.Boolean)
     */
    @Override
    public void deleteFolder(String folderId, Boolean recursive) {
        if (recursive == null) {
            recursive = false;
        }
        boxOperation(HttpMethod.DELETE, FOLDER_OPERATION + folderId
                + "?recursive=" + recursive.toString());
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * org.springframework.social.box.api.FolderOperations#getFolderInformation
     * (java.lang.String)
     */
    @Override
    public BoxFolder moveFolder(String folderId, String newParentFolderId) {
        return moveFolder(folderId, newParentFolderId, null);
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * org.springframework.social.box.api.FolderOperations#getFolderInformation
     * (java.lang.String)
     */
    @Override
    public BoxFolder moveFolder(String folderId, String newParentFolderId,
            List<BoxFolderFields> fields) {
        try {
            return boxOperation(HttpMethod.PUT, FOLDER_OPERATION + folderId, fields,
                    mapper.writeValueAsString(new BoxNewItem(null, newParentFolderId)),
                    BoxFolder.class);
        } catch (JsonProcessingException e) {
            throw new UncategorizedApiException(BOX_PROVIDER_NAME, "spring-social-box internal error", e);
        }
    }

    @JsonInclude(Include.NON_NULL)
    private class BoxFolderUpdate {
        @JsonProperty("name")
        String name;
        @JsonProperty("description")
        String description;
        @JsonProperty("tags")
        List<String> tags;

        public BoxFolderUpdate(String name, String description,
                List<String> tags) {
            this.name = name;
            this.description = description;
            this.tags = tags;
        }
    }

}
