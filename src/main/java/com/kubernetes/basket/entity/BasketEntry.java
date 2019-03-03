package com.kubernetes.basket.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.cloud.gcp.data.datastore.core.mapping.Entity;
import org.springframework.data.annotation.Id;

@Getter
@Setter
@Entity
public class BasketEntry {

    @Id
    private String uid;
    private String productId;
    private int quantity;
}
