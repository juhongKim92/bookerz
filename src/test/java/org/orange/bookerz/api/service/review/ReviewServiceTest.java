package org.orange.bookerz.api.service.review;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.orange.bookerz.api.controller.review.request.AddReviewRequest;
import org.orange.bookerz.domain.book.Book;
import org.orange.bookerz.domain.book.BookRepository;
import org.orange.bookerz.domain.member.Member;
import org.orange.bookerz.domain.member.MemberRepository;
import org.orange.bookerz.domain.review.Review;
import org.orange.bookerz.domain.review.ReviewRepository;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ReviewServiceTest {


    @Mock
    ReviewRepository reviewRepository;
    @Mock
    MemberRepository memberRepository;
    @Mock
    BookRepository bookRepository;
    @InjectMocks
    ReviewService reviewService;

    @Test
    @DisplayName("부모 리뷰 등록 성공")
    void addReviewSuccess() throws Exception{
        // given

        AddReviewRequest request = AddReviewRequest.builder()
                .bookId(1L)
                .score(5)
                .comment("Great book!")
                .parentReviewId(null)
                .build();

        Book book = Book.builder()
                .title("book")
                .build();

        Member member = Member.builder()
                .email("email")
                .build();

        Long memberId = 1L;

        when(memberRepository.findById(anyLong())).thenReturn(Optional.of(member));
        when(bookRepository.findById(anyLong())).thenReturn(Optional.of(book));
        when(reviewRepository.save(any(Review.class))).thenReturn(new Review());

        // when
        reviewService.addReview(request, memberId);

        // then
        verify(bookRepository).findById(anyLong());
        verify(memberRepository).findById(anyLong());
        verify(reviewRepository).save(any(Review.class));
    }
}