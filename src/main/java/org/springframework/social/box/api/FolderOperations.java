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

import java.util.List;

import org.springframework.social.box.api.UserOperations.BoxUserFields;
import org.springframework.social.box.domain.BoxFolder;
import org.springframework.social.box.domain.BoxFolderItems;
import org.springframework.social.box.domain.BoxUser;


/**
 * Folders contain information about the items contained inside of them,
 * including files and other folders. There is also a set of metadata such as
 * who owns the folder and when it was modified that is also returned. When
 * accessing other resources that make reference to folders, a ‘mini folder’
 * object will be used.
 *
 * https://developers.box.com/docs/#folders
 *
 * @author Ioannis Nikolaou
 */
public interface FolderOperations {

    /**
     * Retrieves the files and/or folders contained within this folder without
     * any other metadata about the folder. For each item the mini format is
     * returned. The id of the root folder is 0.
     *
     * @param folderId
     *            the folder id
     *
     * @return a {@link BoxFolderItems} object
     *
     * @see <a
     *      href="https://developers.box.com/docs/#folders-retrieve-a-folders-items">https://developers.box.com/docs/#folders-retrieve-a-folders-items</a>
     */
    public BoxFolderItems getFolderItems(String folderId);

    /**
     * Retrieves a subset of the information of the files and/or folders
     * contained within this folder without any other metadata about the folder.
     * For each item the mini format is returned. The id of the root folder is
     * 0.
     *
     * @param folderId
     *            the folder id
     * @param fields
     *            A list of the fields of the user information data that will be
     *            returned.
     *
     * @return a {@link BoxFolderItems} object
     *
     * @see <a
     *      href="https://developers.box.com/docs/#folders-retrieve-a-folders-items">https://developers.box.com/docs/#folders-retrieve-a-folders-items</a>
     */
    public BoxFolderItems getFolderItems(String folderId, List<BoxFolderItemsFields> fields);

    /**
     * Used to create a new empty folder. The new folder will be created inside
     * of the specified parent folder
     *
     * @param name
     *            The desired name for the folder
     * @param parentId
     *            The ID of the parent folder
     * @return A {@link BoxFolder} is returned if the parent folder ID is valid and if no name collisions occur.
     *
     * @see <a href="https://developers.box.com/docs/#folders-create-a-new-folder">https://developers.box.com/docs/#folders-create-a-new-folder</a>
     */
    public BoxFolder createFolder(String name, String parentId);

    /**
     * Used to create a new empty folder. The new folder will be created inside
     * of the specified parent folder. The return object will contain only the
     * specified fields.
     *
     * @param name
     *            The desired name for the folder
     * @param parentId
     *            The ID of the parent folder
     * @return A {@link BoxFolder} is returned if the parent folder ID is valid
     *         and if no name collisions occur.
     *
     * @see <a
     *      href="https://developers.box.com/docs/#folders-create-a-new-folder">https://developers.box.com/docs/#folders-create-a-new-folder</a>
     */
    public BoxFolder createFolder(String name, String parentId, List<BoxFolderFields> fields);

    /**
     * The available fields that can be used to define the subset of the folder
     * information data ({@link BoxFolder}) that will be retrieved from box.
     *
     * @author Ioannis Nikolaou
     */
    public enum BoxFolderFields {
        /**
         * For folders is ‘folder’
         */
        TYPE,
        /**
         * The folder’s ID.
         */
        ID,
        /**
         * A unique ID for use with the /events endpoint. May be null for some
         * folders such as root or trash.
         */
        SEQUENCE_ID,
        /**
         * A unique string identifying the version of this folder. May be null
         * for some folders such as root or trash.
         */
        ETAG,
        /**
         * The name of the folder.
         */
        NAME,
        /**
         * The time the folder was created. May be null for some folders such as
         * root or trash.
         */
        CREATED_AT,
        /**
         * The time the folder or its contents were last modified. May be null
         * for some folders such as root or trash.
         */
        MODIFIED_AT,
        /**
         * The description of the folder.
         */
        DESCRIPTION,
        /**
         * The folder size in bytes. Be careful parsing this integer, it can easily go into EE notation.
         *
         *
         *@see <a href="http://en.wikipedia.org/wiki/Double-precision_floating-point_format">IEEE754 format</a>
         */
        SIZE,
        /**
         * The path of folders to this item, starting at the root.
         */
        PATH_COLLECTION,
        /**
         * The user who created this folder.
         */
        CREATED_BY,
        /**
         * The user who last modified this folder.
         */
        MODIFIED_BY,
        /**
         * The time the folder or its contents were put in the trash. May be
         * null for some folders such as root or trash.
         */
        TRASHED_AT,
        /**
         * The time the folder or its contents were put in the trash. May be
         * null for some folders such as root or trash.
         */
        PURGED_AT,
        /**
         * The time the folder or its contents were originally created
         * (according to the uploader). May be null for some folders such as
         * root or trash.
         */
        CONTENT_CREATED_AT,
        /**
         * The time the folder or its contents were last modified (according to
         * the uploader). May be null for some folders such as root or trash.
         */
        CONTENT_MODIFIED_AT,
        /**
         * The user who owns this folder.
         */
        OWNED_BY,
        /**
         * The shared link for this folder. Null if not set.
         */
        SHARED_LINK,
        /**
         * The upload email address for this folder. Null if not set.
         */
        FOLDER_UPLOAD_EMAIL,
        /**
         * The folder that contains this one. May be null for folders such as
         * root, trash and child folders whose parent is inaccessible.
         */
        PARENT,
        /**
         * Whether this item is deleted or not.
         */
        ITEM_STATUS,
        /**
         * A collection of mini file and folder objects contained in this folder.
         */
        ITEM_COLLECTION,
        /**
         * Whether this folder will be synced by the Box sync clients or not.
         * Can be synced, not_synced, or partially_synced.
         */
        SYNC_STATE,
        /**
         * Whether this folder has any collaborators.
         */
        HAS_COLLABORATIONS,
        /**
         * The permissions that the current user has on this folder. The keys
         * are can_download, can_upload, can_rename, can_delete, can_share,
         * can_invite_collaborator, and can_set_share_access. Each value is a
         * boolean.
         */
        PERMISSIONS,
        /**
         * All tags applied to this folder.
         */
        TAGS,
        /**
         * Whether non-owners can invite collaborators to this folder.
         */
        CAN_NON_OWNERS_INVITE
    }

    /**
     * The available fields (union of {@link BoxFolderFields} and {@link BoxFileFields} fields) that can be used to define the subset of the folder items
     * information data ({@link BoxFolderItems}) that will be retrieved from box.
     *
     * @author Ioannis Nikolaou
     */
    public enum BoxFolderItemsFields {
        /**
         * For file/folders is ‘file/folder’
         */
        TYPE,
        /**
         * The file or file/folder’s ID.
         */
        ID,
        /**
         * A unique ID for use with the /events endpoint. May be null for some
         * file/folders such as root or trash.
         */
        SEQUENCE_ID,
        /**
         * A unique string identifying the version of this file/folder. May be null
         * for some file/folders such as root or trash.
         */
        ETAG,
        /**
         * The name of the file/file/folder.
         */
        NAME,
        /**
         * The time the file/folder was created. May be null for some file/folders such as
         * root or trash.
         */
        CREATED_AT,
        /**
         * The time the file/folder or its contents were last modified. May be null
         * for some file/folders such as root or trash.
         */
        MODIFIED_AT,
        /**
         * The description of the file/folder.
         */
        DESCRIPTION,
        /**
         * The file/folder size in bytes. Be careful parsing this integer, it can easily go into EE notation.
         *
         *
         *@see <a href="http://en.wikipedia.org/wiki/Double-precision_floating-point_format">IEEE754 format</a>
         */
        SIZE,
        /**
         * The path of folders to this item, starting at the root.
         */
        PATH_COLLECTION,
        /**
         * The user who created this file/folder.
         */
        CREATED_BY,
        /**
         * The user who last modified this file/folder.
         */
        MODIFIED_BY,
        /**
         * The time the file/folder or its contents were put in the trash. May be
         * null for some file/folders such as root or trash.
         */
        TRASHED_AT,
        /**
         * The time the file/folder or its contents were put in the trash. May be
         * null for some file/folders such as root or trash.
         */
        PURGED_AT,
        /**
         * The time the file/folder or its contents were originally created
         * (according to the uploader). May be null for some file/folders such as
         * root or trash.
         */
        CONTENT_CREATED_AT,
        /**
         * The time the file/folder or its contents were last modified (according to
         * the uploader). May be null for some file/folders such as root or trash.
         */
        CONTENT_MODIFIED_AT,
        /**
         * The user who owns this file/folder.
         */
        OWNED_BY,
        /**
         * The shared link for this file/folder. Null if not set.
         */
        SHARED_LINK,
        /**
         * The upload email address for this folder. Null if not set.
         */
        FOLDER_UPLOAD_EMAIL,
        /**
         * The folder that contains this one. May be null for folders such as
         * root, trash and child folders whose parent is inaccessible.
         */
        PARENT,
        /**
         * Whether this item is deleted or not.
         */
        ITEM_STATUS,
        /**
         * A collection of mini file and folder objects contained in this folder.
         */
        ITEM_COLLECTION,
        /**
         * Whether this folder will be synced by the Box sync clients or not.
         * Can be synced, not_synced, or partially_synced.
         */
        SYNC_STATE,
        /**
         * Whether this folder has any collaborators.
         */
        HAS_COLLABORATIONS,
        /**
         * The permissions that the current user has on this file/folder. The keys
         * are can_download, can_upload, can_rename, can_delete, can_share,
         * can_invite_collaborator, and can_set_share_access. Each value is a
         * boolean.
         */
        PERMISSIONS,
        /**
         * All tags applied to this file/folder.
         */
        TAGS,
        /**
         * Whether non-owners can invite collaborators to this folder.
         */
        CAN_NON_OWNERS_INVITE,
        /**
         * The sha1 hash of this file.
         */
        SHA1,
        /**
         * The version of the file.
         */
        VERSION_NUMBER,
        /**
         * The number of comments on a file.
         */
        COMMENT_COUNT,
        /**
         * The lock held on the file.
         */
        LOCK,
        /**
         * Whether the file is a package. Used for Mac Packages used by iWorks.
         */
        IS_PACKAGE
    }
}
