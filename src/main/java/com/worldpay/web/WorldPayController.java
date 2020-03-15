package com.worldpay.web;

import com.worldpay.service.WorldPayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
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

    @PostMapping("/worldpay/offers")
    public void createOffersByMerchant(){
        worldPayService.addOffersByMerchant();
    }
}