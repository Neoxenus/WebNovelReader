package com.neoxenus.webnovelreader.book.service.impl;

import com.neoxenus.webnovelreader.book.service.ViewCountService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ViewCountServiceImpl implements ViewCountService {

    private final StringRedisTemplate redisTemplate;
    private static final String VIEW_KEY_PREFIX = "book:view:";
    private static final String UNIQUE_VIEW_KEY_PREFIX = "book:uniqueView:";
    public void incrementViewCount(Long bookId) {
        String key = VIEW_KEY_PREFIX + bookId;
        redisTemplate.opsForValue().increment(key);
    }

    public Long getViewCount(Long bookId) {
        String key = VIEW_KEY_PREFIX + bookId;
        String count = redisTemplate.opsForValue().get(key);
        return count == null ? 0L : Long.parseLong(count);
    }

    @Override
    public void incrementUniqueViewCount(Long bookId) {

    }

    @Override
    public Long getUniqueViewCount(Long bookId) {
        return null;
    }
}
