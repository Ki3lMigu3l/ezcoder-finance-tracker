package br.dev.ezcoder.ezfinancepro.service;

import br.dev.ezcoder.ezfinancepro.model.dto.request.UserCreateRequest;
import br.dev.ezcoder.ezfinancepro.model.dto.response.UserResponse;
import br.dev.ezcoder.ezfinancepro.model.entity.UserModel;

import java.util.List;

public interface UserService {

    UserModel registerUser(UserModel newUser);
    UserModel findUserById(String userId);
    List<UserResponse> findAllUsers();
    UserModel updateUser (String userId, UserCreateRequest request);
    void deleteUserById(String userId);
}
