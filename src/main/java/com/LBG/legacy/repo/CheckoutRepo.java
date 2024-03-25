package com.LBG.legacy.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.LBG.legacy.domain.Checkout;

public interface CheckoutRepo extends JpaRepository<Checkout, Integer> {
}
