package com.neoxenus.webnovelreader.bookmark.entity;

import com.neoxenus.webnovelreader.user.entity.User;
import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class BookmarkCollection {
    @Id
    @GeneratedValue
    private Long id;

    private Boolean isDefault;

    private Boolean isPublic; //default collections is false by default

    @ManyToOne(optional = false)
    private User user;

    private String name;

    private String description; //null for default

    @ManyToMany(mappedBy = "collection")
    private List<Bookmark> bookmarks = new ArrayList<>();
}
