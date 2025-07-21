package com.neoxenus.webnovelreader.comment.service.target;

import com.neoxenus.webnovelreader.comment.enums.CommentTargetType;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class CommentTargetServiceFactory {
    private final Map<CommentTargetType, CommentTargetService> serviceMap;

    public CommentTargetServiceFactory(List<CommentTargetService> services) {
        this.serviceMap = services.stream().collect(Collectors.toMap(
            CommentTargetService::getTargetType,
            Function.identity()
        ));
    }

    public CommentTargetService getService(CommentTargetType type) {
        return serviceMap.get(type);
    }
}
