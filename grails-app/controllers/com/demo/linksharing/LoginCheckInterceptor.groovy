package com.demo.linksharing


class LoginCheckInterceptor {

    LoginCheckInterceptor() {
//        matchAll().excludes(controller: 'login')

        match(controller: 'login',action: 'index')
        match(controller: 'user',action: 'index')
        match(controller: 'login',action: 'logout')
        match(controller: 'resource',action: 'delete')
        match(controller: 'resource',action: 'index')
        match(controller: 'resource',action: 'show')
        match(controller: 'resource',action: 'search')
        match(controller: 'subscription',action: 'save')
        match(controller: 'subscription',action: 'update')
        match(controller: 'subscription',action: 'delete')
    }

    boolean before() {
        if (session.user == null)
            redirect(controller: 'login', action: 'loginHandler')
        else
            true
    }

    boolean after() { true }

    void afterView() {
        // no-op
    }
}
