package org.orange.bookerz.domain.review;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.orange.bookerz.domain.BaseEntity;
import org.orange.bookerz.domain.book.Book;
import org.orange.bookerz.domain.member.Member;

@Entity
@NoArgsConstructor
public class Review extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @ManyToOne
    @JoinColumn(name = "book_id", nullable = false)
    Book book;
    @ManyToOne
    @JoinColumn(name = "member_id", nullable = false)
    Member member;
    int score;
    @Column(length = 1000)
    String comment;
    @ManyToOne
    @JoinColumn(name = "parent_review_id")
    Review parentReview;

    @Builder
    public Review(Book book, Member member, int score, String comment,Review parentReview) {
        this.book = book;
        this.member = member;
        this.score = score;
        this.comment = comment;
        this.parentReview = parentReview;
    }
    public boolean isParent() {
        return this.parentReview == null;
    }
}
