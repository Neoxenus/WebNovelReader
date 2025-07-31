package com.neoxenus.webnovelreader.comment.dto.request;

import com.neoxenus.webnovelreader.comment.enums.VoteType;

public record VoteRequest(
        VoteType vote
) {
}
