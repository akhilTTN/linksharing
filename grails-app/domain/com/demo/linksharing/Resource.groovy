package com.demo.linksharing

import co.ResourceSearchCO
import vo.PostDetailVO
import vo.PostsVO
import vo.RatingInfoVO

abstract class Resource {

    String desctiption
    Date dateCreated
    Date lastUpdated


    static constraints = {
        desctiption(nullable: false, blank: false)
        createdBy(nullable: false)
        topic(nullable: false)
    }

    static transients = ['ratingInfo']

    static belongsTo = [createdBy: User, topic: Topic]
    static hasMany = [ratings: ResourceRating, readingItem: ReadingItem]

    static namedQueries = {
        search {
            ResourceSearchCO resourceSearchCO ->
                'topic' {
                    if (resourceSearchCO.topicID) {
                        eq('topic', resourceSearchCO.topicID)
                    }
                    if (resourceSearchCO.visibility) {
                        eq('visibility', resourceSearchCO.visibility)
                    }
                }
        }
    }


    static def getRatingInfo(Long resourceId) {
        List<Long> ratingInfoVO = ResourceRating.createCriteria().get {
            projections {
                count('score')
                avg('score')
                sum('score')
            }
//            eq('user',this)
            eq('resource', Resource.get(resourceId))
        }
//        println(ratingInfoVO)
        if (ratingInfoVO[0] != 0) {
            new RatingInfoVO(totalVotes: ratingInfoVO[0], averageScore: ratingInfoVO[1], totalScore: ratingInfoVO[2])
        }
    }

/*
//todo GORM2 Q5) Add top post when user is not logged in
-Resource with maximum number of votes will be top post
-Only 5 posts should be shown in order of maximum vote count
-Use groupProperty with id of resource otherwise lots of queries will be fired
-Collect Resource list with resource id using getall rather then finder otherwise ordering will not be maintained
*/

    static def topPost() {
        def result = ResourceRating.createCriteria().list {
            projections {
                'resource' {
                    groupProperty('id')
                }
                count('id', 'count')
                order('count', 'desc')
            }
            maxResults 5
        }
        ArrayList fResult = []
        result.each {
            PostsVO p = new PostsVO()
            Resource resource = Resource.get(it[0])
            p.resourceID = it[0] as long//resource.id
            p.desctiption = resource.desctiption
            p.topicID = resource.topicId
            p.topicName = resource.topic.topicName
            p.createdBy = resource.createdBy
            fResult.add(p)
        }
//        println(fResult)
        fResult

    }


    static def getResourceDetails(long id) {
        RatingInfoVO ratingInfoVO = getRatingInfo(id)
        Resource resource = Resource.get(id)
        if (ratingInfoVO == null) {
            ratingInfoVO = new RatingInfoVO(averageScore: 0)
        }
        PostDetailVO detailedPostVO = new PostDetailVO(resourceID: id, description: resource.desctiption,
                ratings: ratingInfoVO.averageScore, updated: resource.lastUpdated,
                username: resource.createdBy.username, fullName: resource.createdBy.name,
                topicName: resource.topic.topicName,topic: resource.topic)
        detailedPostVO
    }


    static def getAllResources(Topic topic) {
        List<PostsVO> allResources = []
        List resources = Resource.createCriteria().list {
            projections {
                property('id')
                property('desctiption')
                property('topic')
                property('createdBy')
            }
            eq('topic', topic)
        }
        resources.each {
            Topic topic1 = it[2]
            allResources.add(new PostsVO(resourceID: it[0],
                    desctiption: it[1],
                    topicID: topic1.id,
                    topicName: topic1.topicName,
                    createdBy: it[3]))
        }
        allResources
    }


    static def recentPost() {
        List<PostsVO> recentPost = []
        def recentPostList = Resource.createCriteria().list {
//            projections {
//                property('id')
//            }
            order('lastUpdated', 'desc')
            maxResults 5
        }
        recentPostList.each {
            Resource resource = it
            recentPost.add(new PostsVO(resourceID: resource.id, desctiption: resource.desctiption,
                    topicID: resource.topicId, topicName: resource.topic.topicName, createdBy: resource.createdBy))
        }
        recentPost
    }


    @Override
    public String toString() {
        return "Resource{" +
                "id=" + id +
                ", version=" + version +
                ", ratings=" + ratings +
                ", readingItem=" + readingItem +
                ", createdBy=" + createdBy +
                ", topic=" + topic +
                ", desctiption='" + desctiption + '\'' +
                ", dateCreated=" + dateCreated +
                ", lastUpdated=" + lastUpdated +
                '}';
    }
}
