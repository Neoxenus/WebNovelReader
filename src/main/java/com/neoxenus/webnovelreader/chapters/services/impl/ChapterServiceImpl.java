package com.neoxenus.webnovelreader.chapters.services.impl;

import com.neoxenus.webnovelreader.books.entities.Book;
import com.neoxenus.webnovelreader.books.repo.BookRepository;
import com.neoxenus.webnovelreader.chapters.etitities.Chapter;
import com.neoxenus.webnovelreader.chapters.etitities.dtos.ChapterCreateRequest;
import com.neoxenus.webnovelreader.chapters.etitities.dtos.ChapterDto;
import com.neoxenus.webnovelreader.chapters.etitities.dtos.ChapterUpdateRequest;
import com.neoxenus.webnovelreader.chapters.mapper.ChapterMapper;
import com.neoxenus.webnovelreader.chapters.repo.ChapterRepository;
import com.neoxenus.webnovelreader.chapters.services.ChapterService;
import com.neoxenus.webnovelreader.exceptions.NoSuchBookException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class ChapterServiceImpl implements ChapterService {

    private final ChapterRepository chapterRepository;
    private final BookRepository bookRepository;

    private final ChapterMapper chapterMapper;

    @Override
    @Transactional
    public ChapterDto addChapter(Long bookId, ChapterCreateRequest chapterCreateRequest) throws NoSuchBookException {
        Chapter chapter = chapterMapper.toChapter(chapterCreateRequest);
        Book book = bookRepository.findById(bookId).orElseThrow(() -> new NoSuchBookException("No such book for id: " + bookId));
        chapter.setBook(book);
        return chapterMapper.toDto(
                chapterRepository.save(chapter)
        );
    }

    @Override
    public List<ChapterDto> getBookChapters(Long bookId) {
        return chapterMapper.toDto(chapterRepository.findByBookId(bookId));
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<ChapterDto> getChapter(Long bookId, Long chapterId) {
        //different checks (for example for belonging to book)?
        return chapterMapper.toDto(chapterRepository.findById(chapterId));
    }

    @Override
    @Transactional
    public ChapterDto updateChapter(Long bookId, Long chapterId, ChapterUpdateRequest chapterUpdateRequest) {
        Optional<Chapter> optionalChapter = chapterRepository.findById(chapterId);
        if(optionalChapter.isPresent()) {
            Chapter chapterById = optionalChapter.get();
            Chapter updatedChapter = chapterMapper.toChapter(chapterById, chapterUpdateRequest);
            return chapterMapper.toDto(chapterRepository.save(updatedChapter));
        } else {
            //todo: introduce custom exception
            throw new NoSuchElementException("No chapter for this id: " + chapterId);
        }
    }

    @Override
    @Transactional
    public void deleteChapter(Long bookId, Long chapterId) {
        if(chapterRepository.existsById(chapterId)){
            log.info("Deleting chapter with id: {}", chapterId);
            chapterRepository.deleteById(chapterId);
        }
        else{
            log.error("No chapter for this id: {}", chapterId);
            //todo: introduce custom exception
            throw new NoSuchElementException("No chapter for this id: " + chapterId);
        }
    }
}
