package com.neoxenus.webnovelreader.bookmark.entity;

import com.neoxenus.webnovelreader.user.entity.User;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Data;

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

}
