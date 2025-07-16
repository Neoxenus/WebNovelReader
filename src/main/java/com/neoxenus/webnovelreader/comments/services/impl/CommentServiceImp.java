package com.neoxenus.webnovelreader.comments.services.impl;

import com.neoxenus.webnovelreader.comments.entities.dtos.CommentCreateRequest;
import com.neoxenus.webnovelreader.comments.entities.dtos.CommentDto;
import com.neoxenus.webnovelreader.comments.entities.dtos.CommentUpdateRequest;
import com.neoxenus.webnovelreader.comments.enums.CommentTargetType;
import com.neoxenus.webnovelreader.comments.services.CommentService;
import com.neoxenus.webnovelreader.comments.services.target.CommentTargetServiceFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentServiceImp implements CommentService {
    private final CommentTargetServiceFactory factory;

    @Override
    public CommentDto updateComment(Long commentId, CommentUpdateRequest request) {
        return null;
    }

    @Override
    public void deleteComment(Long commentId) {

    }

    @Override
    public List<CommentDto> getComments(CommentTargetType type, Long targetId) {
        return factory.getService(type).getComments(targetId);
    }

    @Override
    public CommentDto createComment(CommentTargetType type, Long targetId, CommentCreateRequest request) {
        return factory.getService(type).createComment(targetId, request);
    }

}
