package br.dev.ezcoder.ezfinancepro.service.impl;

import br.dev.ezcoder.ezfinancepro.exception.UserNotFoundException;
import br.dev.ezcoder.ezfinancepro.model.dto.request.UserCreateRequest;
import br.dev.ezcoder.ezfinancepro.model.dto.response.UserResponse;
import br.dev.ezcoder.ezfinancepro.model.entity.UserModel;
import br.dev.ezcoder.ezfinancepro.repository.UserRepository;
import br.dev.ezcoder.ezfinancepro.service.RoleService;
import br.dev.ezcoder.ezfinancepro.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserModel registerUser(UserModel newUser) {
        return userRepository.save(newUser);
    }

    @Override
    public List<UserResponse> findAllUsers() {
        return mapUsersToResponseDTOs(userRepository.findAll());
    }

    @Override
    public UserModel findUserById(String userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException(userId));
    }

    @Override
    public UserModel updateUser(String userId, UserCreateRequest request) {
        UserModel userFound = findUserById(userId);
        BeanUtils.copyProperties(request, userFound);
        userFound.setUpdatedAt(LocalDateTime.now());
        userFound.setPassword(passwordEncoder.encode(request.password()));

        return userRepository.save(userFound);
    }

    @Override
    public void deleteUserById(String userId) {
        UserModel userFound = findUserById(userId);
        userRepository.deleteById(userFound.getId());
    }

    public List<UserResponse> mapUsersToResponseDTOs (List<UserModel> users) {
        Objects.requireNonNull(users, "Users List cannot be Null");

        return users.stream()
                .filter(Objects::nonNull)
                .map(this::entityToResponseDTO)
                .toList();
    }

    private UserResponse entityToResponseDTO(UserModel user) {
        return UserResponse.entityToResponse(user);
    }

}
