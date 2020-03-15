package com.worldpay.service;

import com.worldpay.domain.Offers;
import com.worldpay.repository.OffersJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class WorldPayServiceImpl implements WorldPayService {

    public static final int VALIDITY=60;

    @Autowired
    private OffersJpaRepository offersJpaRepository;

    @Override
    public void addOffersByMerchant(String offer) {

        Offers result = setNewOffer(offer);
        offersJpaRepository.save(result);
    }

    @Override
    public List<Offers> getOffersByMerchant() {
        return offersJpaRepository.findAll();
    }

    private Offers setNewOffer(String offer) {
        Offers offers = new Offers();

        offers.setOffer(offer);
        offers.setStartDate(LocalDateTime.now());
        offers.setValidity(VALIDITY);
        offers.setEndDate(offers.getStartDate().plusMonths(VALIDITY));
        return offers;
    }
}
