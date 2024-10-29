package com.meli.coupon;

import java.util.List;

public class CouponRequest {
    private List<String> item_ids;
    private Float amount;

    public CouponRequest(List<String> item_ids, Float amount) {
        this.item_ids = item_ids;
        this.amount = amount;
    }

    public List<String> getItemIds() {
        return item_ids;
    }

    public void setItemIds(List<String> itemIds) {
        this.item_ids = itemIds;
    }

    public Float getAmount() {
        return amount;
    }

    public void setAmount(Float amount) {
        this.amount = amount;
    }
}
