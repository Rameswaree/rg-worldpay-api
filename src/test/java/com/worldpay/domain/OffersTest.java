package com.worldpay.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class OffersTest {

   private Offers offers;

   private static final String VALIDITY="365";
   private static final String OFFER="NEW_YEAR";

   @BeforeEach
    void setUp(){
       offers = new Offers();
   }

   @Test
    void shouldReturnValidityWhenValidityIsSet(){
       offers.setValidity(VALIDITY);

       assertEquals(VALIDITY, offers.getValidity());
   }

    @Test
    void shouldReturnNullWhenValidityIsNotSet(){

        assertNull(offers.getValidity());
    }


    @Test
    void shouldReturnOfferWhenOfferIsSet(){
        offers.setOffer(OFFER);

        assertEquals(OFFER, offers.getOffer());
    }

    @Test
    void shouldReturnNullWhenOfferIsNotSet(){

        assertNull(offers.getOffer());
    }
}