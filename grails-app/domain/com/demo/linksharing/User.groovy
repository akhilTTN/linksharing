package com.demo.linksharing

import CO.SearchCO
import VO.InboxVO
import VO.PostsVO
import VO.SubscriptionVO
import VO.TopicVO
import org.hibernate.sql.JoinType

class User {

    static constraints = {
        firstName(blank: false, nullable: false, validator: { val, obj ->
            val.matches(".*\\d+.*") ? ["com.ttn.linksharing.User.firstName.validator"] : true
        })
        lastName(blank: false, nullable: false, validator: { val, obj ->
            val.matches(".*\\d+.*") ? ["com.ttn.linksharing.User.firstName.validator"] : true
        })
        username(blank: false, nullable: false, unique: true)
        email(blank: false, nullable: false, unique: true, email: true)
        password(blank: false, nullable: false, minSize: 5, validator: { val, obj ->
            if (val.equalsIgnoreCase(obj.firstName)) {
                return 'com.ttn.linksharing.User.password.validator'
            } else
                return true
        })
        photoPath(nullable: true)
        admin(nullable: true)
        active(nullable: true)
        confirmPassword(blank: true, nullable: true, validator: { val, obj ->
            if (!val.equals(obj.password)) {
                return 'com.ttn.linksharing.User.confirmPassword.validator'
            }
        })
    }


    String firstName
    String lastName
    String email
    String username
    String password
    String photoPath
    Boolean admin
    Boolean active
    String confirmPassword
    Date dateCreated
    Date lastUpdated

    static transients = ['name', 'confirmPassword']

    String getName() {
        return (firstName + " " + lastName)
    }

    static hasMany = [topics: Topic, subscription: Subscription, resources: Resource, reads: ReadingItem]


    static mapping = {
//        photo(sqlType: 'longBlob')
        sort id: 'desc'
        subscription lazy: false
    }


    @Override
    public String toString() {
        return "${username}"
    }

    /*
//todo GORM2 Q6) Add Inbox feature on user/index when user is loggedin
- Create method getUnReadResources in user domain which takes SearchCO argument and returns unreaditems of user from ReadingItem domain
- The search should also work using user/index page, q parameter of SearchCO. If searchco.q is found then getUnReadResources method
will search the items based on ilike of resource.description.
- The pagination parameter should also be used in getUnReadResources criteria query. Create readingItem/changeIsRead action which
takes Long id and Boolean isRead
- User executeUpdate to change the isRead of readingItem with given id
- If value returned by executeUpdate is 0 then render error else render success
*/

    def getUnReadResources(Map params,SearchCO searchCO = null) {
        List<PostsVO> PostsVOList = []
        def result = ReadingItem.createCriteria().list(params) {
            createAlias("resource", "r", JoinType.LEFT_OUTER_JOIN)
            projections {
//                property('r.id')
                property('r.topic')
                property('r.id')
                property('r.desctiption')
                property('r.createdBy')
            }
//            resultTransform (Transformers.aliasToBean(ResourceDTO))
            if (searchCO && searchCO.q) {
                ilike("r.desctiption", "%${searchCO.q}%")
            }
            eq('isRead', false)
            eq('user', this)
            maxResults 2
        }
        result.each {
            PostsVOList.add(new PostsVO(topicName: it[0].topicName, resourceID: it[1],
                    createdBy: it[3], desctiption: it[2], topicID: it[0].id))
        }
        println(PostsVOList)
        PostsVOList
    }

    /* static List getUnReadResources(Map params) {
         def list = ReadingItem.createCriteria().list(params) {
             projections {
                 property('resource')
             }
             eq('user', this)
             eq('isRead',false)
 //            order("dateCreated", "desc")
         }
         list
     }*/


    static def getSubscribedTopic(User user) {
        List<TopicVO> subscriptions = []
        def subscriptionList = Subscription.createCriteria().list() {
            projections {
                property('topic')
                property('seriousness')
            }
            eq('user', user)
//            maxResults 5
        }

        subscriptionList.each {
            Topic topic = it[0]
            subscriptions.add(new TopicVO(id: topic.id, topicName: topic.topicName, visibility: topic.visibility,
                    createdBy: topic.createdBy, count: topic.resources.size(), subsCount: topic.subscription.size()))
        }
        return subscriptions
    }


    static def allCreatedTopics(User user) {
        List<TopicVO> topicsCreated = []
        List<Topic> list = Topic.findAllByCreatedBy(user)
        list.each {
            Topic topic = it
            topicsCreated.add(new TopicVO(id: topic.id, topicName: topic.topicName, visibility: topic.visibility,
                    createdBy: topic.createdBy, count: topic.resources.size(), subsCount: topic.subscription.size()))

        }

        topicsCreated
    }

    static List<PostsVO> allCreatedPost(User user) {
        List<PostsVO> allPosts = []
        List<Resource> list = Resource.findAllByCreatedBy(user)
        list.each {
            Resource resource = it
            allPosts.add(new PostsVO(resourceID: resource.id, desctiption: resource.desctiption,
                    topicID: resource.topicId, topicName: resource.topic.topicName, createdBy: resource.createdBy))
        }
        allPosts
    }
}