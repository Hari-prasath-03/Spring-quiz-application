package tech.hariprasath.quizApplication.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import tech.hariprasath.quizApplication.entity.QuizSchema;
import tech.hariprasath.quizApplication.entity.UserSchema;

import java.util.List;

@Repository
public interface QuizRepository extends MongoRepository<QuizSchema, String> {

    List<QuizSchema> findAllByUser(UserSchema user);
}
