package com.neoxenus.webnovelreader.bookmarkcollection.entity;

import com.neoxenus.webnovelreader.bookmark.entity.Bookmark;
import com.neoxenus.webnovelreader.user.entity.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
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

    Integer position;

    @ManyToMany(mappedBy = "collection")
    private List<Bookmark> bookmarks = new ArrayList<>();
}
