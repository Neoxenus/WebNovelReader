package com.neoxenus.webnovelreader.chapters.services.impl;

import com.neoxenus.webnovelreader.chapters.etitities.dtos.ChapterCreateRequest;
import com.neoxenus.webnovelreader.chapters.etitities.dtos.ChapterDto;
import com.neoxenus.webnovelreader.chapters.etitities.dtos.ChapterUpdateRequest;
import com.neoxenus.webnovelreader.chapters.services.ChapterService;

import java.util.List;
import java.util.Optional;

public class ChapterServiceImpl implements ChapterService {
    @Override
    public ChapterDto addChapter(Long bookId, ChapterCreateRequest chapterCreateRequest) {
        return null;
    }

    @Override
    public List<ChapterDto> getBookChapters(Long bookId) {
        return null;
    }

    @Override
    public Optional<ChapterDto> getChapter(Long bookId, Long chapterId) {
        return Optional.empty();
    }

    @Override
    public ChapterDto updateChapter(Long bookId, Long chapterId, ChapterUpdateRequest chapterUpdateRequest) {
        return null;
    }

    @Override
    public void deleteChapter(Long bookId, Long chapterId) {

    }
}
