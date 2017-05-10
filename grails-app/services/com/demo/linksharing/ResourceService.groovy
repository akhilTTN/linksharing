package com.demo.linksharing

import grails.transaction.Transactional

@Transactional
class ResourceService {

    def serviceMethod() {

    }

    def deleteResourceService(long id) {
        Resource resource = Resource.load(id)
        if (resource) {
            try {
                if (resource.delete(flush: true)) {
                    return "Resource deleted Successfully"
                } else {
                    return "Resource not deleted"
                }
            } catch (Exception e) {
                log.error "Error : ${e.message}"
                return "Resource can't be deleted"
            }
        } else {
            return "Resource not found"
        }
    }
}
