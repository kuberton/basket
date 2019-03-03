package com.kubernetes.basket.service;

import com.kubernetes.basket.entity.BasketEntry;
import com.kubernetes.basket.entity.BasketState;
import com.kubernetes.basket.exception.BasketNotFoundException;
import com.kubernetes.basket.repository.BasketRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BasketService {

    private static final String BASKET_NOT_FOUND_EXCEPTION_MESSAGE = "basket was not found";

    private final BasketRepository basketRepository;

    public BasketState findBasketStateByUser(String userId) {
        return basketRepository.findById(userId)
                .orElseThrow(() -> new BasketNotFoundException(BASKET_NOT_FOUND_EXCEPTION_MESSAGE));
    }

    public void addProductToBasket(String userId, String productId, int quantity) {
        BasketState basketState = basketRepository.findById(userId).orElse(new BasketState());
        BasketEntry basketEntry = new BasketEntry();
        basketEntry.setProductId(productId);
        basketEntry.setQuantity(quantity);
        basketState.getBasketEntries().add(basketEntry);
        basketRepository.save(basketState);
    }

    public void clearBasket(String userId) {
        basketRepository.deleteById(userId);
    }
}