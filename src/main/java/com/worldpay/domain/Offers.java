package com.worldpay.domain;

import org.springframework.core.style.ToStringCreator;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "tblOffers")
public class Offers implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "intOfferId", nullable = false)
    private int id;

    @Column(name = "vchOffer")
    private String offer;

    @Column(name = "price")
    private String price;

    @Column(name="currency")
    private String currency;

    @Column(name = "validity")
    private String validity;

    @Column(name = "dtmStartDate")
    private LocalDateTime startDate;

    @Column(name = "dtmEndDate")
    private LocalDateTime endDate;

    @Column(name="status")
    private String statusOffer;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOffer() {
        return offer;
    }

    public void setOffer(String offer) {
        this.offer = offer;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getValidity() {
        return validity;
    }

    public void setValidity(String validity) {
        this.validity = validity;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }

    public String getStatusOffer() {
        return statusOffer;
    }

    public void setStatusOffer(String statusOffer) {
        this.statusOffer = statusOffer;
    }

    @Override
    public String toString() {
        return new ToStringCreator(this)
                .append("id" , id)
                .append("offer", offer)
                .append("price" , price)
                .append("currency", currency)
                .append("validity",  validity)
                .append("startDate", startDate)
                .append("endDate" , endDate)
                .append("status", statusOffer)
                .toString();
    }
}
