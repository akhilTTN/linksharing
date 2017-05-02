package com.demo.linksharing

class LinkResource extends Resource {

    String url

    static constraints = {
        url nullable: false,blank: null
    }
}
