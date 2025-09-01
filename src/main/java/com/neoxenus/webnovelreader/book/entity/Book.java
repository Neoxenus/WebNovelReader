package com.neoxenus.webnovelreader.book.entity;

import com.neoxenus.webnovelreader.chapter.etitity.Chapter;
import com.neoxenus.webnovelreader.comment.entity.Comment;
import com.neoxenus.webnovelreader.tag.entity.Tag;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.DoubleStream;

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

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    private Long totalViews = 0L;
    private Long uniqueViews = 0L;

    private Long ratingCount = 0L;
    private Double avgStoryDevelopment = 0d;
    private Double avgWritingQuality = 0d;
    private Double avgWorldBackground = 0d;
    private Double avgCharacterDesign = 0d;
    private Double avgRating = 0d;

    @OneToMany(mappedBy = "book", cascade = CascadeType.REMOVE)
    private List<BookRating> bookRatings;
    @OneToMany(mappedBy = "book", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    private List<Chapter> chapterList;

    @OneToMany(mappedBy = "book", cascade = CascadeType.REMOVE)
    private List<Comment> commentList;

    @OneToMany(mappedBy = "book", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    private List<View> viewList;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "book_tag_link",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id")
    )
    private List<Tag> tags;


    public void updateAverage(int[] newRatings, BookRating oldRating) {
        double[] averages = {
                avgStoryDevelopment,
                avgWritingQuality,
                avgWorldBackground,
                avgCharacterDesign
        };

        if(oldRating == null) {
            updateAverage(newRatings);
            return;
        }

        int[] oldValues =
                new int[]{
                oldRating.getStoryDevelopment(),
                oldRating.getWritingQuality(),
                oldRating.getWorldBackground(),
                oldRating.getCharacterDesign()
        };

        for (int i = 0; i < averages.length; i++) {
            averages[i] += (newRatings[i] - oldValues[i]) / (double) ratingCount;
        }
        avgRating = DoubleStream.of(averages).average().orElse(0d);

        avgStoryDevelopment = averages[0];
        avgWritingQuality = averages[1];
        avgWorldBackground = averages[2];
        avgCharacterDesign = averages[3];
    }

    public void updateAverage(int[] newRatings) {
        double[] averages = {
                avgStoryDevelopment,
                avgWritingQuality,
                avgWorldBackground,
                avgCharacterDesign
        };

        for (int i = 0; i < averages.length; i++) {
            averages[i] = computeNewAvg(averages[i], newRatings[i], this.ratingCount);
        }
        this.ratingCount++;

        avgRating = DoubleStream.of(averages).average().orElse(0d);

        avgStoryDevelopment = averages[0];
        avgWritingQuality = averages[1];
        avgWorldBackground = averages[2];
        avgCharacterDesign = averages[3];
    }

    private double computeNewAvg(Double currentAvg, int newValue, long currentCount) {
        return (currentAvg * currentCount + newValue) / (currentCount + 1);
    }

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
