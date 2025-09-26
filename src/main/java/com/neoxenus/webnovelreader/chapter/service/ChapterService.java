package com.neoxenus.webnovelreader.chapter.service;

import com.neoxenus.webnovelreader.chapter.dto.response.ChapterDtoResponse;
import com.neoxenus.webnovelreader.chapter.dto.response.ChapterSummaryDtoResponse;
import com.neoxenus.webnovelreader.chapter.dto.request.ChapterCreateDtoRequest;
import com.neoxenus.webnovelreader.chapter.dto.request.ChapterUpdateDtoRequest;
import com.neoxenus.webnovelreader.chapter.etitity.Chapter;
import com.neoxenus.webnovelreader.exceptions.NoSuchEntityException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface ChapterService {

    ChapterDtoResponse addChapter(Long bookId, ChapterCreateDtoRequest request) throws NoSuchEntityException;
    Page<ChapterSummaryDtoResponse> getBookChapters(Long bookId, Pageable pageable);
    ChapterDtoResponse getChapterDtoForView(Long bookId, Long chapterId) throws NoSuchEntityException;
    Chapter getChapter(Long bookId, Long chapterId) throws NoSuchEntityException;

    ChapterDtoResponse updateChapter(Long bookId, Long chapterId, ChapterUpdateDtoRequest request) throws NoSuchEntityException;
    void deleteChapter(Long bookId, Long chapterId) throws NoSuchEntityException;



}
