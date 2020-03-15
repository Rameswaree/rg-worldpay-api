package com.worldpay.service;

import com.worldpay.domain.Offers;
import com.worldpay.repository.OffersJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class WorldPayServiceImpl implements WorldPayService {

    private static final String ACTIVE_STATUS="ACTIVE";
    private static final String EXPIRED_STATUS="EXPIRED";
    private static final String CURRENCY="GBP";
    private OffersJpaRepository offersJpaRepository;

    @Autowired
    public WorldPayServiceImpl(OffersJpaRepository offersJpaRepository){
        this.offersJpaRepository = offersJpaRepository;
    }

    @Override
    public void addOffersByMerchant(String offer, String price, String validity) {

        Offers result = setNewOffer(offer, price, validity);
        offersJpaRepository.save(result);
    }

    @Override
    public List<Offers> getOffersByMerchant() {
        return offersJpaRepository.findAll();
    }

    @Override
    public void cancelOffersByMerchant(String offer) {
        Offers byOffer = offersJpaRepository.findByOffer(offer);

        if(!EXPIRED_STATUS.equalsIgnoreCase(byOffer.getStatusOffer())){
            byOffer.setEndDate(LocalDateTime.now());
            byOffer.setStatusOffer(EXPIRED_STATUS);
            offersJpaRepository.save(byOffer);
        }
    }

    private Offers setNewOffer(String offer, String price, String validity) {
        Offers offers = new Offers();

        Offers byOffer = offersJpaRepository.findByOffer(offer);
        if(byOffer!=null){
            return byOffer;
        }
        offers.setOffer(offer);
        offers.setPrice(price);
        offers.setCurrency(CURRENCY);
        offers.setStartDate(LocalDateTime.now());
        offers.setValidity(validity);
        offers.setEndDate(offers.getStartDate().plusDays(Long.parseLong(validity)));
        offers.setStatusOffer(ACTIVE_STATUS);
        return offers;
    }
}
