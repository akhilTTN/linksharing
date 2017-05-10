package com.demo.linksharing

import VO.TopicVO
import com.demo.linksharing.util.Seriousness
import com.demo.linksharing.util.Visibility
import groovy.transform.ToString

@ToString
class Topic {

    static constraints = {
        topicName(nullable: false, unique: 'createdBy', blank: false)
        createdBy(nullable: false)
        visibility(nullable: false)
    }

    String topicName
    Date dateCreated
    Date lastUpdated
    Visibility visibility

    static belongsTo = [createdBy: User]
    static hasMany = [subscription: Subscription, resources: Resource]

    def afterInsert() {
        Topic.withNewSession {
            Subscription subscription = new Subscription(user: this.createdBy, topic: this, seriousness: Seriousness.VERY_SERIOUS)
            if (!subscription.save(flush: true, failOnError: true)) {
                log.error "Subscription: ${subscription} was not saved to the database"
            } else {
                log.info " Subscription ${subscription} saved successfully"
            }
        }
    }

    static List<TopicVO> getTrendingTopics() {
        List<TopicVO> trendingTopics = []
        Resource.createCriteria().list {
            createAlias('topic', 't')
            projections {
                groupProperty("t.id")
                property("t.topicName")
                property("t.visibility")
                property("t.createdBy")
                count("t.id", "topicCount")
            }
            order("topicCount", "desc")
            order("t.topicName", "desc")
            maxResults(5)
        }.each {
            trendingTopics.add(new TopicVO(id: it[0], name: it[1], visibility: it[2],
                    createdBy: it[3], count: it[4]))
        }
        return trendingTopics
    }

    static namedQueries = {
        names { String name ->
            eq "topicName", name
        }
    }

    static mapping = {
        sort topicName:'asc'
    }


    @Override
    public String toString() {
        return "Topic{" +
                "topicName='" + topicName + '\'' +
                '}';
    }
}