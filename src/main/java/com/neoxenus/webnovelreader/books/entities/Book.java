package com.neoxenus.webnovelreader.books.entities;

import com.neoxenus.webnovelreader.chapters.etitities.Chapter;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
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

    @OneToMany(mappedBy = "book", fetch = FetchType.LAZY)
    @OrderBy("chapterNumber DESC")
    //@JsonManagedReference
    private List<Chapter> chapterList;

    @Transient
    public Integer getNumberOfChapters() {
        return chapterList == null ? 0 : chapterList.size();
    }

    /*
    status in COO(ongoing, dropped, hiatus, completed)
	status of translation
	number of chapters in original
	number of chapters Available/translated
	year of publishing
	language
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
