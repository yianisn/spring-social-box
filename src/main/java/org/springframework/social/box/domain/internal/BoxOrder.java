package org.springframework.social.box.domain.internal;

public class BoxOrder extends BoxObject {
    private String by;
    private String direction;

    public String getBy() {
        return by;
    }
    public String getDirection() {
        return direction;
    }
}