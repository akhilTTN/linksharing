package com.demo.linksharing


class LoginCheckInterceptor {

    LoginCheckInterceptor() {
        matchAll().excludes(controller: 'login')
    }

    boolean before() {
        if (session.user == null)
            redirect(controller: 'login', action: 'index')
        true
    }

    boolean after() { true }

    void afterView() {
        // no-op
    }
}
