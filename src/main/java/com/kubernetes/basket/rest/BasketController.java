package com.kubernetes.basket.rest;

import com.kubernetes.basket.dto.BasketEntryDto;
import com.kubernetes.basket.entity.BasketState;
import com.kubernetes.basket.service.BasketService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/basket")
@RequiredArgsConstructor
public class BasketController {

    private final BasketService basketService;

    @GetMapping
    public BasketState getBasketStateForUserId(@RequestHeader("X-Auth") String userId) {
        return basketService.findBasketStateByUser(userId);
    }

    @PutMapping
    public void addProductToBasket(@RequestBody BasketEntryDto basketEntry, @RequestHeader("X-Auth") String userId) {
        basketService.addProductToBasket(userId, basketEntry.getProductId(), basketEntry.getQuantity());
    }

    @DeleteMapping
    public void clearBasketForUser(@RequestHeader("X-Auth") String userId) {
        basketService.clearBasket(userId);
    }
}
