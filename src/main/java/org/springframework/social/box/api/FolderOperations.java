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

import org.springframework.social.box.domain.BoxFolder;
import org.springframework.social.box.domain.BoxFolderItems;


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
     * @return a {@link BoxFolderItems} object
     *
     * @see <a href="https://developers.box.com/docs/#folders-retrieve-a-folders-items">https://developers.box.com/docs/#folders-retrieve-a-folders-items</a>
     */
    public BoxFolderItems getFolderItems(String folderId);

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

}
