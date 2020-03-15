package com.worldpay.web;

import com.worldpay.domain.Offers;
import com.worldpay.service.WorldPayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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
    public void createOffersByMerchant(@RequestParam String offer){
        worldPayService.addOffersByMerchant(offer);
    }

    @GetMapping("worldpay/availableOffers")
    public List<Offers> availableOffersByMerchant(){
        return worldPayService.getOffersByMerchant();
    }
}