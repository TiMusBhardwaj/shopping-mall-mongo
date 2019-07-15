package com.example.mall.controller.Exception;

public class CartInactiveException extends RuntimeException {

	
	private String cartId;

    public static CartInactiveException createWith(String cartId) {
        return new CartInactiveException(cartId);
    }

    private CartInactiveException(String cartId) {
        this.cartId = cartId;
    }

    @Override
    public String getMessage() {
        return "Cart for '" + cartId + "' not found";
    }
}