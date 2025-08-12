package com.neoxenus.webnovelreader.book.service;

public interface ViewCountService {

    void incrementViewCount(Long bookId, Long chapterId);

    void incrementUniqueViewCount(Long bookId);


    void syncViewFromRedisToDb();
}
