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
import static org.junit.Assert.assertThat;

import java.io.IOException;
import java.util.Properties;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.core.io.ClassPathResource;
import org.springframework.social.OperationNotPermittedException;
import org.springframework.social.box.connect.BoxConnectionFactory;
import org.springframework.social.connect.Connection;
import org.springframework.social.oauth2.AccessGrant;

/**
 *
 * @author Ioannis Nikolaou
 */
public class FileOperationsIT {
    static String accessToken;
    private static FileOperations fileOperations;

    @BeforeClass
    public static void oneTimeSetUp() throws IOException {
        Properties p = new Properties();
        p.load(FileOperationsIT.class.getResourceAsStream("/boxIT.properties"));
        accessToken = p.getProperty("accessToken");

        BoxConnectionFactory connectionFactory = new BoxConnectionFactory("clientId", "clientSecret");
        AccessGrant bxAccessGrant = new AccessGrant(accessToken);
        Connection<Box> connection = connectionFactory.createConnection(bxAccessGrant);
        fileOperations = connection.getApi().fileOperations();
    }

    @Test
    public void uploadFile() {
        fileOperations.uploadFile("test", "0", new ClassPathResource("unknown"));
    }

    @Test
    public void uploadEmptyFile() {
        try {
            fileOperations.uploadFile("test1", "0", null);
        } catch (Exception e) {
            assertThat(e, instanceOf(OperationNotPermittedException.class));
        }
    }

}
