package com.allen.coupon.domain.handling;

public class InsufficientStocksException extends  BusinessHandlerException{
    public InsufficientStocksException(String s) {
        super(s);
    }
}
