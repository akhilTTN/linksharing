package com.demo.linksharing

class DocumentResource extends Resource {

    String filePath

    static constraints = {
        filePath nullable: false
    }
}
