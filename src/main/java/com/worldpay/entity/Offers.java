package com.worldpay.entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Builder
@Getter
@Setter
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
