package com.neoxenus.webnovelreader.tag.util;

import com.neoxenus.webnovelreader.tag.enums.TagType;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

@Component
public class StringToTagTypeConverter implements Converter<String, TagType> {
        @Override
        @NonNull
        public TagType convert(@NonNull String source) throws IllegalArgumentException {
            return TagType.valueOf(source.toUpperCase());
    }
}
