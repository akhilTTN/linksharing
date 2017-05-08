package com.demo.linksharing.util

/**
 * Created by akhil on 29/4/17.
 */
enum Seriousness {
    CASUAL,
    SERIOUS,
    VERY_SERIOUS

    static Seriousness getEnum(String value){
        return valueOf(value.toUpperCase())
    }
}