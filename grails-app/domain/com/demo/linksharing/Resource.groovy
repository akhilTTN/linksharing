package com.demo.linksharing

import CO.ResourceSearchCO
import VO.RatingInfoVO

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


        RatingInfoVO getRatingInfo(){
        def result=ResourceRating.createCriteria().get() {
            projections{
                count('id')
                sum('score')
                avg('score')
            }
            eq('resource',this)
        }
        println("${result.toString()}")
        return new RatingInfoVO(totalVotes: result[0],totalScore: result[1], averageScore: result[2])
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
                    count('id')
                    order('id', 'asc')
                }
            }
            maxResults 5
        }
        List listOfIdOfResourcesFromResourceRating = []
        result.each {
            listOfIdOfResourcesFromResourceRating.add(Resource.get(it[0] as Serializable))
        }
        println(listOfIdOfResourcesFromResourceRating)
        listOfIdOfResourcesFromResourceRating
    }


}
