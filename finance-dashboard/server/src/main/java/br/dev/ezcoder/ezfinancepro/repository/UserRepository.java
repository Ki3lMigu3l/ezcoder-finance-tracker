package br.dev.ezcoder.ezfinancepro.repository;

import br.dev.ezcoder.ezfinancepro.model.entity.UserModel;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends MongoRepository<UserModel, String> {

    Optional<UserModel> findByEmail(String email);
}
