package br.dev.ezcoder.ezfinancepro.controller;

import br.dev.ezcoder.ezfinancepro.model.converter.UserDtoConverter;
import br.dev.ezcoder.ezfinancepro.model.dto.request.UserRequestDTO;
import br.dev.ezcoder.ezfinancepro.model.dto.response.UserResponseDTO;
import br.dev.ezcoder.ezfinancepro.model.dto.response.UserResponseUpdateDTO;
import br.dev.ezcoder.ezfinancepro.model.entity.User;
import br.dev.ezcoder.ezfinancepro.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final UserDtoConverter userDtoConverter;

    @PostMapping
    public ResponseEntity<UserResponseDTO> createUser (@RequestBody @Valid UserRequestDTO request) {
        User createdUser = userService.create(request);
        URI location = buildUserUri(createdUser.getId());
        return ResponseEntity.created(location).body(userDtoConverter.toReponse(createdUser));
    }

    @GetMapping
    public ResponseEntity<List<UserResponseDTO>> getAllUsers () {
        return ResponseEntity.ok(userService.findAllUsers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDTO> getUserById (@PathVariable String id) {
        User userFound = userService.findUser(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found!"));
        return ResponseEntity.ok(userDtoConverter.toReponse(userFound));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserResponseUpdateDTO> updateUser (@PathVariable String id, @RequestBody @Valid UserRequestDTO request) {
        return ResponseEntity.ok(userDtoConverter.toUpdateResponse(userService.updateUser(id, request)));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUser (@PathVariable String id) {
        userService.deleteUser(id);
    }

    private URI buildUserUri(String userId) {
        return ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(userId)
                .toUri();
    }
}
