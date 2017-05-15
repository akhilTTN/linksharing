package com.demo.linksharing


class ResourceInterceptor {

    ResourceInterceptor() {
//        match(action:'delete')

    }

    boolean before() {
       /* User loggedInUser = session.user
        Resource resource = Resource.get(params.id)
        if(resource) {
            if (loggedInUser.admin || resource.createdBy.username.equals(loggedInUser.username)) {
                log.info("${loggedInUser.username} is trying to delete ${resource.desctiption}")
                return true
            } else {
                render("User ${loggedInUser.username} is not authorised to delete ${resource.desctiption} resource")
                return false
            }
        }
        else{
            render("Resource not found")
            return false
        }*/
        true
    }

    boolean after() { true }

    void afterView() {
        // no-op
    }
}
