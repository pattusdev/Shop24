package com.Bk24Shop.Shop.enums;


public enum Successes {


    success1("Deleted Successful",101),

    ;





    public String message;
    public Integer code;



    Successes(String message, Integer code) {
        this.message = message;
        this.code = code;
    }

}


