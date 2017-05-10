package com.demo.linksharing

import CO.ResourceSearchCO
import com.demo.linksharing.util.Visibility


class ResourceController {

    def resourceService

    def index() {

    }

    def delete(int id) {
        render resourceService.deleteResourceService(id)
    }

    def search(ResourceSearchCO resourceSearchCO) {
        if (resourceSearchCO.q) {
            resourceSearchCO.visibility = Visibility.PUBLIC
        }
    }


    def show() {
        Resource resource = Resource.get(1)
        log.info("$resource")
        render "${resource.topic} >>>>>>>>> ${resource.getRatingInfo()}"
    }

    def trendingTopic() {
//        Topic topic =Topic.get(1)
        Topic.getTrendingTopics().each {
            render "${it} <br/>"
        }
    }
}

