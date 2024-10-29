package com.meli.coupon;

import com.meli.item.Item;
import com.meli.item.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class CouponService {
    private final ItemService itemService;

    @Autowired
    public CouponService(ItemService itemService) {
        this.itemService = itemService;
    }
    private CouponResponse couponMaximizedItems(List<Item> itemIds, Float amount){
        List<Item> sortedItemsByPrice = itemIds.stream()
                .sorted(Comparator.comparingDouble(Item::getPrice))
                .collect(Collectors.toList());

        ArrayList<String> couponItemIds = new ArrayList<>();
        Float totalAmount = 0.0f;
        for (Item item : sortedItemsByPrice) {
            if ((totalAmount + item.getPrice()) <= amount) {
                totalAmount += item.getPrice();
                couponItemIds.add(item.getId());
            }
            else{
                break;
            }
        }
        CouponResponse couponResponse = new CouponResponse(couponItemIds, totalAmount);
        return couponResponse;
    }

    public CouponResponse calculateMaximizedCoupon(List<String> itemIds, Float amount){

        List<Item> itemsInfo = itemService.getItems(itemIds);

        return couponMaximizedItems(itemsInfo, amount);
    }
}
