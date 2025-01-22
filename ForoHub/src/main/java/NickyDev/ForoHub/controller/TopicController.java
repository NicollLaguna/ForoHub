package NickyDev.ForoHub.controller;

import NickyDev.ForoHub.model.topic.Topic;
import NickyDev.ForoHub.model.topic.TopicInfo;
import NickyDev.ForoHub.model.topic.TopicRequest;
import NickyDev.ForoHub.service.TopicService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/topics")
@SecurityRequirement(name = "bearer-key")
public class TopicController {
    @Autowired
    private TopicService topicService;

    @PostMapping()
    public ResponseEntity addTopic(@RequestBody @Valid TopicRequest topicRequest, UriComponentsBuilder uriBuilder) {
        return topicService.postTopic(topicRequest, uriBuilder);
    }

    @GetMapping("/{id}")
    public ResponseEntity getTopic(@PathVariable Long id) {
        return topicService.findTopicById(id);
    }

    @GetMapping
    public ResponseEntity<Page<TopicInfo>> getAllTopics(@PageableDefault(sort = "date") Pageable pagination) {
        return topicService.findAll(pagination);
    }

    @PutMapping("/{id}")
    public ResponseEntity modifyTopic(@PathVariable @Valid Long id, @RequestBody @Valid TopicRequest topicRequest) {
        return topicService.modifyTopicById(id, topicRequest);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteTopic(@PathVariable @Valid Long id) {
        return topicService.deleteTopicById(id);
    }
}

