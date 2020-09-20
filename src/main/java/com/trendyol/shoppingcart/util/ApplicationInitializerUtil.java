package com.trendyol.shoppingcart.util;

import com.trendyol.shoppingcart.model.Campaign;
import com.trendyol.shoppingcart.model.Category;
import com.trendyol.shoppingcart.model.Coupon;
import com.trendyol.shoppingcart.model.Product;
import com.trendyol.shoppingcart.model.ShoppingCart;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class ApplicationInitializerUtil {

	public static List<Product> productList = new ArrayList<>();
	public static List<Category> categoryList = new ArrayList<>();
	public static List<Campaign> campaignList = new ArrayList<>();
	public static List<Coupon> couponList = new ArrayList<>();
	public static Category clothe;
	public static Category dress;
	public static Category xyz;
	public static Category food;
	public static Category apple;

	public static Product polo;
	public static Product strapless;
	public static Product zestar;
	public static Product washington;

	public static Campaign appleCampaign;
	public static Campaign foodCampaign;

	public static Coupon couponTen;
	public static Coupon couponTwenty;
	public static Coupon couponThousand;

	public static ShoppingCart shoppingCart = new ShoppingCart();

	@PostConstruct
	public static void init() {
		categoryList = new ArrayList<>();
		productList = new ArrayList<>();
		campaignList = new ArrayList<>();
		couponList = new ArrayList<>();

		//initalize categories
		clothe = Category.builder().parentCategory(null).title("clothe").id(UUIDGeneratorUtil.getUUID()).build();
		dress = Category.builder().parentCategory(clothe).title("dress").id(UUIDGeneratorUtil.getUUID()).build();

		xyz = Category.builder().parentCategory(null).title("xyz").id(UUIDGeneratorUtil.getUUID()).build();
		food = Category.builder().parentCategory(xyz).title("food").id(UUIDGeneratorUtil.getUUID()).build();
		apple = Category.builder().parentCategory(food).title("apple").id(UUIDGeneratorUtil.getUUID()).build();

		categoryList.add(clothe);
		categoryList.add(dress);
		categoryList.add(food);
		categoryList.add(apple);

		//initalize products
		polo = Product.builder().category(dress).title("polo dress").price(new BigDecimal(100)).id(UUIDGeneratorUtil.getUUID()).build();
		strapless = Product.builder().category(dress).title("strapless dress").price(new BigDecimal(50)).id(UUIDGeneratorUtil.getUUID()).build();

		zestar = Product.builder().category(apple).title("zestar apple").price(new BigDecimal(10)).id(UUIDGeneratorUtil.getUUID()).build();
		washington = Product.builder().category(apple).title("washington dress").price(new BigDecimal(5)).id(UUIDGeneratorUtil.getUUID()).build();

		productList.add(polo);
		productList.add(strapless);
		productList.add(zestar);
		productList.add(washington);

		//initalize campaign
		foodCampaign =  Campaign.builder().id(UUIDGeneratorUtil.getUUID()).discountRatio(BigDecimal.TEN).category(food).build();
		appleCampaign =  Campaign.builder().id(UUIDGeneratorUtil.getUUID()).discountRatio(new BigDecimal(30)).category(xyz).build();


		campaignList.add(foodCampaign);
		campaignList.add(appleCampaign);


		//initalize coupon
		couponTen = Coupon.builder().discountAmount(BigDecimal.TEN).minimumCartAmount(new BigDecimal(100)).id(UUIDGeneratorUtil.getUUID()).build();
		couponTwenty = Coupon.builder().discountAmount(new BigDecimal(20)).minimumCartAmount(new BigDecimal(200)).id(UUIDGeneratorUtil.getUUID()).build();
		couponThousand = Coupon.builder().discountAmount(new BigDecimal(50)).minimumCartAmount(new BigDecimal(20000)).id(UUIDGeneratorUtil.getUUID()).build();

		couponList.add(couponTen);
		couponList.add(couponTwenty);
		couponList.add(couponThousand);
	}

}
