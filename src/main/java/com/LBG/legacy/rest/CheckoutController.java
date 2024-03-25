package com.LBG.legacy.rest;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.LBG.legacy.domain.Checkout;
import com.LBG.legacy.services.CheckoutService;

@RestController
@RequestMapping("/checkout")
public class CheckoutController {

	private final CheckoutService checkoutService;

	public CheckoutController(CheckoutService checkoutService) {
		this.checkoutService = checkoutService;
	}

	@GetMapping("/get")
	public ResponseEntity<List<Checkout>> getAllCheckouts() {
		List<Checkout> checkouts = checkoutService.getAllCheckouts();
		return new ResponseEntity<>(checkouts, HttpStatus.OK);
	}

	@GetMapping("get/{id}")
	public ResponseEntity<Checkout> getCheckoutById(@PathVariable Integer id) {
		Optional<Checkout> checkout = checkoutService.getCheckoutById(id);
		return checkout.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
				.orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}

	@PostMapping("/create")
	public ResponseEntity<Checkout> createCheckout(@RequestBody Checkout checkout) {
		Checkout createdCheckout = checkoutService.createCheckout(checkout);
		return new ResponseEntity<>(createdCheckout, HttpStatus.CREATED);
	}

}
