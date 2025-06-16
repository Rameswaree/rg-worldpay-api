package com.worldpay.web;

import com.worldpay.entity.Offers;
import com.worldpay.service.WorldPayService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller class
 * @author rameswaree@gmail.com
 * Copyright 2020-2021
 */
@AllArgsConstructor
@RestController
@RequestMapping("/worldpay")
public class WorldPayController{

    private final WorldPayService worldPayService;

    @PostMapping("/createOffers")
    public void createOffersByMerchant(@RequestParam String offer,
                                       @RequestParam String price, @RequestParam String currency,
                                       @RequestParam String validity, @RequestParam String paymentMode){

        worldPayService.addOffersByMerchant(offer, price, currency, validity, paymentMode);
    }

    @GetMapping("/availableOffers")
    public List<Offers> availableOffersByMerchant(@RequestParam(required = false) String offer, @RequestParam(required = false) String paymentMode,
                                                  @RequestParam(required = false) String currency, @RequestParam(required = false) String status){
        return worldPayService.getOffersByMerchant(offer, paymentMode,currency, status);
    }

    @PutMapping("/cancelOffers")
    public void cancelOffersByMerchant(@RequestParam String offer) {

        worldPayService.cancelOffersByMerchant(offer);
    }

    @PatchMapping("/updateOffers")
    public void updateOffersByMerchant(@RequestParam String offer, @RequestParam String price,
                                       @RequestParam String endDate) {

       worldPayService.updateOffersByMerchant(offer, price, endDate);
    }
}