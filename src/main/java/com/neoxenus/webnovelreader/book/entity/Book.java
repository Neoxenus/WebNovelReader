package com.neoxenus.webnovelreader.book.entity;

import com.neoxenus.webnovelreader.chapter.etitity.Chapter;
import com.neoxenus.webnovelreader.tag.entity.Tag;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Table(name = "books")
public class Book {

    public Book() {
        chapterList = new ArrayList<>();
    }

    @Id
    @GeneratedValue
    private Long id;

    private String title;   //{title + author} => unique?

    private String yearOfPublishing;

    private String languageOfOriginal;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    private Integer totalViews = 0;

    @OneToMany(mappedBy = "book")
    private List<BookRating> bookRatings;
    @OneToMany(mappedBy = "book")
    @OrderBy("chapterNumber DESC")
    private List<Chapter> chapterList;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "book_tag_link",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id")
    )
    private List<Tag> tags;

//    @Transient
//    public Integer getNumberOfChapters() {
//        return chapterList == null ? 0 : chapterList.size();
//    }

    /*
    status in COO(ongoing, dropped, hiatus, completed)
	status of translation
	number of chapters in original
	number of chapters Available/translated
	authors
	translators -> new entity (id, name)
	publishers -> new entity (id, name)
	updated_at


	Views:  (unique views)
	Total views: (all chapters)

	Comments: (for book)
	Total comments: (all chapters)


     */
}
