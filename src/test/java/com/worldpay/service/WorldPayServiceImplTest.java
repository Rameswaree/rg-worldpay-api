package com.worldpay.service;

import com.worldpay.domain.Offers;
import com.worldpay.repository.OffersJpaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringJUnitConfig
public class WorldPayServiceImplTest {

    WorldPayServiceImpl worldPayService;

    @Mock
    private OffersJpaRepository offersJpaRepository;

    @Mock
    private Offers offers;

    private static final String OFFER="XMAS";
    private static final String PRICE="75";
    private static final String CURRENCY="GBP";
    private static final String VALIDITY="25";
    private static final String PAYMENTMODE="Wallet";

    @BeforeEach
    public void setUp(){
        MockitoAnnotations.initMocks(this);

        worldPayService = new WorldPayServiceImpl(offersJpaRepository);

        when(offers.getStatus()).thenReturn(WorldPayServiceImpl.ACTIVE_STATUS);
    }

    @Test
    void shouldStoreOffersByMerchant(){

        worldPayService.addOffersByMerchant(OFFER, PRICE, CURRENCY, VALIDITY, PAYMENTMODE);
        verify(offersJpaRepository).findByOfferIgnoreCase(OFFER);
        verify(offersJpaRepository).save(any());
    }
}