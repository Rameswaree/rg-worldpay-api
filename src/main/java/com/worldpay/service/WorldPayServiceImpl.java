package com.worldpay.service;

import com.worldpay.domain.Offers;
import com.worldpay.repository.OffersJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class WorldPayServiceImpl implements WorldPayService {

    public static final String VALIDITY="60";

    private OffersJpaRepository offersJpaRepository;

    @Autowired
    public WorldPayServiceImpl(OffersJpaRepository offersJpaRepository){
        this.offersJpaRepository = offersJpaRepository;
    }

    @Override
    public void addOffersByMerchant(String offer, String price) {

        Offers result = setNewOffer(offer, price);
        offersJpaRepository.save(result);
    }

    @Override
    public List<Offers> getOffersByMerchant() {
        return offersJpaRepository.findAll();
    }

    private Offers setNewOffer(String offer, String price) {
        Offers offers = new Offers();

        offers.setOffer(offer);
        offers.setPrice(price);
        offers.setStartDate(LocalDateTime.now());
        offers.setValidity(VALIDITY);
        offers.setEndDate(offers.getStartDate().plusMonths(Long.parseLong(VALIDITY)));
        return offers;
    }
}
