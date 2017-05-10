package com.demo.linksharing


class ApplicationInterceptor {

    ApplicationInterceptor() {
//        matchAll()

    }

    boolean before() {
        true
        log.info("INCOMING REQUEST STARTED ${params.toString()}")
    }

    boolean after() {
        true
        log.info("INCOMING REQUEST STARTED ${params.toString()}")

    }

    void afterView() {
        // no-op
    }
}
