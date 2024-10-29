package com.meli.coupon;

import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/coupon")
public class CouponController {

    private final CouponService couponService;

    @Autowired
    public CouponController(CouponService itemService) {
        this.couponService = itemService;
    }

    @PostMapping
    @RateLimiter(name = "couponLimiter")
    public ResponseEntity<CouponResponse> couponItems(@RequestBody CouponRequest request) {
        CouponResponse couponItems = couponService.calculateMaximizedCoupon(request.getItemIds(), request.getAmount());
        if (couponItems.getItem_ids().isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return new ResponseEntity<>(couponItems, HttpStatus.OK);
    }
}