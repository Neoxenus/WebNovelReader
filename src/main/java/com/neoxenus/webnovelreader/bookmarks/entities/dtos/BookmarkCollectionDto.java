package com.neoxenus.webnovelreader.bookmarks.entities.dtos;

import com.neoxenus.webnovelreader.users.entities.dtos.UserDto;

import java.util.List;


public record BookmarkCollectionDto(Long id, Boolean isDefault, Boolean isPublic, UserDto user, String name,
                                    String description, List<BookmarkDto> bookmarks) {

}
