package com.worldpay.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Offers implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "offerId", nullable = false)
    private int id;

    private String offer;

    private String price;

    private String currency;

    private String validity;

    private LocalDateTime startDate;

    private LocalDateTime endDate;

    private String status;

    private String paymentMode;
}
