package org.orange.bookerz.api.service.review;

import lombok.RequiredArgsConstructor;
import org.orange.bookerz.api.controller.review.request.AddReviewRequest;
import org.orange.bookerz.api.exception.InvalidRequestException;
import org.orange.bookerz.domain.book.Book;
import org.orange.bookerz.domain.book.BookRepository;
import org.orange.bookerz.domain.member.Member;
import org.orange.bookerz.domain.member.MemberRepository;
import org.orange.bookerz.domain.review.Review;
import org.orange.bookerz.domain.review.ReviewRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final BookRepository bookRepository;
    private final MemberRepository memberRepository;

    public void addReview(AddReviewRequest request, Long memberId) {

        Book book = bookRepository.findById(request.getBookId())
                .orElseThrow(() -> new InvalidRequestException("id", "check book id"));

        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new InvalidRequestException("id", "check member id"));

        Review parentReview = null;
        if (request.getParentReviewId() != null) {
            parentReview = reviewRepository.findById(request.getParentReviewId())
                    .orElseThrow(() -> new InvalidRequestException("id", "parent review id"));
        }

        Review review = Review.builder()
                .book(book)
                .member(member)
                .score(request.getScore())
                .comment(request.getComment())
                .parentReview(parentReview)
                .build();

        reviewRepository.save(review);
    }

    public void getReviews(Long bookId) {

        reviewRepository.findByBookId(bookId);
    }

}
