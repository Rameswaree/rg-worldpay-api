package com.worldpay.service;

import com.worldpay.domain.Offers;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WorldPayService {

    void addOffersByMerchant(String offer, String price);

    List<Offers> getOffersByMerchant();
}
