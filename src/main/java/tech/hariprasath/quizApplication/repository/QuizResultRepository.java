package tech.hariprasath.quizApplication.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import tech.hariprasath.quizApplication.entity.QuizResultSchema;
import tech.hariprasath.quizApplication.entity.QuizSchema;

import java.util.List;

@Repository
public interface QuizResultRepository extends MongoRepository<QuizResultSchema, String> {

    List<QuizResultSchema> findAllByQuiz(QuizSchema quiz);
}
