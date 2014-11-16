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
package org.springframework.social.box.domain;

import org.springframework.social.box.domain.internal.BoxEnterpriseMini;
import org.springframework.social.box.domain.internal.BoxFileMini;
import org.springframework.social.box.domain.internal.BoxFilePermissions;
import org.springframework.social.box.domain.internal.BoxFileSystemObject;
import org.springframework.social.box.domain.internal.BoxFolderPermissions;
import org.springframework.social.box.domain.internal.BoxFolderUploadEmail;
import org.springframework.social.box.domain.internal.BoxIdentifiableObject;
import org.springframework.social.box.domain.internal.BoxItemObject;
import org.springframework.social.box.domain.internal.BoxLock;
import org.springframework.social.box.domain.internal.BoxOrder;
import org.springframework.social.box.domain.internal.BoxPathCollection;
import org.springframework.social.box.domain.internal.BoxPermissionsObject;
import org.springframework.social.box.domain.internal.BoxSharedLink;
import org.springframework.social.box.domain.internal.BoxUserMini;
import org.springframework.social.box.domain.internal.json.BoxEnterpriseMiniMixin;
import org.springframework.social.box.domain.internal.json.BoxFileMiniMixin;
import org.springframework.social.box.domain.internal.json.BoxFilePermissionsMixin;
import org.springframework.social.box.domain.internal.json.BoxFileSystemObjectMixin;
import org.springframework.social.box.domain.internal.json.BoxFolderPermissionsMixin;
import org.springframework.social.box.domain.internal.json.BoxFolderUploadEmailMixin;
import org.springframework.social.box.domain.internal.json.BoxIdentifiableObjectMixin;
import org.springframework.social.box.domain.internal.json.BoxItemObjectMixin;
import org.springframework.social.box.domain.internal.json.BoxLockMixin;
import org.springframework.social.box.domain.internal.json.BoxOrderMixin;
import org.springframework.social.box.domain.internal.json.BoxPathCollectionMixin;
import org.springframework.social.box.domain.internal.json.BoxPermissionsObjectMixin;
import org.springframework.social.box.domain.internal.json.BoxSharedLinkMixin;
import org.springframework.social.box.domain.internal.json.BoxUserMiniMixin;
import org.springframework.social.box.domain.json.BoxFileMixin;
import org.springframework.social.box.domain.json.BoxFolderItemsMixin;
import org.springframework.social.box.domain.json.BoxFolderMixin;
import org.springframework.social.box.domain.json.BoxUserMixin;

import com.fasterxml.jackson.databind.module.SimpleModule;

/**
 *
 * @author Ioannis Nikolaou
 */
public class BoxModule extends SimpleModule {

    private static final long serialVersionUID = 1L;

    public BoxModule() {
        super("BoxModule");
    }

    @Override
    public void setupModule(SetupContext context) {
        context.setMixInAnnotations(BoxUserMini.class, BoxUserMiniMixin.class);
        context.setMixInAnnotations(BoxEnterpriseMini.class, BoxEnterpriseMiniMixin.class);
        context.setMixInAnnotations(BoxFileMini.class, BoxFileMiniMixin.class);
        context.setMixInAnnotations(BoxFilePermissions.class, BoxFilePermissionsMixin.class);
        context.setMixInAnnotations(BoxFileSystemObject.class, BoxFileSystemObjectMixin.class);
        //BoxFolderMini
        context.setMixInAnnotations(BoxFolderPermissions.class, BoxFolderPermissionsMixin.class);
        context.setMixInAnnotations(BoxFolderUploadEmail.class, BoxFolderUploadEmailMixin.class);
        context.setMixInAnnotations(BoxIdentifiableObject.class, BoxIdentifiableObjectMixin.class);
        context.setMixInAnnotations(BoxItemObject.class, BoxItemObjectMixin.class);
        context.setMixInAnnotations(BoxLock.class, BoxLockMixin.class);
        //BoxObject
        context.setMixInAnnotations(BoxOrder.class, BoxOrderMixin.class);
        context.setMixInAnnotations(BoxPathCollection.class, BoxPathCollectionMixin.class);
        context.setMixInAnnotations(BoxPermissionsObject.class, BoxPermissionsObjectMixin.class);
        context.setMixInAnnotations(BoxSharedLink.class, BoxSharedLinkMixin.class);
        context.setMixInAnnotations(BoxUserMini.class, BoxUserMiniMixin.class);

        context.setMixInAnnotations(BoxFolderItems.class, BoxFolderItemsMixin.class);
        context.setMixInAnnotations(BoxFile.class, BoxFileMixin.class);
        context.setMixInAnnotations(BoxFolder.class, BoxFolderMixin.class);
        context.setMixInAnnotations(BoxUser.class, BoxUserMixin.class);
    }

}
