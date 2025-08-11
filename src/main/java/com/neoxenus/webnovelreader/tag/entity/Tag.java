package com.neoxenus.webnovelreader.tag.entity;

import com.neoxenus.webnovelreader.book.entity.Book;
import com.neoxenus.webnovelreader.tag.enums.TagType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@Builder
@Table(name = "tags")
@NoArgsConstructor
@AllArgsConstructor
public class Tag {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String name;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Enumerated(EnumType.STRING)
    private TagType tagType;

    @ManyToMany(mappedBy = "tags")
    private List<Book> books;
}
