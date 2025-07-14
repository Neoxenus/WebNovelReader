package com.neoxenus.webnovelreader.chapters.services;

import com.neoxenus.webnovelreader.chapters.etitities.dtos.ChapterCreateRequest;
import com.neoxenus.webnovelreader.chapters.etitities.dtos.ChapterDto;
import com.neoxenus.webnovelreader.chapters.etitities.dtos.ChapterUpdateRequest;
import com.neoxenus.webnovelreader.exceptions.NoSuchEntityException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface ChapterService {

    ChapterDto addChapter(Long bookId, ChapterCreateRequest chapterCreateRequest) throws NoSuchEntityException;
    List<ChapterDto> getBookChapters(Long bookId);
    Optional<ChapterDto> getChapter(Long bookId, Long chapterId);
    ChapterDto updateChapter(Long bookId, Long chapterId, ChapterUpdateRequest chapterUpdateRequest);
    void deleteChapter(Long bookId, Long chapterId);



}
