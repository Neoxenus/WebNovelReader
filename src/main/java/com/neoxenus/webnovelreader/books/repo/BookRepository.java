package com.neoxenus.webnovelreader.books.repo;

import com.neoxenus.webnovelreader.books.entities.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
}
