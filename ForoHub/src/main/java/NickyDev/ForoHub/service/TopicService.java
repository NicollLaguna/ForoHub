package NickyDev.ForoHub.service;

import NickyDev.ForoHub.model.topic.Topic;
import NickyDev.ForoHub.model.topic.TopicInfo;
import NickyDev.ForoHub.model.topic.TopicRequest;
import NickyDev.ForoHub.model.topic.validation.TopicValidator;
import NickyDev.ForoHub.model.ValidationException;
import NickyDev.ForoHub.model.topic.TopicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class TopicService {
    @Autowired
    private TopicRepository topicRepository;
    @Autowired
    private TopicValidator topicValidator;

    public ResponseEntity postTopic(TopicRequest topicRequest, UriComponentsBuilder uriComponentsBuilder) {
        topicValidator.validate(topicRequest);
        Topic newTopic = topicRepository.save(new Topic(topicRequest));
        URI url = uriComponentsBuilder.path("/topics/{id}").buildAndExpand(newTopic.getId()).toUri();
        return ResponseEntity.created(url).body(topicRequest);
    }

    public ResponseEntity findTopicById(Long id) {
        Optional<TopicInfo> foundTopic = topicRepository.findById(id).map(TopicInfo::new);

        if (foundTopic.isPresent()) {
            return ResponseEntity.ok(foundTopic.get());
        } else {
            throw new ValidationException("The selected ID does not exist or is invalid");
        }
    }

    public ResponseEntity<Page<TopicInfo>> findAll(Pageable pagination) {

        Page<TopicInfo> topics = topicRepository.findAll(pagination).map(TopicInfo::new);
        return ResponseEntity.ok(topics);
    }

    public ResponseEntity modifyTopicById(Long id, TopicRequest topicRequest) {
        Optional<Topic> foundTopic = topicRepository.findById(id);
        if (foundTopic.isPresent()) {
            var modifiedTopic = new Topic(id, topicRequest.title(), topicRequest.message()
                    , LocalDateTime.now(), true, topicRequest.author(), topicRequest.course());
            TopicInfo topicInfo = new TopicInfo(modifiedTopic);
            topicValidator.validateExcluding(topicRequest, id);
            topicRepository.save(modifiedTopic);
            return ResponseEntity.ok(topicInfo);
        } else {
            throw new ValidationException("The selected ID does not exist or is invalid");
        }
    }

    public ResponseEntity deleteTopicById(Long id) {
        Optional<Topic> foundTopic = topicRepository.findById(id);
        if (foundTopic.isPresent()) {
            topicRepository.deleteById(id);
        } else {
            throw new ValidationException("The ID does not exist, is invalid, or has already been deleted");
        }
        return ResponseEntity.noContent().build();
    }
}

