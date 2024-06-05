package org.orange.bookerz.api.controller.review.request;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.orange.bookerz.domain.review.Review;

@Getter
@NoArgsConstructor
public class AddReviewRequest {

    Long bookId;
    int score;
    String comment;
    Long parentReviewId;

    @Builder
    public AddReviewRequest(Long bookId, int score, String comment, Long parentReviewId) {
        this.bookId = bookId;
        this.score = score;
        this.comment = comment;
        this.parentReviewId = parentReviewId;
    }
}
