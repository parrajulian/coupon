package com.meli.coupon;

import java.util.List;

public class CouponResponse {
    private List<String> item_ids;
    private Float total;

    public CouponResponse(List<String> item_ids, Float total) {
        this.item_ids = item_ids;
        this.total = total;
    }

    public List<String> getItem_ids() {
        return item_ids;
    }

    public void setItemIds(List<String> item_ids) {
        this.item_ids = item_ids;
    }

    public Float getTotal() {
        return total;
    }

    public void setTotal(Float total) {
        this.total = total;
    }
}
