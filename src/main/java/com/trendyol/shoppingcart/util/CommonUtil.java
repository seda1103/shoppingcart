package com.trendyol.shoppingcart.util;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Collections;
import java.util.Optional;
import java.util.stream.Stream;

public class CommonUtil {

	public static final BigDecimal DELIVERY_COST_FOR_CATEGORY = new BigDecimal(5);
	public static final BigDecimal DELIVERY_COST_FOR_PRODUCT = new BigDecimal(1);


	public static boolean greater(BigDecimal first, BigDecimal second) {
		return first.compareTo(second) > 0;
	}


	public static boolean lower(BigDecimal first, BigDecimal second) {
		return first.compareTo(second) < 0;
	}

	public static boolean equals(BigDecimal first, BigDecimal second) {
		return first.compareTo(second) == 0;
	}

	public static <T> Stream<T> asStream(final Collection<T> collection) {
		return Optional.ofNullable(collection).orElse(Collections.emptyList()).stream();
	}


}
