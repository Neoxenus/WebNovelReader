package com.neoxenus.webnovelreader.bookmarks.entities;

import com.neoxenus.webnovelreader.users.entities.User;
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

    private Boolean isPublic;

    @ManyToOne(optional = false)
    private User user;

    private String name;

    private String description; //null for default

}
