package linksharing

import com.demo.linksharing.DocumentResource
import com.demo.linksharing.LinkResource
import com.demo.linksharing.ReadingItem
import com.demo.linksharing.Resource
import com.demo.linksharing.ResourceRating
import com.demo.linksharing.Subscription
import com.demo.linksharing.Topic
import com.demo.linksharing.User
import com.demo.linksharing.util.Seriousness
import com.demo.linksharing.util.Visibility

class BootStrap {

    def init = { servletContext ->
        /*createUser()
        createTopics()
        createResource()
        subscribeToTopics()
        createReadingItems()
        giveResourceRating()*/

    }

    def createUser() {
        if (User.count == 0) {
            log.info "=================Creating Normal User======================="
            User normalUser = new User(firstName: "test", lastName: "test", username: "test123", photo: null, admin: false, active: true, email: "test@tothenew.com", password: "tests" , confirmPassword: "tests")
            normalUser.save(failOnError: true, flush: true)
            log.info "=================Creating Normal User======================="
            if (normalUser.hasErrors()) {
                normalUser.errors.allErrors.each {
                    log.error it
                }
            }
        } else {
            log.info("User already present in the table")
        }
        log.info "=================Creating ADMIN======================="
        User admin = new User(firstName: "Akhil", lastName: "Rawat", username: "akhil123", photo: null, admin: true, active: true, email: "akhil@tothenew.com", password: "akhil123", confirmPassword: "akhil123")
        admin.save(failOnError: true, flush: true)
        log.info "=================Creating ADMIN======================="
        if (admin.hasErrors()) {
            admin.errors.allErrors.each {
                log.error it
            }
        }
    }

    def createTopics() {
        (1..(User.count())).each { s ->
            if (Topic.countByCreatedBy(User.get(s)) == 0) {
                5.times {
                    Topic topic = new Topic(topicName: "topic${it}", createdBy: User.get(s), visibility: Visibility.PUBLIC)
//                topic.addToSubscription(new Subscription(user: User.get(s), topic: topic, seriousness: Seriousness.SERIOUS))
                    topic.save(failOnError: true)
                    if (topic.hasErrors()) {
                        log.error(topic.errors.allErrors())
                    }
                }
            }
        }
    }

    def createResource() {
        if (Resource.count() == 0) {
            String content = "This is a dummy description"
            def topics = Topic.list()
            topics.each { s ->
                log.info(" >>>>>>>>>>>>>>>>>>>> ${s.createdBy}>>>>>>>>>>")
                2.times {
                    Resource linkResource = new LinkResource(createdBy: s.createdBy, topic: s, desctiption: content + " " + s.getTopicName(), url: "http://www.google.com")
                    linkResource.save(failOnError: true, flush: true)
                    if (linkResource.hasErrors())
                        log.error(linkResource.errors.allErrors())
                    else
                        log.info(">>>> link Resource has been created successfully >>>>>")

                    Resource documentResource = new DocumentResource(createdBy: s.createdBy, topic: s, desctiption: content + " " + s.getTopicName(), filePath: "/home/akhil/abc.txt")
                    documentResource.save(failOnError: true, flush: true)
                    if (documentResource.hasErrors())
                        log.error(documentResource.errors.allErrors())
                    else
                        log.info(">>>> document Resource has been created successfully >>>>>")

                }
            }
        }
    }


    def subscribeToTopics() {
        List<User> user = User.all
        List<Topic> topics = Topic.all
        user.each { users ->
            topics.each { topic ->
                if (!Subscription.findByUserAndTopic(users, topic)) {
                    Subscription subscription = new Subscription(user: users, topic: topic, seriousness: Seriousness.CASUAL)
                    if (subscription.save(flush: true, failOnError: true))
                        log.info(">>>>> User ${user} has subscribed to ${topics} topic")
                    else
                        log.error("++++++ User ${user} was not able to subscribe to ${topics} topic due to some error +++++++++")
                }

            }
        }


    }

    /*List<ReadingItem> createReadingItems() {
        List<ReadingItem> readingItems = []
        List<User> users = User.list()
        users.each { user ->
            user.subscription.each { subscriptions ->
                subscriptions.topic.resources.each { resource ->
                    if (resource.createdBy != user && !user.reads?.contains(resource)) {
                        ReadingItem readingItem = new ReadingItem(user: user, isRead: false, resource: resource)
                        if (readingItem.save(flush: true, failOnError: true)) {
                            readingItems.add(readingItem)
                            user.addToReads(readingItem)
                            log.info "${readingItem} saved successfully for user ${user} and resource ${resource}"
                        } else {
                            log.error "Error saving reading item: ${readingItem} for user: ${user} and resource: ${resource}"
                        }
                    }
                }
            }
        }
        readingItems
    }*/

    def createReadingItems() {
        List<User> userList = User.list()
        List<Topic> topicList = Topic.all
        userList.each { user ->
            def subscriptionList = Subscription.findAllByUser(user)
//            log.info("Subscriptions: ${subscriptionList*.id}   User: ${user.id}")
//            def resourceList = Resource.findAllByTopicInList(subscriptionList*.topic)
            def resourceList = Resource.findAllByCreatedByNotEqualAndTopicInList(user, subscriptionList*.topic)
            log.info("$resourceList*.id")
            //
            List<ReadingItem> readingItem1 = ReadingItem.findByUser(user)
            log.info(readingItem1)
            def finalReadingItemList = resourceList*.id - readingItem1*.resource
            log.info(">>>>>>>>>>>>>++++ ${finalReadingItemList}>>>>>>>>>>>>>>++++")
            finalReadingItemList.each { resource ->
                ReadingItem readingItem = new ReadingItem(user: user, resource: resource, isRead: false)
                if (readingItem.save(failOnTrue: true, flush: false))
                    log.info("Reading item $readingItem saved successfully")
                else
                    log.error("Reading item ${readingItem.user} can not be saved")
            }

        }
    }


    def giveResourceRating() {
        List<User> list = User.all
        List<ReadingItem> readingItem = ReadingItem.all
        readingItem.each {
            if (!it.isRead) {
                ResourceRating rating = new ResourceRating(resource: it.resource, user: it.user, score: 3)
                if (rating.save(failOnError: true, flush: false))
                    log.info("Resource rating done on $it.resource")
                else
                    log.error("Resource rating was not done properly")
            }
        }

    }


}