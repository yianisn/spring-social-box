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
import org.springframework.social.box.api.FileOperations;
import org.springframework.social.box.domain.BoxFile;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author Ioannis Nikolaou
 */
public class FileTemplate extends BoxOperations implements FileOperations {

    private static final String FILE_OPERATION = "files/";

    public FileTemplate(RestTemplate restTemplate) {
        super(restTemplate);
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
}
