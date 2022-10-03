package com.allen.coupon.domain.handling;

public class InvalidCurrencyException extends  BusinessHandlerException{
    public InvalidCurrencyException(String s) {
        super(s);
    }
}
