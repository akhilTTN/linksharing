package com.demo.linksharing


import grails.test.mixin.TestFor
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.web.ControllerUnitTestMixin} for usage instructions
 */
@TestFor(ResourceInterceptor)
class ResourceInterceptorSpec extends Specification {

    def setup() {
    }

    def cleanup() {

    }

    void "Test resource interceptor matching"() {
        when:"A request matches the interceptor"
            withRequest(controller:"resource")

        then:"The interceptor does match"
            interceptor.doesMatch()
    }
}
