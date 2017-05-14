package VO

import com.demo.linksharing.User
import com.demo.linksharing.util.Visibility

/**
 * Created by akhil on 10/5/17.
 */
class TopicVO {
    long id
    String name
    Visibility visibility
    int count
    User createdBy
    int subsCount


    @Override
    public String toString() {
        return "TopicVO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", visibility=" + visibility +
                ", count=" + count +
                ", createdBy=" + createdBy +
                ", subsCount=" + subsCount +
                '}';
    }
}
