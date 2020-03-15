package com.worldpay.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNull;

public class OffersTest {

   private Offers offers;

   private static final String VALIDITY="365";

   @BeforeEach
    void setUp(){
       offers = new Offers();
   }

   @Test
    void shouldReturnValidityWhenValidityIsSet(){
       offers.setValidity(VALIDITY);

       assertThat(offers.getValidity().equals(VALIDITY));
   }

    @Test
    void shouldReturnNullWhenValidityIsNotSet(){

        assertNull(offers.getValidity());
    }
}