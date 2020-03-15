package com.worldpay.repository;

import com.worldpay.domain.Offers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OffersJpaRepository extends JpaRepository<Offers,Integer> {

    Offers save(Offers offers);
    List<Offers> findAll();
}
