package com.allen.coupon.domain.handling;

public class InsufficientFundsException extends BusinessHandlerException{
    public InsufficientFundsException(String s) {
        super(s);
    }
}
