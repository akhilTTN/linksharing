package com.demo.linksharing

import co.SearchCO
import vo.PostsVO
import vo.TopicVO
import vo.UserDetailsVO
import com.demo.linksharing.util.Visibility
import groovy.transform.ToString
import org.hibernate.sql.JoinType

@ToString
class Topic {

    static constraints = {
        topicName(nullable: false, unique: 'createdBy', blank: false)
        createdBy(nullable: false)
        visibility(nullable: false)
    }

    static transients = ['name']

    String topicName
    Date dateCreated
    Date lastUpdated
    Visibility visibility

    static belongsTo = [createdBy: User]
    static hasMany = [subscription: Subscription, resources: Resource]

    /*def afterInsert() {
        Topic.withNewSession {
            Subscription subscription = new Subscription(user: this.createdBy, topic: this, seriousness: Seriousness.VERY_SERIOUS)
            if (!subscription.save(flush: true, failOnError: true)) {
                log.error "Subscription: ${subscription} was not saved to the database"
            } else {
                log.info " Subscription ${subscription} saved successfully"
            }
        }
    }*/

    /*static List<TopicVO> getTrendingTopic() {

        List<TopicVO> listOfTrendingTopic = []

        List list = Resource.createCriteria().list() {
            projections {
                property('topic')
            }
            count('topic')
            groupProperty('topic')
            maxResults 5

        }
        list.each {
            Topic topic = it[0]
            listOfTrendingTopic.add(new TopicVO(id: topic.id, name: topic.topicName, visibility: topic.visibility,
                    createdBy: topic.createdBy, count: it[1], subsCount: topic.subscription.size()))
        }
        listOfTrendingTopic
    }*/


    static def getSubscribedUsers(Topic topic) {
        List<UserDetailsVO> allSubsUsers = []
        List allSubscribedUsers = Subscription.createCriteria().list {
            projections {
                property('user')
            }
            eq('topic', topic)
        }

        allSubscribedUsers.each {
            User user = it
            allSubsUsers.add(new UserDetailsVO(userName: user.username,
                    userFullName: user.name,
                    subscriptionCount: user.subscription.size(),
                    topicCount: user.topics.size(),
                    userId: user.id))

        }
        allSubsUsers
    }


    static def getSearched(SearchCO searchCO) {
        List<PostsVO> searchResult = []
        List result = Topic.createCriteria().list() {
            createAlias("resources", "r", JoinType.LEFT_OUTER_JOIN)
            projections {
                property('id')
                property('topicName')
                property('r.id')
                property('r.desctiption')
                property('r.createdBy')
            }
            if (searchCO && searchCO.q) {
                or {
                    ilike("topicName", "%${searchCO.q}%")
                    ilike("r.desctiption", "%${searchCO.q}%")
                }
            }
        }
        result.each {
            searchResult.add(new PostsVO(topicName: it[1], resourceID: it[2],
                    createdBy: it[4], desctiption: it[3],topicID: it[0]))
        }
        searchResult
    }


    static def getTrendingTopics() {
        List<TopicVO> topicVOS = []
        List list = Resource.createCriteria().list {
            projections {
                property('topic')
            }
            count('topic', 'resCount')
            groupProperty('topic')
            order('resCount')
            maxResults 5
        }
        list.each {
            Topic topic = it[0]
            topicVOS.add(new TopicVO(id: topic.id, topicName: topic.topicName, visibility: topic.visibility,
                    createdBy: topic.createdBy, count: it[1], subsCount: topic.subscription.size()))
        }
        topicVOS
    }






    /*static namedQueries = {
        names { String name ->
            eq "topicName", name
        }
    }*/

    static mapping = {
        sort topicName: 'asc'
    }


    @Override
    public String toString() {
        return "topicName='" + topicName ;
    }
}