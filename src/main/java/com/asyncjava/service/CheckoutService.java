package com.asyncjava.service;

import com.asyncjava.domain.checkout.Cart;
import com.asyncjava.domain.checkout.CartItem;
import com.asyncjava.domain.checkout.CheckoutResponse;
import com.asyncjava.domain.checkout.CheckoutStatus;

import java.util.List;
import java.util.stream.Collectors;

import static com.asyncjava.util.CommonUtil.startTimer;
import static com.asyncjava.util.CommonUtil.timeTaken;
import static com.asyncjava.util.LoggerUtil.log;

public class CheckoutService {

    private PriceValidatorService priceValidatorService;

    public CheckoutService(PriceValidatorService priceValidatorService) {
        this.priceValidatorService = priceValidatorService;
    }

    public CheckoutResponse checkout(Cart cart){
        startTimer();
        List<CartItem> priceValidationList = cart.getCartItemList()
                .parallelStream()
                .map(cartItem -> {
                    boolean isPriceInvalid = priceValidatorService.isCartItemInvalid(cartItem);
                    cartItem.setExpired(isPriceInvalid);
                    return cartItem;
                })
                .filter(CartItem::isExpired)
                .collect(Collectors.toList());

        timeTaken();
        if(priceValidationList.size()>0){
            return new CheckoutResponse(CheckoutStatus.FAILURE, priceValidationList);
        }

        //double finalPrice = calculateFinalPrice(cart);
        double finalPrice = calculateFinalPrice_reduce(cart);
        log(" Checkout Complete and the final price is " + finalPrice);
         return new CheckoutResponse(CheckoutStatus.SUCCESS,finalPrice);
    }

    private double calculateFinalPrice(Cart cart) {

        return cart.getCartItemList()
                .parallelStream()
                .map(cartItem -> cartItem.getQuantity() * cartItem.getRate())
                .mapToDouble(Double::doubleValue)
                .sum();
    }

    private double calculateFinalPrice_reduce(Cart cart) {

        return cart.getCartItemList()
                .parallelStream()
                .map(cartItem -> cartItem.getQuantity() * cartItem.getRate())
                .reduce(0.0, Double::sum);
    }

}
