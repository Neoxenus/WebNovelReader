package com.neoxenus.webnovelreader.book.entity;

import com.neoxenus.webnovelreader.user.entity.User;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class BookRating {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    private Book book;

    @ManyToOne
    private User user;

    private Integer storyDevelopment;
    private Integer writingQuality;
    private Integer worldBackground;
    private Integer characterDesign;
}
