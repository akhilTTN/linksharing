package com.demo.linksharing

import CO.FileCO
import CO.LinkCO
import CO.ResourceSearchCO
import VO.PostDetailVO
import VO.RatingInfoVO
import com.demo.linksharing.util.Visibility
import org.springframework.web.multipart.MultipartFile


class ResourceController {

    def resourceService

    def index() {

    }

    def delete(long id) {
        render resourceService.deleteResourceService(id)
    }

    def search(ResourceSearchCO resourceSearchCO) {
        if (resourceSearchCO.q) {
            resourceSearchCO.visibility = Visibility.PUBLIC
        }
    }


    def show(Long id) {
        RatingInfoVO ratingInfoVO = Resource.getRatingInfo(id)
        render """
                    totalVotes:${ratingInfoVO.totalVotes}
                    averageScore:${ratingInfoVO.averageScore}
                    totalScore:$ratingInfoVO.totalScore
                    """
    }

    def topPost() {
        List list = Resource.topPost()
//        render("${list}")
        render(template: "/topic/posts", model: [resourceList: list]);
//        render "${Resource.topPost()}"
    }

    def trendingTopic() {
//        Topic topic =Topic.get(1)
        Topic.getTrendingTopics().each {
            render "${it} <br/>"
        }
    }


    def save(LinkCO linkCo) {
        linkCo.createdBy = session.user
        resourceService.createResource(linkCo)
        redirect(controller: 'user', action: 'index')
    }


    def updateReadItem(Long id, Boolean isRead) {
        String str = ReadingItem.changeIsRead(id, isRead)
        render "${str}"
    }

    def viewPost(long id) {
        PostDetailVO PostdetailVO = Resource.getResourceDetails(id)
        render view: "viewPost", model: [detailedPost: PostdetailVO]
    }

    def upload(FileCO fileCO) {
        MultipartFile file = fileCO.myFile
        if (file.empty) {
            flash.message = 'file cannot be empty'
            render("error")
            return
        }

        def rootDir = request.getSession().getServletContext().getRealPath("/")
        //servletContext.getRealPath("/") //app directory
        File fileDest = new File(rootDir, "/uploads/${file.originalFilename}")
        file.transferTo(fileDest)
        fileCO.createdBy = session.user
        Resource resource = new DocumentResource(filePath: "${rootDir}/uploads/${file.originalFilename}",
                description: fileCO.description, topic: Topic.get(fileCO.topicId), createdBy: fileCO.createdBy)
        resource.save(flush: true, failOnError: true)
        redirect controller: "user", action: "index"
    }


    def download(long id){
        Resource resource = Resource.get(id)
        log.info("$resource.filePath")
        response.setHeader("Content-Disposition","attachment;filename=myfile")
        byte[] myBytes = new File ("${resource.filePath}").bytes
        response.setContentType("text/plain")
        response.outputStream << myBytes

    }


}

