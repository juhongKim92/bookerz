package org.orange.bookerz.api.service.review.response;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class ReviewResponse {

    private Long id;
    private Long bookId;
    private String comment;
    private int score;
    private String memberName;
    private List<ReviewResponse> replies;

    @Builder
    public ReviewResponse(Long id, Long bookId, String comment, int score, String memberName, List<ReviewResponse> replies) {
        this.id = id;
        this.bookId = bookId;
        this.comment = comment;
        this.score = score;
        this.memberName = memberName;
        this.replies = replies;
    }
}
