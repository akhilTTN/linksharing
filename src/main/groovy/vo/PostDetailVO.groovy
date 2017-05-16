package vo

import com.demo.linksharing.LinkResource
import com.demo.linksharing.Resource
import com.demo.linksharing.Topic

/**
 * Created by akhil on 14/5/17.
 */
class PostDetailVO {
    String username
    String fullName
    String topicName
    String description
    int ratings
    Date updated
    long resourceID
    Topic topic


    boolean isLinkResource(){
        Resource resource= Resource.get(resourceID)
        if(resource instanceof LinkResource)
            return true
        else
            return false
    }

    @Override
    public String toString() {
        return "PostDetailVO{" +
                "username='" + username + '\'' +
                ", fullName='" + fullName + '\'' +
                ", topicName='" + topicName + '\'' +
                ", description='" + description + '\'' +
                ", ratings=" + ratings +
                ", updated=" + updated +
                ", resourceID=" + resourceID +
                ", topic=" + topic +
                '}';
    }
}
