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
