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

import static org.hamcrest.core.IsInstanceOf.instanceOf;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.Properties;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.core.io.ClassPathResource;
import org.springframework.social.OperationNotPermittedException;
import org.springframework.social.ResourceNotFoundException;
import org.springframework.social.box.BoxIT;
import org.springframework.social.box.api.FileOperations.BoxFileFields;
import org.springframework.social.box.api.FolderOperations.BoxFolderFields;
import org.springframework.social.box.connect.BoxConnectionFactory;
import org.springframework.social.box.domain.BoxFile;
import org.springframework.social.box.domain.BoxFolderItems;
import org.springframework.social.connect.Connection;
import org.springframework.social.oauth2.AccessGrant;

/**
 *
 * @author Ioannis Nikolaou
 */
public class FileOperationsIT extends BoxIT {
    static String accessToken;
    private static FolderOperations folderOperations;
    private static FileOperations fileOperations;

    @BeforeClass
    public static void oneTimeSetUp() throws IOException {
        Properties p = new Properties();
        p.load(FileOperationsIT.class.getResourceAsStream("/boxIT.properties"));
        accessToken = p.getProperty("accessToken");

        BoxConnectionFactory connectionFactory = new BoxConnectionFactory("clientId", "clientSecret");
        AccessGrant bxAccessGrant = new AccessGrant(accessToken);
        Connection<Box> connection = connectionFactory.createConnection(bxAccessGrant);

        folderOperations = connection.getApi().folderOperations();
        fileOperations = connection.getApi().fileOperations();
    }

    @Test
    public void uploadFetchDeleteFile() {
        BoxFile boxFile = fileOperations.uploadFile("box_cyan.png", "0", new ClassPathResource("box_cyan.png"), Arrays.asList(BoxFileFields.ID));
        assertNotNull(boxFile.getId());
        assertNull(boxFile.getName());

        BoxFile boxFileAgain = fileOperations.getFileInformation(boxFile.getId(), Arrays.asList(BoxFileFields.NAME));
        assertNotNull(boxFileAgain.getName());

        fileOperations.deleteFile(boxFile.getId());

        try {
            fileOperations.getFileInformation(boxFile.getId());
        } catch (Exception e) {
            assertThat(e, instanceOf(ResourceNotFoundException.class));
        }

    }

    @Test
    public void uploadEmptyFile() {
        try {
            fileOperations.uploadFile("test", "0", null);
        } catch (Exception e) {
            assertThat(e, instanceOf(OperationNotPermittedException.class));
        }
    }

    @Test
    public void moveFile() {
        BoxFolderItems boxFolderItems;
        String boxFolderId, boxFileId;
        boxFolderId = folderOperations.createFolder("api folder", "0", Collections.singletonList(BoxFolderFields.ID)).getId();
        boxFileId = fileOperations.uploadFile("box_cyan.png", "0", new ClassPathResource("box_cyan.png"), Arrays.asList(BoxFileFields.ID)).getId();

        boxFolderItems = folderOperations.getFolderItems("0");
        assertTrue(containsItem(boxFileId, boxFolderItems));

        fileOperations.moveFile(boxFileId, boxFolderId);

        boxFolderItems = folderOperations.getFolderItems("0");
        assertFalse(containsItem(boxFileId, boxFolderItems));

        boxFolderItems = folderOperations.getFolderItems(boxFolderId);
        assertTrue(containsItem(boxFileId, boxFolderItems));

        folderOperations.deleteFolder(boxFolderId, true);
    }
}
