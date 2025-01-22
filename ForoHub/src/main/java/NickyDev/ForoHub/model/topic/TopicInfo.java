package NickyDev.ForoHub.model.topic;

import java.time.LocalDateTime;

public record TopicInfo(String title,
                        String message,
                        LocalDateTime date,
                        boolean status,
                        String author,
                        String course) {
    // title, message, creation date, status, author, and course
    public TopicInfo(Topic topic) {
        this(topic.getTitle(), topic.getMessage(), topic.getDate(), topic.isStatus(), topic.getAuthor(), topic.getCourse());
    }
}

