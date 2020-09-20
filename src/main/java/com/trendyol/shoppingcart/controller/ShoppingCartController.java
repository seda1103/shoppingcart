package com.trendyol.shoppingcart.controller;

import com.trendyol.shoppingcart.model.Product;
import com.trendyol.shoppingcart.model.ShoppingCart;
import com.trendyol.shoppingcart.service.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.math.BigDecimal;

@RestController
@RequestMapping ("shoppingcartservice")
public class ShoppingCartController {

	@Autowired
	private ShoppingCartService shoppingCartService;

	@PostMapping ("/additem/{quantity}")
	public ShoppingCart addItem(@Valid @RequestBody Product product, @PathVariable("quantity") String quantity) {
		return shoppingCartService.addItem(product, new BigDecimal(quantity));
	}

	@PostMapping ("/removeitem/{quantity}")
	public ShoppingCart removeItem(@Valid @RequestBody Product product, @PathVariable("quantity") String quantity) {
		return shoppingCartService.removeItem(product, new BigDecimal(quantity));
	}

	@GetMapping ("/totalamount")
	public BigDecimal getTotalAmount() {
		return shoppingCartService.getTotalAmount();
	}

	@GetMapping ("/deliveryamount")
	public BigDecimal getDeliveryAmount() {
		return shoppingCartService.getDeliveryAmount();
	}

	@GetMapping ("/paymentamount")
	public BigDecimal getPaymentAmount() {
		return shoppingCartService.getPaymentAmount();
	}

	@GetMapping ("/couponamount")
	public BigDecimal getCouponAmount() {
		return shoppingCartService.getCouponAmount();
	}
}
