package com.neoxenus.webnovelreader.book.service.impl;

import com.neoxenus.webnovelreader.book.entity.Book;
import com.neoxenus.webnovelreader.book.entity.View;
import com.neoxenus.webnovelreader.book.repo.BookRepository;
import com.neoxenus.webnovelreader.book.repo.ViewRepository;
import com.neoxenus.webnovelreader.book.service.BookService;
import com.neoxenus.webnovelreader.book.service.ViewCountService;
import com.neoxenus.webnovelreader.chapter.repo.ChapterRepository;
import com.neoxenus.webnovelreader.exceptions.InvalidFormatException;
import com.neoxenus.webnovelreader.user.entity.User;
import com.neoxenus.webnovelreader.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class ViewCountServiceImpl implements ViewCountService {

    private final StringRedisTemplate redisTemplate;
    private final UserService userService;

    private final BookService bookService;
    private final ChapterRepository chapterRepository;

    private final BookRepository bookRepository;


    private final ViewRepository uniqueViewRepository;

    private static final String VIEW_KEY_PREFIX = "book:view:";

    public String getViewKey(Long bookId, Long chapterId){
        return "book:view:" + bookId + ":" + chapterId;
    }

    public void incrementViewCount(Long bookId, Long chapterId) {
        String key = getViewKey(bookId, chapterId);
        redisTemplate.opsForValue().increment(key);
    }

    @Override
    public void incrementUniqueViewCount(Long bookId) {
        User user = userService.getCurrentUser();
        if(user != null && !uniqueViewRepository.existsByBookIdAndUserId(bookId, user.getId())) {
            Book book = bookService.getBookById(bookId);
            bookRepository.incrementUniqueViewCount(bookId, 1L);
            View view = View.builder()
                    .book(book)
                    .user(user)
                    .build();
            uniqueViewRepository.save(view);
        }
    }



    private Long extractBookIdFromKey(String key) {
        if(key.startsWith(VIEW_KEY_PREFIX)){
            key = key.substring(VIEW_KEY_PREFIX.length());
            key = key.substring(0, key.indexOf(':'));
            return Long.valueOf(key);
        } else {
            throw new InvalidFormatException(
                    "Expected key that starts with " + VIEW_KEY_PREFIX);
        }
    }
    private Long extractChapterIdFromKey(String key) {
        if(key.startsWith(VIEW_KEY_PREFIX)){
            key = key.substring(VIEW_KEY_PREFIX.length());
            key = key.substring(key.indexOf(':') + 1);
            return Long.valueOf(key);
        } else {
            throw new InvalidFormatException(
                    "Expected key that starts with " + VIEW_KEY_PREFIX);
        }
    }

    @Override
    @Scheduled(fixedDelayString = "${views.schedule.fixed-rate}", initialDelayString = "${views.schedule.initial-delay}")
    @Transactional
    public void syncViewFromRedisToDb() {
        Set<String> keys = redisTemplate.keys(VIEW_KEY_PREFIX + "*");
        if (keys == null || keys.isEmpty()) return;

        for (String key : keys) {
            Long count = redisTemplate.opsForValue().increment(key, 0);
            Long bookId = extractBookIdFromKey(key);
            Long chapterId = extractChapterIdFromKey(key);
            if (count != null && count > 0) {
                bookRepository.incrementViewCount(bookId, count);
                chapterRepository.incrementViewCount(chapterId, count);
            }

            redisTemplate.opsForValue().set(key, "0");
        }
    }
}
