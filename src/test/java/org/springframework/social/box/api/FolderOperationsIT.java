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
import static org.junit.Assert.fail;

import java.io.IOException;
import java.util.Arrays;
import java.util.Properties;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.social.box.api.FolderOperations.BoxFolderFields;
import org.springframework.social.box.api.FolderOperations.BoxFolderItemsFields;
import org.springframework.social.box.connect.BoxConnectionFactory;
import org.springframework.social.box.domain.BoxFolder;
import org.springframework.social.box.domain.BoxFolderItems;
import org.springframework.social.box.domain.enums.BoxItemType;
import org.springframework.social.box.domain.internal.BoxFileMini;
import org.springframework.social.connect.Connection;
import org.springframework.social.oauth2.AccessGrant;

/**
 *
 * @author Ioannis Nikolaou
 */
public class FolderOperationsIT {
    static String accessToken;
    private static FolderOperations folderOperations;

    @BeforeClass
    public static void oneTimeSetUp() throws IOException {
        Properties p = new Properties();
        p.load(FolderOperationsIT.class.getResourceAsStream("/boxIT.properties"));
        accessToken = p.getProperty("accessToken");

        BoxConnectionFactory connectionFactory = new BoxConnectionFactory("clientId", "clientSecret");
        AccessGrant bxAccessGrant = new AccessGrant(accessToken);
        Connection<Box> connection = connectionFactory.createConnection(bxAccessGrant);
        folderOperations = connection.getApi().folderOperations();
    }

    @Test
    public void createModifyDeleteFolder() {
        BoxFolder boxFolder;
        boxFolder = folderOperations.createFolder("api folder", "0");
        assertEquals("api folder", boxFolder.getName());

        boxFolder = folderOperations.updateFolderName(boxFolder.getId(), "new api folder name", Arrays.asList(BoxFolderFields.NAME));
        assertEquals("new api folder name", boxFolder.getName());

        folderOperations.deleteFolder(boxFolder.getId(), false);

        BoxFolderItems boxFolderItems = folderOperations.getFolderItems("0", Arrays.asList(BoxFolderItemsFields.NAME));
        for (BoxFileMini fi: boxFolderItems.getEntries()) {
            if ("new api folder name".equals(fi.getName())) {
                fail("test folder should have been deleted");
            }
        }
    }

    private void listFolders(BoxFolderItems boxFolderItems) {
        for (BoxFileMini boxFileMini: boxFolderItems.getEntries()) {
            if (boxFileMini.getType() == BoxItemType.FOLDER) {
                System.out.println(boxFileMini.getName() + " [" + boxFileMini.getId() + "]");
            }
        }
    }
}
