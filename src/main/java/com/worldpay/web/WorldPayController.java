package com.worldpay.web;

import com.worldpay.domain.Offers;
import com.worldpay.service.OfferNotFoundException;
import com.worldpay.service.WorldPayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * Controller class
 * @author rameswaree@gmail.com
 * Copyright 2020-2021
 */
@RestController
public class WorldPayController{

    WorldPayService worldPayService;

    @Autowired
    public WorldPayController(WorldPayService worldPayService){
        this.worldPayService = worldPayService;
    }

    @PostMapping("/worldpay/createOffers")
    public void createOffersByMerchant(@RequestParam String offer,
                                       @RequestParam String price, @RequestParam String currency,
                                       @RequestParam String validity, @RequestParam String paymentMode){

        worldPayService.addOffersByMerchant(offer, price, currency, validity, paymentMode);
    }

    @GetMapping("/worldpay/availableOffers")
    public List<Offers> availableOffersByMerchant(@RequestParam Optional<String> offer, @RequestParam Optional<String> paymentMode,
                                                  @RequestParam Optional<String> currency, @RequestParam Optional<String> status){
        return worldPayService.getOffersByMerchant(offer, paymentMode,currency, status);
    }

    @PutMapping("/worldpay/cancelOffers")
    public void cancelOffersByMerchant(@RequestParam String offer) throws OfferNotFoundException {

        worldPayService.cancelOffersByMerchant(offer);
    }

    @PatchMapping("/worldpay/updateOffers")
    public void updateOffersByMerchant(@RequestParam String offer, @RequestParam String price,
                                       @RequestParam String endDate) throws OfferNotFoundException {

       worldPayService.updateOffersByMerchant(offer, price, endDate);
    }
}