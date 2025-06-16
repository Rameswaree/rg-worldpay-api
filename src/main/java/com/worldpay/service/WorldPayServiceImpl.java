package com.worldpay.service;

import com.worldpay.entity.Offers;
import com.worldpay.repository.OffersRepository;
import com.worldpay.utils.DateUtils;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j
public class WorldPayServiceImpl implements WorldPayService {

    private static final String ACTIVE_STATUS="ACTIVE";
    private static final String EXPIRED_STATUS="EXPIRED";
    private final OffersRepository offersRepository;

    @Override
    public void addOffersByMerchant(String offer, String price, String currency, String validity, String paymentMode) {

        Offers result = setNewOffer(offer, price, currency, validity, paymentMode);
        offersRepository.save(result);
    }

    @Override
    public List<Offers> getOffersByMerchant(Optional<String> offer, Optional<String> paymentMode,
                                            Optional<String> currency, Optional<String> status) {
        List<Offers> offersListByParams = offersRepository.findByOfferIgnoreCaseOrPaymentModeIgnoreCaseOrCurrencyIgnoreCaseOrStatusIgnoreCase(offer, paymentMode, currency, status);

        List<Offers> offersList = offersRepository.findAll();
        if(offersListByParams.isEmpty()){
            log.error("Offer list is not available when querying with the given parameters");
            return offersList;
        }
        return offersListByParams;
    }

    @Override
    public void cancelOffersByMerchant(String offer) {
        Offers byOffer = offersRepository.findByOfferIgnoreCase(offer);

        if(byOffer==null){
            throw new OfferNotFoundException("The offer "+ offer + " is not present in the database for cancellation");
        }
        if(!EXPIRED_STATUS.equalsIgnoreCase(byOffer.getStatus())){
            byOffer.setEndDate(LocalDateTime.now());
            byOffer.setStatus(EXPIRED_STATUS);
            offersRepository.save(byOffer);
        }
    }

    @Override
    public void updateOffersByMerchant(String offer, String price, String endDate) {
        Offers byOffer = offersRepository.findByOfferIgnoreCase(offer);
        if(byOffer==null){
            throw new OfferNotFoundException("The offer "+ offer + " is not present in the database for updating");
        }
            try{
                LocalDateTime localDateTime = DateUtils.toLocalDateTime(endDate);
                if(localDateTime.isAfter(LocalDateTime.now())) {
                    byOffer.setEndDate(localDateTime);
                }else{
                    log.info("End date cannot be behind current business date");
                    return;
                }
                byOffer.setPrice(price);
                if(EXPIRED_STATUS.equalsIgnoreCase(byOffer.getStatus()))
                    byOffer.setStatus(ACTIVE_STATUS);
            }catch (Exception e){
                log.error("Exception occurred while updating offer: {}", String.valueOf(e));
            }
            offersRepository.save(byOffer);
    }

    private Offers setNewOffer(String offer, String price, String currency, String validity, String paymentMode) {
        Offers offers = new Offers();

        Offers byOffer = offersRepository.findByOfferIgnoreCase(offer);
        if(byOffer!=null){
            log.info("Offer {} is present in the database", byOffer.getOffer());
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
