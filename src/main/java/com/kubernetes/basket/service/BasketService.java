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

    public BasketState findBasketStateByUser(long userId) {
        return basketRepository.findByUserId(userId).stream().findFirst().orElse(null);
    }

    public void addProductToBasket(long userId, String productId, int quantity) {
        BasketState basketState = basketRepository.findByUserId(userId).stream().findFirst().orElseGet(this::createEmptyBasket);
        BasketEntry basketEntry = new BasketEntry();
        basketEntry.setUid(userId);
        basketEntry.setProductId(productId);
        basketEntry.setQuantity(quantity);
        basketState.getBasketEntries().add(basketEntry);
        basketState.setUserId(userId);
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