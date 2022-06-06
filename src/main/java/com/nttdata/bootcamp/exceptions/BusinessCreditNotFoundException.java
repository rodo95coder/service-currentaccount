package com.nttdata.bootcamp.exceptions;

public class BusinessCreditNotFoundException extends InterruptedException {
    public BusinessCreditNotFoundException( String message ){ super(message); }
}