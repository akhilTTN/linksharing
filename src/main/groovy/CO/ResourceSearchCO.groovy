package CO

import com.demo.linksharing.util.Visibility

/**
 * Created by akhil on 9/5/17.
 */
class ResourceSearchCO extends SearchCO {
    long topicID
    Visibility visibility

    static constraints = {
        topic_id(nullable:true)
        visibility(validator: { val, obj ->
            Visibility.getEnum(val) == Visibility.PUBLIC
        })
    }
}
