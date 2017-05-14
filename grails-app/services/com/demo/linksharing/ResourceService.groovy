package com.demo.linksharing

import CO.LinkCO
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

    def createResource(LinkCO linkCO) {
        log.info("${linkCO}")
        Resource resource = new LinkResource(url: linkCO.url,description: linkCO.description,
                topic: Topic.get(linkCO.id),createdBy: linkCO.createdBy)
        log.info("${resource}")
        resource.save(failOnError:true,flush:true)
        if(resource.hasErrors()){
            flash.error = "sorry resource can't be saved"
        }else{
            log.info("resource successfully saved")
//            flash.message = "resource successfully saved"
        }
    }
}
