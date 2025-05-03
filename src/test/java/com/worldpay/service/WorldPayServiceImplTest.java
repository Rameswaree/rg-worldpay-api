package com.worldpay.service;

import com.worldpay.domain.Offers;
import com.worldpay.repository.OffersJpaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringJUnitConfig
public class WorldPayServiceImplTest {

    @InjectMocks
    WorldPayServiceImpl worldPayService;

    @Mock
    private OffersJpaRepository offersJpaRepository;

    private Offers offers;

    private static final String OFFER="XMAS";
    private static final String PRICE="75";
    private static final String CURRENCY="GBP";
    private static final String VALIDITY="25";
    private static final String PAYMENT_MODE ="Wallet";

    @BeforeEach
    public void setUp(){
        offers = new Offers();
    }

    @Test
    void shouldStoreOffersByMerchant(){

        worldPayService.addOffersByMerchant(OFFER, PRICE, CURRENCY, VALIDITY, PAYMENT_MODE);
        verify(offersJpaRepository).findByOfferIgnoreCase(OFFER);
        verify(offersJpaRepository).save(any());
    }

    @Test
    void shouldGetOffersByMerchant(){

        when(offersJpaRepository.findByOfferIgnoreCase(OFFER)).thenReturn(offers);
        worldPayService.getOffersByMerchant(any(),any(),any(),any());
        verify(offersJpaRepository).findAll();
    }
}