package com.cafepos.backend.dto;

import java.util.List;

public class CheckoutRequest {
    
    private Long userId;
    private List<CheckoutItem> items;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public List<CheckoutItem> getItems(){
        return items;
    }

    public void setItems(List<CheckoutItem> items){
        this.items = items;
    }

    public static class CheckoutItem{
        private Long productId;
        private int quantity;

        public Long getProductId() {
            return productId;
        }

        public void setProductId(Long productId) {
            this.productId = productId;
        }

        public int getQuantity() {
            return quantity;
        }

        public void setQuantity(int quantity) {
            this.quantity = quantity;
        }
    }
}
