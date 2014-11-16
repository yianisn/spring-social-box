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
import org.springframework.social.box.api.FileOperations;
import org.springframework.social.box.domain.BoxFile;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 *
 * @author Ioannis Nikolaou
 */
public class FileTemplate extends BoxOperations implements FileOperations {

    private static final String FILE_OPERATION = "files/";
    private ObjectMapper mapper;

    public FileTemplate(RestTemplate restTemplate) {
        super(restTemplate);
        mapper = new ObjectMapper(new JsonFactory());
    }

    /* (non-Javadoc)
     * @see org.springframework.social.box.api.FileOperations#getFileInformation(java.lang.String)
     */
    @Override
    public BoxFile getFileInformation(String fileId) {
        return getFileInformation(fileId, null);
    }

    /* (non-Javadoc)
     * @see org.springframework.social.box.api.FileOperations#getFileInformation(java.lang.String, java.util.List)
     */
    @Override
    public BoxFile getFileInformation(String fileId, List<BoxFileFields> fields) {
        return boxOperation(HttpMethod.GET, FILE_OPERATION + fileId, fields, BoxFile.class);
    }

    /* (non-Javadoc)
     * @see org.springframework.social.box.api.FileOperations#updateFileName(java.lang.String, java.lang.String)
     */
    @Override
    public BoxFile updateFileName(String fileId, String newName) {
        return updateFile(fileId, newName, null, null, null);
    }

    /* (non-Javadoc)
     * @see org.springframework.social.box.api.FileOperations#updateFileName(java.lang.String, java.lang.String, java.util.List)
     */
    @Override
    public BoxFile updateFileName(String fileId, String newName, List<BoxFileFields> fields) {
        return updateFile(fileId, newName, null, null, fields);
    }

    /* (non-Javadoc)
     * @see org.springframework.social.box.api.FileOperations#updateFileDescription(java.lang.String, java.lang.String)
     */
    @Override
    public BoxFile updateFileDescription(String fileId, String newDescription) {
        return updateFile(fileId, null, newDescription, null, null);
    }

    /* (non-Javadoc)
     * @see org.springframework.social.box.api.FileOperations#updateFileDescription(java.lang.String, java.lang.String, java.util.List)
     */
    @Override
    public BoxFile updateFileDescription(String fileId, String newDescription, List<BoxFileFields> fields) {
        return updateFile(fileId, null, newDescription, null, fields);
    }

    /* (non-Javadoc)
     * @see org.springframework.social.box.api.FileOperations#updateFileTags(java.lang.String, java.util.List)
     */
    @Override
    public BoxFile updateFileTags(String fileId, List<String> newTags) {
        return updateFile(fileId, null, null, newTags, null);
    }

    /* (non-Javadoc)
     * @see org.springframework.social.box.api.FileOperations#updateFileTags(java.lang.String, java.util.List, java.util.List)
     */
    @Override
    public BoxFile updateFileTags(String fileId, List<String> newTags, List<BoxFileFields> fields) {
        return updateFile(fileId, null, null, newTags, fields);
    }

    /* (non-Javadoc)
     * @see org.springframework.social.box.api.FileOperations#updateFile(java.lang.String, java.lang.String, java.lang.String, java.util.List, java.util.List)
     */
    @Override
    public BoxFile updateFile(String fileId, String newName, String newDescription, List<String> newTags, List<BoxFileFields> fields) {
        try {
            return boxOperation(HttpMethod.PUT, FILE_OPERATION + fileId, fields, mapper.writeValueAsString(new BoxFileUpdate(newName, newDescription, newTags)), BoxFile.class);
        } catch (JsonProcessingException e) {
            throw new UncategorizedApiException(BOX_PROVIDER_NAME, "spring-social-bx internal error", e);
        }
    }

    @JsonInclude(Include.NON_NULL)
    private class BoxFileUpdate {
        @JsonProperty("name")
        String name;
        @JsonProperty("description")
        String description;
        @JsonProperty("tags")
        List<String> tags;

        public BoxFileUpdate(String name, String description, List<String> tags) {
            this.name = name;
            this.description = description;
            this.tags = tags;
        }
    }
}
