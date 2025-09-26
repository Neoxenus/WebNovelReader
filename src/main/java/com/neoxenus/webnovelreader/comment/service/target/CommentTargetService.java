package com.neoxenus.webnovelreader.comment.service.target;

import com.neoxenus.webnovelreader.comment.dto.request.CommentCreateDtoRequest;
import com.neoxenus.webnovelreader.comment.dto.response.CommentDtoResponse;
import com.neoxenus.webnovelreader.comment.enums.CommentTargetType;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CommentTargetService {
    CommentTargetType getTargetType();

    List<CommentDtoResponse> getComments(Long targetId);
    CommentDtoResponse createComment(Long targetId, CommentCreateDtoRequest request);


}
