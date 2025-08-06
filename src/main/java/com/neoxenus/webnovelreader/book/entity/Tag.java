package com.neoxenus.webnovelreader.book.entity;

import com.neoxenus.webnovelreader.book.enums.TagType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Builder
@Table(name = "tags")
@NoArgsConstructor
@AllArgsConstructor
public class Tag {

    public Tag(String name, String description, TagType tagType) {
        this.name = name;
        this.description = description;
        this.tagType = tagType;
        this.books = new ArrayList<>();
    }
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String description;

    @Enumerated(EnumType.STRING)
    private TagType tagType;

    @ManyToMany
    @JoinTable(
            name = "book_tag_link",
            joinColumns = @JoinColumn(name = "tags_id"),
            inverseJoinColumns = @JoinColumn(name = "books_id")
    )
    private List<Book> books;
}
