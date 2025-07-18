package com.neoxenus.webnovelreader.comments.util;

import com.neoxenus.webnovelreader.comments.enums.CommentTargetType;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class StringToCommentTargetTypeConverter implements Converter<String, CommentTargetType> {
    @Override
    public CommentTargetType convert(String source) {
        return CommentTargetType.fromPath(source);
    }
}