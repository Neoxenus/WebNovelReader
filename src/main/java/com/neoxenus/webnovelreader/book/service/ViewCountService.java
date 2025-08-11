package com.neoxenus.webnovelreader.book.service;

public interface ViewCountService {

    void incrementViewCount(Long bookId);

    Long getViewCount(Long bookId);

    void incrementUniqueViewCount(Long bookId);

    Long getUniqueViewCount(Long bookId);
}
