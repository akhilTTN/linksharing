package com.demo.linksharing

class ResourceController {

    def index() { }

    def delete(int id){
        User user = session.user
        if (user.resources.createdBy == user) {
            Resource resource = Resource.load(id)
            try {
                if (resource.delete(flush:true)) {
                    flash.message = "Resource deleted Successfully"
                } else {
                    flash.error = "Resource not deleted"
                }

            } catch (Exception e) {
                log.error "Error : ${e.message}"
                render "Resource can't be deleted"
            }
        } else {
            flash.error = "Resource deletion not allowed"
        }
        redirect(uri: '/')
}
