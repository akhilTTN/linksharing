package CO

import com.demo.linksharing.User

/**
 * Created by akhil on 14/5/17.
 */
class LinkCO {
    String url;
    String description;
    long id
    User createdBy


    @Override
    public String toString() {
        return "LinkCO{" +
                "url='" + url + '\'' +
                ", description='" + description + '\'' +
                ", id=" + id +
                ", createdBy=" + createdBy +
                '}';
    }
}



