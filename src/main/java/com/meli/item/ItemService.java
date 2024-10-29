package com.meli.item;

import com.meli.rest.RestApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

@Service
public class ItemService
{
    private final RestApiService restApiService;
    @Autowired
    public ItemService(RestApiService restApiService) {
        this.restApiService = restApiService;
    }
    @Cacheable
    private Item getItem(String id) {
        return restApiService.get("/items",id,Item.class);
    }

    public List<Item> getItems(List<String> itemIds) {
        return itemIds.stream()
                .map(id -> CompletableFuture.supplyAsync(() -> getItem(id)))
                .map(CompletableFuture::join)
                .collect(Collectors.toList());
    }
}
