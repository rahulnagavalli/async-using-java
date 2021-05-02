package com.asyncjava.service;

import com.asyncjava.domain.Review;

import static com.asyncjava.util.CommonUtil.delay;

public class ReviewService {

    public Review retrieveReviews(String productId) {
        delay(1000);
        return new Review(200, 4.5);
    }
}
