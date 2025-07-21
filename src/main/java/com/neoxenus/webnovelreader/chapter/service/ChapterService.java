package com.neoxenus.webnovelreader.chapter.service;

import com.neoxenus.webnovelreader.chapter.dto.request.ChapterCreateRequest;
import com.neoxenus.webnovelreader.chapter.dto.ChapterDto;
import com.neoxenus.webnovelreader.chapter.dto.request.ChapterUpdateRequest;
import com.neoxenus.webnovelreader.exceptions.NoSuchEntityException;

import java.util.List;


public interface ChapterService {

    ChapterDto addChapter(Long bookId, ChapterCreateRequest request) throws NoSuchEntityException;
    List<ChapterDto> getBookChapters(Long bookId);
    ChapterDto getChapter(Long bookId, Long chapterId) throws NoSuchEntityException;
    ChapterDto updateChapter(Long bookId, Long chapterId, ChapterUpdateRequest request) throws NoSuchEntityException;
    void deleteChapter(Long bookId, Long chapterId) throws NoSuchEntityException;



}
