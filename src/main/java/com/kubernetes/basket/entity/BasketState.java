package com.kubernetes.basket.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.cloud.gcp.data.datastore.core.mapping.Entity;
import org.springframework.data.annotation.Id;

import java.util.List;

@Entity
@Getter
@Setter
public class BasketState {

    @Id
    private String userId;
    private List<BasketEntry> basketEntries;
}
