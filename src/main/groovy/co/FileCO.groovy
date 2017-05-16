package co

import com.demo.linksharing.User
import org.springframework.web.multipart.MultipartFile

/**
 * Created by akhil on 14/5/17.
 */
class FileCO {
    String description
    long topicId
    User createdBy
    MultipartFile myFile


    @Override
     String toString() {
        return "FileCO{" +
                "description='" + description + '\'' +
                ", topicId=" + topicId +
                ", createdBy=" + createdBy +
                ", myFile=" + myFile +
                '}'
    }
}
