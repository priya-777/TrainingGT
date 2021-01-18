package com.priya.training.controller;

public class InvalidEmployeeNameException extends Exception{
    InvalidEmployeeNameException(String s) {
        super(s);
    }
}
