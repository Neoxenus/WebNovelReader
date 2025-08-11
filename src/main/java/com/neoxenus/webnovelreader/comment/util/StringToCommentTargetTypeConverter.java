package com.neoxenus.webnovelreader.comment.util;

import com.neoxenus.webnovelreader.comment.enums.CommentTargetType;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

@Component
public class StringToCommentTargetTypeConverter implements Converter<String, CommentTargetType> {
    @Override
    @NonNull
    public CommentTargetType convert(@NonNull String source) {
        return CommentTargetType.fromPath(source);
    }
}