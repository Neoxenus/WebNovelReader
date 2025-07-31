package com.neoxenus.webnovelreader.comment.service;

import com.neoxenus.webnovelreader.comment.dto.CommentDto;
import com.neoxenus.webnovelreader.comment.dto.request.VoteRequest;

public interface CommentVoteService {

    CommentDto vote(Long id, VoteRequest request);
}
