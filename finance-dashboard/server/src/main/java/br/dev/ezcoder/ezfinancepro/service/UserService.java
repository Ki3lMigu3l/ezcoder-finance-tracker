package br.dev.ezcoder.ezfinancepro.service;

import br.dev.ezcoder.ezfinancepro.model.dto.request.UserRequestDTO;
import br.dev.ezcoder.ezfinancepro.model.dto.response.UserResponseDTO;
import br.dev.ezcoder.ezfinancepro.model.entity.User;
import br.dev.ezcoder.ezfinancepro.repositories.UserRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public User registerUser (@Valid UserRequestDTO userRequestDTO) {
        return userRepository.save(new User(userRequestDTO));
    }

    public User findUserById(String id) {
        User userFound = userRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found!"));

        return userFound;
    }

    public List<UserResponseDTO> findAllUsers() {
        return mapUsersToResponseDTOs(userRepository.findAll());
    }

    public User updateUser (String userId, @Valid UserRequestDTO userUpdateDTO) {
        User userFound = userRepository.findById(userId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found!"));

        BeanUtils.copyProperties(userUpdateDTO, userFound);
        userFound.setUpdatedAt(LocalDateTime.now());
        return userRepository.save(userFound);
    }

    public void deleteUserById(String id) {
        userRepository.deleteById(id);
    }

    public List<UserResponseDTO> mapUsersToResponseDTOs (List<User> users) {
        Objects.requireNonNull(users, "Users List cannot be Null");

        return users.stream()
                .filter(Objects::nonNull)
                .map(this::entityToResponseDTO)
                .toList();
    }

    private UserResponseDTO entityToResponseDTO(User user) {
        return UserResponseDTO.entityToResponse(user);
    }
}
