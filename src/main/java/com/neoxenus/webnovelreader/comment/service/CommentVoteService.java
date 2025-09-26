package com.neoxenus.webnovelreader.comment.service;

import com.neoxenus.webnovelreader.comment.dto.response.CommentDtoResponse;
import com.neoxenus.webnovelreader.comment.dto.request.VoteDtoRequest;

public interface CommentVoteService {

    CommentDtoResponse vote(Long id, VoteDtoRequest request);
}
