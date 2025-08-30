package tech.hariprasath.quizApplication.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import tech.hariprasath.quizApplication.entity.UserSchema;

import java.util.Optional;

@Repository
public interface UserRepository extends MongoRepository<UserSchema, String> {

    Boolean existsByEmail(String email);

    Optional<UserSchema> findByEmail(String email);
}
