package org.springframework.social.box;

import org.springframework.social.box.domain.BoxFolderItems;
import org.springframework.social.box.domain.internal.BoxFileMini;

public abstract class BoxIT {

    protected Boolean containsItem(String itemId, BoxFolderItems boxFolderItems) {
        for (BoxFileMini boxFileMini: boxFolderItems.getEntries()) {
            if (itemId.equals(boxFileMini.getId())) {
                return true;
            }
        }
        return false;
    }

}
