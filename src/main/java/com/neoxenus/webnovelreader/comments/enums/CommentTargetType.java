package com.neoxenus.webnovelreader.comments.enums;

import java.util.Arrays;

public enum CommentTargetType {
    BOOK("books"),
    CHAPTER("chapters"),

    USER("users");
    private final String path;

    CommentTargetType(String path) {
        this.path = path;
    }

    public static CommentTargetType fromPath(String path) {
        return Arrays.stream(values())
                .filter(t -> t.path.equalsIgnoreCase(path))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Unknown type: " + path));
    }
}
