package NickyDev.ForoHub.model.topic;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record TopicRequest(
        @NotBlank @NotNull @Valid String title,
        @NotBlank @NotNull @Valid String message,
        @NotBlank String author,
        @NotBlank String course) {
    public TopicRequest(Topic topic) {
        this(topic.getTitle(), topic.getMessage(), topic.getAuthor(), topic.getCourse());
    }
}
