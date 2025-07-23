package com.neoxenus.webnovelreader.bookmark.dto;

import com.neoxenus.webnovelreader.user.dto.UserDto;


public record BookmarkCollectionDto(
        Long id,
        Boolean isDefault,
        Boolean isPublic,
        UserDto user,
        String name,
        String description,
        Integer position,
        Integer count
) {

}
