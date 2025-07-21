package com.neoxenus.webnovelreader.chapter.etitity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class ChapterContent {
    @Id
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    private Chapter chapter;

    @Lob
    private String content;
}