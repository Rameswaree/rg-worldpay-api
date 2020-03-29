package com.worldpay.service;

import com.worldpay.domain.Offers;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface WorldPayService {

    void addOffersByMerchant(String offer, String price, String currency, String validity, String paymentMode);

    List<Offers> getOffersByMerchant(Optional<String> offer, Optional<String> paymentMode, Optional<String> currency);

    void cancelOffersByMerchant(String offer);
}
