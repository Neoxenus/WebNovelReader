package com.neoxenus.webnovelreader.book.repo;

import com.neoxenus.webnovelreader.book.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    @Modifying
    @Query("UPDATE Book b SET b.totalViews = b.totalViews + 1 WHERE b.id = :id")
    void incrementViewCount(@Param("id") Long id);
}
