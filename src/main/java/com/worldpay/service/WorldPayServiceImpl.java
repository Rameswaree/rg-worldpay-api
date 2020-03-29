package com.worldpay.service;

import com.worldpay.domain.Offers;
import com.worldpay.repository.OffersJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class WorldPayServiceImpl implements WorldPayService {

    private static final String ACTIVE_STATUS="ACTIVE";
    private static final String EXPIRED_STATUS="EXPIRED";
    private OffersJpaRepository offersJpaRepository;

    @Autowired
    public WorldPayServiceImpl(OffersJpaRepository offersJpaRepository){
        this.offersJpaRepository = offersJpaRepository;
    }

    @Override
    public void addOffersByMerchant(String offer, String price, String currency, String validity, String paymentMode) {

        Offers result = setNewOffer(offer, price, currency, validity, paymentMode);
        offersJpaRepository.save(result);
    }

    @Override
    public List<Offers> getOffersByMerchant(Optional<String> offer, Optional<String> paymentMode, Optional<String> currency) {
        List<Offers> offersListByParams = offersJpaRepository.findByOfferOrPaymentModeOrCurrency(offer, paymentMode, currency);

        List<Offers> offersList = offersJpaRepository.findAll();
        return (!offersListByParams.isEmpty()?offersListByParams:offersList);
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

    private Offers setNewOffer(String offer, String price, String currency, String validity, String paymentMode) {
        Offers offers = new Offers();

        Offers byOffer = offersJpaRepository.findByOffer(offer);
        if(byOffer!=null){
            return byOffer;
        }
        offers.setOffer(offer);
        offers.setPrice(price);
        offers.setCurrency(currency);
        offers.setStartDate(LocalDateTime.now());
        LocalDateTime startDate = offers.getStartDate();
        offers.setValidity(validity);
        offers.setEndDate(startDate.plusDays(Long.parseLong(validity)));
        offers.setStatusOffer(ACTIVE_STATUS);
        offers.setPaymentMode(paymentMode);
        return offers;
    }
}
