package org.orange.bookerz.api.controller.review;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.orange.bookerz.api.controller.review.request.AddReviewRequest;
import org.orange.bookerz.api.service.review.ReviewService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class ReviewControllerV1 {

    private final ReviewService reviewService;

    @PostMapping("/review")
    public ResponseEntity<String> addReview(@RequestBody @Valid AddReviewRequest request, @AuthenticationPrincipal User user) {

        long memberId = Long.parseLong(user.getUsername());
        reviewService.addReview(request, memberId);
        return ResponseEntity.ok().body("review is registered.");
    }

    @GetMapping("/reviews/{bookId}")
    public void getReviews(@PathVariable Long bookId) {


    }
}
