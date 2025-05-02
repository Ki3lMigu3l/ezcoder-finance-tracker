package br.dev.ezcoder.ezfinancepro.services;

import br.dev.ezcoder.ezfinancepro.exception.UserNotFoundException;
import br.dev.ezcoder.ezfinancepro.model.entity.UserModel;
import br.dev.ezcoder.ezfinancepro.repository.UserRepository;
import br.dev.ezcoder.ezfinancepro.service.impl.UserServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserServiceImpl userService;

    @Test
    @DisplayName("Deve encontrar usuário pelo ID")
    void shouldFindUserById() {
        String userId = "507f1f77bcf86cd799439011";
        UserModel user = new UserModel(userId, "test@example.com", "password");
        userRepository.save(user);

        when(userRepository.findById(userId)).thenReturn(Optional.of(user));
        UserModel result = userService.findUserById(user.getId());
        assertAll(
                () -> assertEquals(userId, result.getId()),
                () -> assertEquals("test@example.com", result.getEmail())
        );
    }

    @Test
    @DisplayName("Deve lançar uma exceção quando o usuário não for encontrado")
    void shouldThrowExceptionWhenUserNotFound() {
        String nonExistentId = "1";
        when(userRepository.findById(nonExistentId)).thenReturn(Optional.empty());

        assertThrows(
                UserNotFoundException.class,
                () -> userService.findUserById(nonExistentId)
        );
    }
}
