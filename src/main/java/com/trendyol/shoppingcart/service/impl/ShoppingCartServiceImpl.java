package com.trendyol.shoppingcart.service.impl;

import com.trendyol.shoppingcart.exception.ProductDoesNotExistToRemoveException;
import com.trendyol.shoppingcart.exception.ProductDoesNotHaveEnoughQuantityToRemoveException;
import com.trendyol.shoppingcart.model.Product;
import com.trendyol.shoppingcart.model.ShoppingCart;
import com.trendyol.shoppingcart.model.helper.ProductTotalPrice;
import com.trendyol.shoppingcart.service.CampaignService;
import com.trendyol.shoppingcart.service.CouponService;
import com.trendyol.shoppingcart.service.DeliveryCostService;
import com.trendyol.shoppingcart.service.ShoppingCartService;
import com.trendyol.shoppingcart.util.ApplicationInitializerUtil;
import com.trendyol.shoppingcart.util.CommonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

/**
 * for cart operations
 */
@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {

	@Autowired
	private CampaignService campaignService;
	@Autowired
	private CouponService couponService;

	@Autowired
	private DeliveryCostService deliveryCostService;

	/**
	 * to add new product in shopping cart
	 * for this method it was assumed that campaigns does not change during this cart operations
	 * @param product this is to be added item
	 * @param quantity number of products
	 * @return
	 */
	@Override
	public ShoppingCart addItem(Product product, BigDecimal quantity) {
		if(ApplicationInitializerUtil.shoppingCart.getProductMap().containsKey(product)){
			ProductTotalPrice productTotalPrice = ApplicationInitializerUtil.shoppingCart.getProductMap().get(product);
			productTotalPrice.setQuantity(productTotalPrice.getQuantity().add(quantity));
			ApplicationInitializerUtil.shoppingCart.getProductMap().put(product,productTotalPrice);
		}else{
			ProductTotalPrice productTotalPrice = new ProductTotalPrice();
			productTotalPrice.setPrice(product.getPrice());
			productTotalPrice.setQuantity(quantity);
			productTotalPrice.setCampaignDiscountRatio(campaignService.getCampaignByCategory(product.getCategory()));
			ApplicationInitializerUtil.shoppingCart.getProductMap().put(product,productTotalPrice);
		}
		return ApplicationInitializerUtil.shoppingCart;
	}

	/**
	 * to remove item from cart
	 * It is compared quantity in shopping cart and demanding quantity
	 * @param product
	 * @param quantity
	 */
	@Override
	public ShoppingCart removeItem(Product product, BigDecimal quantity) {
		if(ApplicationInitializerUtil.shoppingCart.getProductMap().containsKey(product)){
			ProductTotalPrice productTotalPrice = ApplicationInitializerUtil.shoppingCart.getProductMap().get(product);
			if(CommonUtil.greater(productTotalPrice.getQuantity(),quantity)){
				productTotalPrice.setQuantity(productTotalPrice.getQuantity().subtract(quantity));
			}else if (CommonUtil.equals(productTotalPrice.getQuantity(),quantity)){
				ApplicationInitializerUtil.shoppingCart.getProductMap().remove(product);
			}else{
				throw new ProductDoesNotHaveEnoughQuantityToRemoveException(product.getTitle());
			}
		}else{
			throw new ProductDoesNotExistToRemoveException(product.getTitle());
		}
		return ApplicationInitializerUtil.shoppingCart;
	}

	/**
	 * It is calculated that total amount in shopping card without coupon discount and delivery cost
	 * @return
	 */
	@Autowired
	public BigDecimal getTotalAmount() {
		return CommonUtil.asStream(ApplicationInitializerUtil.shoppingCart.getProductMap().values()).map(ProductTotalPrice::getTotalPrice).reduce(BigDecimal.ZERO,BigDecimal::add);
	}

	/**
	 * It is calculated delivery amount according to number of products and number of categories
	 * for this calculation I got helped from other candidates repositories on github. Because I didn't get it
	 * @return
	 */
	@Override
	public BigDecimal getDeliveryAmount() {
		return deliveryCostService.getDeliveryCost(ApplicationInitializerUtil.shoppingCart);
	}

	/**
	 * It is calculated coupon amount
 	 * @return
	 */
	@Override
	public BigDecimal getCouponAmount() {
		return couponService.getDiscountAmount(getTotalAmount());
	}

	/**
	 * It is calculated payment amount for customer
	 * @return
	 */
	@Override
	public BigDecimal getPaymentAmount() {
		return getTotalAmount().subtract(getCouponAmount()).add(getDeliveryAmount());
	}




}
