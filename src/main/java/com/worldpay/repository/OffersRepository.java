package com.worldpay.repository;

import com.worldpay.entity.Offers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OffersRepository extends JpaRepository<Offers,Integer> {

    List<Offers> findByOfferIgnoreCaseOrPaymentModeIgnoreCaseOrCurrencyIgnoreCaseOrStatusIgnoreCase(String offer, String paymentMode,
                                                    String currency, String status);
    Offers findByOfferIgnoreCase(String offer);
}
