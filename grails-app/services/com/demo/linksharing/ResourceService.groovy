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

    def createResource(LinkCO linkco) {
//        String s=""
        log.info("${linkco}")
//        s= "${linkco.toString()}"
        Resource resource = new LinkResource(url: linkco.url,desctiption: linkco.description,
                topic: Topic.get(linkco.id),createdBy: linkco.createdBy)
        log.info("${resource}")
//        s=s+ "${resource}"
        resource.save(failOnError:true,flush:true)
        if(resource.hasErrors()){
            flash.error = "sorry resource can't be saved"
        }else{
            log.info("resource successfully saved")
//            flash.message = "resource successfully saved"
        }

//        return s
    }
}
