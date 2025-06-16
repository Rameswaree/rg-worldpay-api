package com.worldpay.service;

import com.worldpay.entity.Offers;

import java.util.List;

public interface WorldPayService {

    void addOffersByMerchant(String offer, String price, String currency, String validity, String paymentMode);

    List<Offers> getOffersByMerchant(String offer, String paymentMode,
                                     String currency, String status);

    void cancelOffersByMerchant(String offer);

    void updateOffersByMerchant(String offer, String price, String endDate);
}
