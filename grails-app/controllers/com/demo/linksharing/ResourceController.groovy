package com.demo.linksharing

class ResourceController {

    def index() { }

    def delete(int id){
        Resource resource = Resource.load(id)
        if(resource)
            resource.delete()
        else
            render "Resource not found"
    }
}
