package com.neoxenus.webnovelreader.chapters.services;

import com.neoxenus.webnovelreader.chapters.etitities.dtos.ChapterCreateRequest;
import com.neoxenus.webnovelreader.chapters.etitities.dtos.ChapterDto;
import com.neoxenus.webnovelreader.chapters.etitities.dtos.ChapterUpdateRequest;
import com.neoxenus.webnovelreader.exceptions.NoSuchEntityException;

import java.util.List;


public interface ChapterService {

    ChapterDto addChapter(Long bookId, ChapterCreateRequest request) throws NoSuchEntityException;
    List<ChapterDto> getBookChapters(Long bookId);
    ChapterDto getChapter(Long bookId, Long chapterId) throws NoSuchEntityException;
    ChapterDto updateChapter(Long bookId, Long chapterId, ChapterUpdateRequest request) throws NoSuchEntityException;
    void deleteChapter(Long bookId, Long chapterId) throws NoSuchEntityException;



}
