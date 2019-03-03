package com.kubernetes.basket.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BasketEntryDto {

    private String productId;
    private int quantity;
}
