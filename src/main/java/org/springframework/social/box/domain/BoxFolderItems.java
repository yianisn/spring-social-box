package org.springframework.social.box.domain;

import java.util.List;

import org.springframework.social.box.domain.internal.BoxFileMini;
import org.springframework.social.box.domain.internal.BoxObject;
import org.springframework.social.box.domain.internal.BoxOrder;

public class BoxFolderItems extends BoxObject {
    private Integer totalCount;
    private Integer offset;
    private Integer limit;
    private List<BoxFileMini> entries;
    private List<BoxOrder> order;

    public Integer getTotalCount() {
        return totalCount;
    }
    public Integer getOffset() {
        return offset;
    }
    public Integer getLimit() {
        return limit;
    }
    public List<BoxFileMini> getEntries() {
        return entries;
    }
    public List<BoxOrder> getOrder() {
        return order;
    }
}