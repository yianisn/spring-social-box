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

import org.springframework.core.io.Resource;
import org.springframework.social.box.domain.BoxFile;


/**
 * File objects represent the metadata about individual files in Box, with
 * attributes describing who created the file, when it was last modified, and
 * other information.
 *
 * https://developers.box.com/docs/#files
 *
 * @author Ioannis Nikolaou
 */
public interface FileOperations {

    /**
     * Used to retrieve the metadata about a file.
     *
     * @param fileId
     *            The id of the file
     * @return A {@link BoxFil} object
     */
    public BoxFile getFileInformation(String fileId);

    /**
     *
     * Retrieves s subset of the metadata about a file.
     *
     * @param fileId
     *            The id of the file
     * @param fields
     *            A list of the fields of the file information data that will be
     *            returned.
     * @return A {@link BoxFil} object
     */
    public BoxFile getFileInformation(String fileId, List<BoxFileFields> fields);

    /**
     * Used to update information about the file. To leave an attribute
     * unchanged set it to null.
     *
     * @param fileId
     *            The ID of the file to be updated
     * @param newName
     *            The new name of the file
     * @return The updated {@link BoxFile} is returned if no name collisions
     *         occur.
     */
    public BoxFile updateFileName(String fileId, String newName);

    /**
     * Used to update information about the file. To leave an attribute
     * unchanged set it to null.
     *
     * @param fileId
     *            The ID of the file to be updated
     * @param newDescription
     *            The description name of the file
     * @return The updated {@link BoxFile} is returned
     */
    public BoxFile updateFileDescription(String fileId, String newDescription);

    /**
     * Used to update information about the file. To leave an attribute
     * unchanged set it to null.
     *
     * @param fileId
     *            The ID of the file to be updated
     * @param newTags
     *            The new tags of the file
     * @return The updated {@link BoxFile} is returned
     */
    public BoxFile updateFileTags(String fileId, List<String> newTags);

    /**
     * Used to update information about the file. To leave an attribute
     * unchanged set it to null.
     *
     * @param fileId
     *            The ID of the file to be updated
     * @param newName
     *            The new name of the file
     * @param newDescription
     *            The description name of the file
     * @param newTags
     *            The new tags of the file
     * @param fields
     *            The list of the fields of the file information data that will be
     *            returned.
     * @return The updated {@link BoxFile} is returned if no name collisions
     *         occur.
     */
    public BoxFile updateFile(String fileId, String newName, String newDescription, List<String> newTags, List<BoxFileFields> fields);

    /**
     * Use the Uploads API to allow users to add a new file. The user can then
     * upload a file by specifying the destination folder for the file. If the
     * user provides a file name that already exists in the destination folder,
     * the user will receive an error.
     *
     * @param name
     *            The file name
     * @param parentId
     *            The id of the parent folder
     * @param file
     *            The file as a Spring IO {@link Resource}
     * @return A {@link BoxFile} is returned if no name collisions occur.
     */
    public BoxFile uploadFile(String name, String parentId, Resource file);

    /**
     * Use the Uploads API to allow users to add a new file. The user can then
     * upload a file by specifying the destination folder for the file. If the
     * user provides a file name that already exists in the destination folder,
     * the user will receive an error.
     *
     * @param name
     *            The file name
     * @param parentId
     *            The id of the parent folder
     * @param file
     *            The file as a Spring IO {@link Resource}
     * @param fields
     *            The list of the fields of the file information data that will
     *            be returned. *
     * @return A {@link BoxFile} is returned if no name collisions occur.
     */
    public BoxFile uploadFile(String name, String parentId, Resource file, List<BoxFileFields> fields);

    /**
     * Used to move a file.
     *
     * @param fileId
     *            The id of the file to be moved
     * @param newParentFolderId
     *            The id of the target folder
     * @return The updated {@link BoxFile} object
     */
    public BoxFile moveFile(String fileId, String newParentFolderId);

    /**
     * Used to move a file.
     *
     * @param fileId
     *            The id of the file to be moved
     * @param newParentFolderId
     *            The id of the target folder
     * @param fields
     *            The list of the fields of the {@link BoxFile} object that will be
     *            returned.
     * @return The updated {@link BoxFile} object
     */
    public BoxFile moveFile(String fileId, String newParentFolderId, List<BoxFileFields> fields);

    /**
     * Discards a file to the trash.
     *
     * @param fileId
     *            The ID of the folder
     */
    public void deleteFile(String fileId);

    /**
     * The available fields that can be used to define the subset of the file
     * information data ({@link BoxFile}) that will be retrieved from box.
     *
     * @author Ioannis Nikolaou
     */
    public enum BoxFileFields {
        /**
         * For file is ‘file’
         */
        TYPE,
        /**
         * The file or file’s ID.
         */
        ID,
        /**
         * A unique ID for use with the /events endpoint. May be null for some
         * file such as root or trash.
         */
        SEQUENCE_ID,
        /**
         * A unique string identifying the version of this file. May be null
         * for some file such as root or trash.
         */
        ETAG,
        /**
         * The sha1 hash of this file.
         */
        SHA1,
        /**
         * The name of the file.
         */
        NAME,
        /**
         * The description of the file.
         */
        DESCRIPTION,
        /**
         * The file size in bytes. Be careful parsing this integer, it can easily go into EE notation.
         *
         *@see <a href="http://en.wikipedia.org/wiki/Double-precision_floating-point_format">IEEE754 format</a>
         */
        SIZE,
        /**
         * The path of folders to this item, starting at the root.
         */
        PATH_COLLECTION,
        /**
         * The time the file was created. May be null for some file such as
         * root or trash.
         */
        CREATED_AT,
        /**
         * The time the file or its contents were last modified. May be null
         * for some file such as root or trash.
         */
        MODIFIED_AT,
        /**
         * The time the file or its contents were put in the trash. May be
         * null for some file such as root or trash.
         */
        TRASHED_AT,
        /**
         * The time the file or its contents were put in the trash. May be
         * null for some file such as root or trash.
         */
        PURGED_AT,
        /**
         * The time the file or its contents were originally created
         * (according to the uploader). May be null for some file such as
         * root or trash.
         */
        CONTENT_CREATED_AT,
        /**
         * The time the file or its contents were last modified (according to
         * the uploader). May be null for some file such as root or trash.
         */
        CONTENT_MODIFIED_AT,
        /**
         * The user who created this file.
         */
        CREATED_BY,
        /**
         * The user who last modified this file.
         */
        MODIFIED_BY,
        /**
         * The user who owns this file.
         */
        OWNED_BY,
        /**
         * The shared link for this file. Null if not set.
         */
        SHARED_LINK,
        /**
         * The folder that contains this one.
         */
        PARENT,
        /**
         * Whether this item is deleted or not.
         */
        ITEM_STATUS,
        /**
         * The version of the file.
         */
        VERSION_NUMBER,
        /**
         * The number of comments on a file.
         */
        COMMENT_COUNT,
        /**
         * The permissions that the current user has on this file. The keys
         * are can_download, can_upload, can_rename, can_delete, can_share,
         * can_invite_collaborator, and can_set_share_access. Each value is a
         * boolean.
         */
        PERMISSIONS,
        /**
         * All tags applied to this file.
         */
        TAGS,

        /**
         * The lock held on the file.
         */
        LOCK,
        /**
         * Indicates the suffix, when available, on the file. By default, set to
         * an empty string. The suffix usually indicates the encoding (file
         * format) of the file contents or usage.
         */
        EXTENSION,
        /**
         * Whether the file is a package. Used for Mac Packages used by iWorks.
         */
        IS_PACKAGE
    }

}
