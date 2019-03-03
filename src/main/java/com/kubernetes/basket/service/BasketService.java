package com.kubernetes.basket.service;

import com.kubernetes.basket.entity.BasketEntry;
import com.kubernetes.basket.entity.BasketState;
import com.kubernetes.basket.repository.BasketRepository;
import lombok.RequiredArgsConstructor;
import org.apache.commons.math3.random.RandomDataGenerator;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class BasketService {

    private static final String BASKET_NOT_FOUND_EXCEPTION_MESSAGE = "basket was not found";

    private final BasketRepository basketRepository;

    public BasketState findBasketStateByUser(String userId) {
        return basketRepository.findByUserId(Long.valueOf(userId)).stream().findFirst().orElse(null);
    }

    public void addProductToBasket(String userId, String productId, int quantity) {
        BasketState basketState = basketRepository.findByUserId(Long.valueOf(userId)).stream().findFirst().orElseGet(this::createEmptyBasket);
        BasketEntry basketEntry = new BasketEntry();
        basketEntry.setUid(new RandomDataGenerator().nextLong(Long.MIN_VALUE, Long.MAX_VALUE));
        basketEntry.setProductId(productId);
        basketEntry.setQuantity(quantity);
        basketState.getBasketEntries().add(basketEntry);
        basketState.setUserId(new RandomDataGenerator().nextLong(Long.MIN_VALUE, Long.MAX_VALUE));
        basketRepository.save(basketState);
    }

    public void clearBasket(String userId) {
        basketRepository.deleteById(userId);
    }

    public BasketState createEmptyBasket() {
        BasketState basketState = new BasketState();
        basketState.setBasketEntries(new ArrayList<>());
        return basketState;
    }
}