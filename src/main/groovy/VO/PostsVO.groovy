package VO

import com.demo.linksharing.LinkResource
import com.demo.linksharing.Resource
import com.demo.linksharing.User

/**
 * Created by akhil on 11/5/17.
 */
class PostsVO {
    String topicName
    String desctiption
    long topicID
    long resourceID
    User createdBy


    @Override
    public String toString() {
        return "PostsVO{" +
                "topicName='" + topicName + '\'' +
                ", description='" + desctiption + '\'' +
                ", topicID=" + topicID +
                ", resourceID=" + resourceID +
                ", createdBy=" + createdBy +
                '}';
    }

    boolean linkResource(){
        if(Resource.get(resourceID) instanceof LinkResource)
           return false
        else
           return true
    }
}
