package com.LBG.legacy.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.LBG.legacy.domain.Checkout;
import com.LBG.legacy.repo.CheckoutRepo;

@Service
public class CheckoutService {

	private final CheckoutRepo checkoutRepository;

	public CheckoutService(CheckoutRepo checkoutRepository) {
		this.checkoutRepository = checkoutRepository;
	}

	public List<Checkout> getAllCheckouts() {
		return checkoutRepository.findAll();
	}

	public Optional<Checkout> getCheckoutById(Integer id) {
		return checkoutRepository.findById(id);
	}

	public Checkout createCheckout(Checkout checkout) {
		return checkoutRepository.save(checkout);
	}

}
