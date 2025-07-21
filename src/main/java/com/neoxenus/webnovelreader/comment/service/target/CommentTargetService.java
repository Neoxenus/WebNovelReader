package com.neoxenus.webnovelreader.comment.service.target;

import com.neoxenus.webnovelreader.comment.dto.request.CommentCreateRequest;
import com.neoxenus.webnovelreader.comment.dto.CommentDto;
import com.neoxenus.webnovelreader.comment.enums.CommentTargetType;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CommentTargetService {
    CommentTargetType getTargetType();

    List<CommentDto> getComments(Long targetId);
    CommentDto createComment(Long targetId, CommentCreateRequest request);


}
