package com.worldpay.service;

import org.springframework.stereotype.Repository;

@Repository
public interface WorldPayService {

    void addOffersByMerchant();

    void getOffersByMerchant();
}
