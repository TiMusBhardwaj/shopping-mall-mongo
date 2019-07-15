package com.example.mall.controller.Exception;

public class ProductUnavailableException extends RuntimeException {

	
	private String productId;

    public static ProductUnavailableException createWith(String productId) {
        return new ProductUnavailableException(productId);
    }

    private ProductUnavailableException(String productId) {
        this.productId = productId;
    }

    @Override
    public String getMessage() {
        return "Product not available for ProductId :"+ this.productId;
    }
}