package com.neoxenus.webnovelreader.chapter.service.impl;

import com.neoxenus.webnovelreader.book.entity.Book;
import com.neoxenus.webnovelreader.book.repo.BookRepository;
import com.neoxenus.webnovelreader.book.service.ViewCountService;
import com.neoxenus.webnovelreader.chapter.dto.ChapterDto;
import com.neoxenus.webnovelreader.chapter.dto.request.ChapterCreateRequest;
import com.neoxenus.webnovelreader.chapter.dto.request.ChapterUpdateRequest;
import com.neoxenus.webnovelreader.chapter.etitity.Chapter;
import com.neoxenus.webnovelreader.chapter.mapper.ChapterMapper;
import com.neoxenus.webnovelreader.chapter.repo.ChapterRepository;
import com.neoxenus.webnovelreader.chapter.service.ChapterService;
import com.neoxenus.webnovelreader.exceptions.EntityAlreadyExistsException;
import com.neoxenus.webnovelreader.exceptions.NoSuchEntityException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class ChapterServiceImpl implements ChapterService {

    private final ChapterRepository chapterRepository;
    private final BookRepository bookRepository;

    private final ChapterMapper chapterMapper;

    private final ViewCountService viewService;

    @Override
    @Transactional
    public ChapterDto addChapter(Long bookId, ChapterCreateRequest request) throws NoSuchEntityException {
        Chapter chapter = chapterMapper.toChapter(request);
        Book book = bookRepository.findById(bookId).orElseThrow(() -> new NoSuchEntityException("No such book for id: " + bookId));
        boolean numberAlreadyExists = chapterRepository.existsByBookIdAndChapterNumber(bookId, request.chapterNumber());
        if(numberAlreadyExists) {
            throw new EntityAlreadyExistsException(
                    "Chapter with number "
                    + request.chapterNumber()
                    + " already exists for a book with id: "
                    + bookId);
        }
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
    @Transactional
    public ChapterDto getChapterDtoForView(Long bookId, Long chapterId) {
        Chapter chapter = chapterRepository.findById(chapterId)
                .orElseThrow(() -> new NoSuchEntityException("No chapter for and id: " + chapterId));

        if (!Objects.equals(chapter.getBook().getId(), bookId)) {
            throw new NoSuchEntityException("Chapter does not belong to book with id: " + bookId);
        }

        viewService.incrementViewCount(bookId, chapterId);
        viewService.incrementUniqueViewCount(bookId);

        return chapterMapper.toDto(chapter);
    }

    @Override
    public Chapter getChapter(Long bookId, Long chapterId) throws NoSuchEntityException {
        Chapter chapter = chapterRepository.findById(chapterId)
                .orElseThrow(() -> new NoSuchEntityException("No chapter for and id: " + chapterId));

        if (!Objects.equals(chapter.getBook().getId(), bookId)) {
            throw new NoSuchEntityException("Chapter does not belong to book with id: " + bookId);
        }

        return chapter;
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
            throw new NoSuchEntityException("No chapter for this id: " + chapterId);
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
            throw new NoSuchEntityException("No chapter for this id: " + chapterId);
        }
    }
}
