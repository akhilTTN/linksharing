package vo

import com.demo.linksharing.User
import com.demo.linksharing.util.Seriousness
import com.demo.linksharing.util.Visibility

/**
 * Created by akhil on 12/5/17.
 */
class SubscriptionVO {

    String topicName;
    User createdBy
    Integer subsCount;
    Integer resCount;
    Visibility visibility
    Seriousness seriousness


    @Override
    public String toString() {
        return "SubscriptionVO{" +
                "topicName='" + topicName + '\'' +
                ", CreatedBy=" + createdBy +
                ", subsCount=" + subsCount +
                ", resCount=" + resCount +
                ", visibility=" + visibility +
                ", seriousness=" + seriousness +
                '}';

    }
}
