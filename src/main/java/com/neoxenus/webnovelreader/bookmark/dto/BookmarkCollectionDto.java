package com.neoxenus.webnovelreader.bookmark.dto;

import com.neoxenus.webnovelreader.user.dto.UserDto;

import java.util.List;


public record BookmarkCollectionDto(
        Long id,
        Boolean isDefault,
        Boolean isPublic,
        UserDto user,
        String name,
        String description,
        List<BookmarkDto> bookmarks) {

}
