package com.kubernetes.basket.repository;

import com.kubernetes.basket.entity.BasketState;
import org.springframework.cloud.gcp.data.datastore.repository.DatastoreRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BasketRepository extends DatastoreRepository<BasketState, String> {
}
