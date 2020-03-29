package com.worldpay.web;

import com.worldpay.service.WorldPayService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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
}