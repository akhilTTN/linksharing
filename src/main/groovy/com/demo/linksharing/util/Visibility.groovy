package com.demo.linksharing.util

/**
 * Created by akhil on 27/4/17.
 */
enum Visibility {

    PUBLIC,
    PRIVATE


    static Visibility getEnum(String value) {
        return valueOf(value.toUpperCase())
    }
}