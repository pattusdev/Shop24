package com.Bk24Shop.Shop.enums;

public enum Errors {


    error1("Drink Not Found",101),
    error2("Cargo Not Found",102),
    error3("Receipt Not Found",103),
    error4("Client Not Found",104),
    error5("Location Not Found",105),
    error6("OrderItem Not Found",106),
    error7("Order Not Found",107),

    ;





    public String message;
    public Integer code;



    Errors(String message, Integer code) {
        this.message = message;
        this.code = code;
    }

}

