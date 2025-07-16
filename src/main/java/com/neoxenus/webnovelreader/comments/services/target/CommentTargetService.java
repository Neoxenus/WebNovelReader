package com.neoxenus.webnovelreader.comments.services.target;

import com.neoxenus.webnovelreader.comments.entities.dtos.CommentCreateRequest;
import com.neoxenus.webnovelreader.comments.entities.dtos.CommentDto;
import com.neoxenus.webnovelreader.comments.enums.CommentTargetType;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CommentTargetService {
    CommentTargetType getTargetType();

    List<CommentDto> getComments(Long targetId);
    CommentDto createComment(Long targetId, CommentCreateRequest request);


}
