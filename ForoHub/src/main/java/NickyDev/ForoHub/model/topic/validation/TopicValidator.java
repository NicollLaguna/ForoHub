package NickyDev.ForoHub.model.topic.validation;

import NickyDev.ForoHub.model.topic.TopicRequest;
import NickyDev.ForoHub.model.topic.TopicRepository;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TopicValidator {

    @Autowired
    private TopicRepository repository;

    public void validate(TopicRequest topicRequest) {
        validateTitleOrMessage(topicRequest.title(), topicRequest.message(), null);
    }

    public void validateExcluding(TopicRequest topicRequest, Long topicId) {
        validateTitleOrMessage(topicRequest.title(), topicRequest.message(), topicId);
    }

    private void validateTitleOrMessage(String title, String message, Long topicId) {
        if (topicId == null) {
            // Case for creating a new topic, checking for any duplicates
            boolean duplicates = repository.existsByTitleOrMessage(title, message);
            if (duplicates) {
                throw new ValidationException("Topics with the same title or message are not allowed");
            }
        } else {
            // Case for updating an existing topic, excluding the current topicId
            boolean titleDuplicate = repository.existsByTitleAndIdIsNot(title, topicId);
            boolean messageDuplicate = repository.existsByMessageAndIdIsNot(message, topicId);

            if (titleDuplicate || messageDuplicate) {
                throw new ValidationException("Topics with the same title or message are not allowed");
            }
        }
    }
}