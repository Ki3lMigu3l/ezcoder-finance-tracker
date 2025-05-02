package br.dev.ezcoder.ezfinancepro.repositories;

import br.dev.ezcoder.ezfinancepro.model.entity.UserModel;
import br.dev.ezcoder.ezfinancepro.repository.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.AutoConfigureDataMongo;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataMongoTest
@AutoConfigureDataMongo
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MongoTemplate mongoTemplate;

    @AfterEach
    void cleanUp() {
        userRepository.deleteAll();
    }

    @Test
    @DisplayName("Deve Salvar e recuperar usuário pelo Email")
    void findByEmailShouldReturnUser() {
        UserModel user = new UserModel("test@example.com", "encodedPass", "Test", "User");
        UserModel savedUser = userRepository.save(user);

        Optional<UserModel> foundUser = userRepository.findByEmail("test@example.com");

        assertThat(foundUser)
                .isPresent()
                .hasValueSatisfying(u -> {
                    assertThat(u.getId()).isNotNull();
                    assertThat(u.getEmail()).isEqualTo("test@example.com");
                    assertThat(u.getFirstName()).isEqualTo("Test");
                    assertThat(u.getLastName()).isEqualTo("User");
                });
    }

    @Test
    @DisplayName("Deve retornar vazio quando buscar por email inexistente")
    void shouldReturnEmptyWhenEmailNotFound() {
        Optional<UserModel> foundUser = userRepository.findByEmail("nonexistent@example.com");
        assertThat(foundUser).isEmpty();
    }
}
