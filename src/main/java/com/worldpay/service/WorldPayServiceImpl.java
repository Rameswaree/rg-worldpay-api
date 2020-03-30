package com.worldpay.service;

import com.worldpay.domain.Offers;
import com.worldpay.repository.OffersJpaRepository;
import com.worldpay.utils.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class WorldPayServiceImpl implements WorldPayService {

    private static final Logger LOGGER = LoggerFactory.getLogger(WorldPayServiceImpl.class);
    public static final String ACTIVE_STATUS="ACTIVE";
    public static final String EXPIRED_STATUS="EXPIRED";
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
    public List<Offers> getOffersByMerchant(Optional<String> offer, Optional<String> paymentMode,
                                            Optional<String> currency, Optional<String> status) {
        List<Offers> offersListByParams = offersJpaRepository.findByOfferIgnoreCaseOrPaymentModeIgnoreCaseOrCurrencyIgnoreCaseOrStatusIgnoreCase(offer, paymentMode, currency, status);

        List<Offers> offersList = offersJpaRepository.findAll();
        if(offersListByParams.isEmpty()){
            LOGGER.error("Offer list is not available when querying with the given parameters");
            return offersList;
        }
        return offersListByParams;
    }

    @Override
    public void cancelOffersByMerchant(String offer) throws OfferNotFoundException{
        Offers byOffer = offersJpaRepository.findByOfferIgnoreCase(offer);

        if(byOffer==null){
            throw new OfferNotFoundException("The offer "+ offer + " is not present in the database for cancellation");
        }
        if(!EXPIRED_STATUS.equalsIgnoreCase(byOffer.getStatus())){
            byOffer.setEndDate(LocalDateTime.now());
            byOffer.setStatus(EXPIRED_STATUS);
            offersJpaRepository.save(byOffer);
        }
    }

    @Override
    public void updateOffersByMerchant(String offer, String price, String endDate) throws OfferNotFoundException{
        Offers byOffer = offersJpaRepository.findByOfferIgnoreCase(offer);
        if(byOffer==null){
            throw new OfferNotFoundException("The offer "+ offer + " is not present in the database for updation");
        }
            try{
                LocalDateTime localDateTime = DateUtils.toLocalDateTime(endDate);
                if(localDateTime.isAfter(LocalDateTime.now())) {
                    byOffer.setEndDate(localDateTime);
                }else{
                    LOGGER.info("End date cannot be behind current business date");
                    return;
                }
                byOffer.setPrice(price);
                if(EXPIRED_STATUS.equalsIgnoreCase(byOffer.getStatus()))
                    byOffer.setStatus(ACTIVE_STATUS);
            }catch (Exception e){
                LOGGER.error("Exception occurred during offer updation: " + e);
            }
            offersJpaRepository.save(byOffer);
    }

    private Offers setNewOffer(String offer, String price, String currency, String validity, String paymentMode) {
        Offers offers = new Offers();

        Offers byOffer = offersJpaRepository.findByOfferIgnoreCase(offer);
        if(byOffer!=null){
            LOGGER.info("Offer " + byOffer.getOffer() + " is present in the database");
            return byOffer;
        }
        offers.setOffer(offer);
        offers.setPrice(price);
        offers.setCurrency(currency);
        offers.setStartDate(LocalDateTime.now());
        LocalDateTime startDate = offers.getStartDate();
        offers.setValidity(validity);
        offers.setEndDate(startDate.plusDays(Long.parseLong(validity)));
        offers.setStatus(ACTIVE_STATUS);
        offers.setPaymentMode(paymentMode);
        return offers;
    }
}
