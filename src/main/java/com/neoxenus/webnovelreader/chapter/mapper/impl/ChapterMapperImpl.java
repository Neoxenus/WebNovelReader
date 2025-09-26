package com.neoxenus.webnovelreader.chapter.mapper.impl;

import com.neoxenus.webnovelreader.chapter.dto.response.ChapterDtoResponse;
import com.neoxenus.webnovelreader.chapter.dto.response.ChapterSummaryDtoResponse;
import com.neoxenus.webnovelreader.chapter.dto.request.ChapterCreateDtoRequest;
import com.neoxenus.webnovelreader.chapter.dto.request.ChapterUpdateDtoRequest;
import com.neoxenus.webnovelreader.chapter.etitity.Chapter;
import com.neoxenus.webnovelreader.chapter.etitity.ChapterContent;
import com.neoxenus.webnovelreader.chapter.mapper.ChapterMapper;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

@Component
public class ChapterMapperImpl implements ChapterMapper {
    @Override
    public ChapterDtoResponse toDto(Chapter chapter) {
        if (chapter != null) {
            return ChapterDtoResponse.builder()
                    .id(chapter.getId())
                    .title(chapter.getTitle())
                    .chapterNumber(chapter.getChapterNumber())
                    .views(chapter.getViews())
                    .datePublished(chapter.getDatePublished())
                    .bookId(chapter.getBook() != null ? chapter.getBook().getId() : null)
                    .content(chapter.getContent() != null ? chapter.getContent().getContent() : null)
                    .build();
        } else {
            return null;
        }
    }

    @Override
    public Page<ChapterDtoResponse> toDto(Page<Chapter> chapterList) {
        return chapterList.map(this::toDto);
    }

    @Override
    public Chapter toChapter(ChapterCreateDtoRequest chapterCreateDtoRequest) {
        if (chapterCreateDtoRequest != null) {
            Chapter chapter = new Chapter();
            chapter.setTitle(chapterCreateDtoRequest.title());
            chapter.setChapterNumber(chapterCreateDtoRequest.chapterNumber());
            ChapterContent content = new ChapterContent();
            content.setContent(chapterCreateDtoRequest.content());
            content.setChapter(chapter);
            chapter.setContent(content);

            return chapter;
        } else {
            return null;
        }

    }

    @Override
    public Chapter toChapter(Chapter toUpdate, ChapterUpdateDtoRequest chapterUpdateDtoRequest) {
        if (toUpdate != null && chapterUpdateDtoRequest != null) {
            toUpdate.setTitle(chapterUpdateDtoRequest.title());
            toUpdate.setChapterNumber(chapterUpdateDtoRequest.chapterNumber());
            toUpdate.getContent().setContent(chapterUpdateDtoRequest.content());
            return toUpdate;
        } else {
            return null;
        }
    }

    @Override
    public ChapterSummaryDtoResponse toSummary(Chapter chapter) {
        if (chapter != null) {
            return ChapterSummaryDtoResponse.builder()
                    .id(chapter.getId())
                    .title(chapter.getTitle())
                    .chapterNumber(chapter.getChapterNumber())
                    .views(chapter.getViews())
                    .datePublished(chapter.getDatePublished())
                    .build();
        } else {
            return null;
        }

    }

    @Override
    public Page<ChapterSummaryDtoResponse> toSummary(Page<Chapter> chapterList) {
        if(chapterList != null){
            return chapterList.map(this::toSummary);
        } else {
            return null;
        }
    }
}
