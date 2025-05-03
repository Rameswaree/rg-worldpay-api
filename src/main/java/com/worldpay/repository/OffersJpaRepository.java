package com.worldpay.repository;

import com.worldpay.domain.Offers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OffersJpaRepository extends JpaRepository<Offers,Integer> {

    List<Offers> findByOfferIgnoreCaseOrPaymentModeIgnoreCaseOrCurrencyIgnoreCaseOrStatusIgnoreCase(Optional<String> offer, Optional<String> paymentMode,
                                                    Optional<String> currency, Optional<String> status);
    Offers findByOfferIgnoreCase(String offer);
}
