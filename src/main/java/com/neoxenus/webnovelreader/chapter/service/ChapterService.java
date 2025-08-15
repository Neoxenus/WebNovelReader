package com.neoxenus.webnovelreader.chapter.service;

import com.neoxenus.webnovelreader.chapter.dto.ChapterDto;
import com.neoxenus.webnovelreader.chapter.dto.ChapterSummary;
import com.neoxenus.webnovelreader.chapter.dto.request.ChapterCreateRequest;
import com.neoxenus.webnovelreader.chapter.dto.request.ChapterUpdateRequest;
import com.neoxenus.webnovelreader.chapter.etitity.Chapter;
import com.neoxenus.webnovelreader.exceptions.NoSuchEntityException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface ChapterService {

    ChapterDto addChapter(Long bookId, ChapterCreateRequest request) throws NoSuchEntityException;
    Page<ChapterSummary> getBookChapters(Long bookId, Pageable pageable);
    ChapterDto getChapterDtoForView(Long bookId, Long chapterId) throws NoSuchEntityException;
    Chapter getChapter(Long bookId, Long chapterId) throws NoSuchEntityException;

    ChapterDto updateChapter(Long bookId, Long chapterId, ChapterUpdateRequest request) throws NoSuchEntityException;
    void deleteChapter(Long bookId, Long chapterId) throws NoSuchEntityException;



}
