package com.neoxenus.webnovelreader.user.event;

import com.neoxenus.webnovelreader.bookmarkcollection.service.BookmarkCollectionService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserEventListener {
    private final BookmarkCollectionService bookmarkCollectionService;

    @EventListener
    public void handleUserCreated(UserCreatedEvent event) {
        bookmarkCollectionService.initDefaultCollectionsForUser(event.getUser());
    }
}
