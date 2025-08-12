package com.neoxenus.webnovelreader.book.repo;

import com.neoxenus.webnovelreader.book.entity.View;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ViewRepository extends JpaRepository<View, Long> {
    boolean existsByBookIdAndUserId(Long bookId, Long userId);
}
