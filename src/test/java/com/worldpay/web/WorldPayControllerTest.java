package com.worldpay.web;

import com.worldpay.domain.Offers;
import com.worldpay.service.WorldPayService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringJUnitConfig
public class WorldPayControllerTest {

    @MockBean
    WorldPayService worldPayService;

    @InjectMocks
    WorldPayController worldPayController;

    private MockMvc mockMvc;

    @BeforeEach
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(worldPayController).build();
    }

    @Test
    void shouldCreateNewOffers() throws Exception {

        mockMvc.perform(post("/worldpay/createOffers?offer=test&price=50" +
                "&currency=INR&validity=60&paymentMode=CC")).andExpect(status().isOk());
        verify(worldPayService, times(1)).addOffersByMerchant(any(), any(), any(), any(), any());
    }

    @Test
    void shouldGetAvailableOffers() throws Exception {

        List<Offers> offersList = new ArrayList<>();
        when(worldPayService.getOffersByMerchant(any(), any(), any(), any())).thenReturn(offersList);
        mockMvc.perform(get("/worldpay/availableOffers")).andExpect(status().isOk());
    }

    @Test
    void shouldCancelOffer() throws Exception {
        mockMvc.perform(put("/worldpay/cancelOffers?offer=test")).andExpect(status().isOk());
        verify(worldPayService).cancelOffersByMerchant(any());
    }

    @Test
    void shouldUpdateOffer() throws Exception {

        mockMvc.perform(patch("/worldpay/updateOffers?offer=test&price=50&endDate=2020-03-30%2009:02:10")).andExpect(status().isOk());
        verify(worldPayService).updateOffersByMerchant(any(),any(),any());
    }
}