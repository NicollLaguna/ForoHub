package NickyDev.ForoHub.model.topic;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TopicRepository extends JpaRepository<Topic, Long> {
    boolean existsByTitleOrMessage(String title, String message);

    boolean existsByTitleAndIdIsNot(String title, Long topicId);

    boolean existsByMessageAndIdIsNot(String message, Long topicId);
}

