package br.dev.ezcoder.ezfinancepro.controller;

import br.dev.ezcoder.ezfinancepro.exception.RoleNotFoundException;
import br.dev.ezcoder.ezfinancepro.model.dto.request.AuthRequest;
import br.dev.ezcoder.ezfinancepro.model.dto.request.UserSignupRequest;
import br.dev.ezcoder.ezfinancepro.model.dto.response.AuthResponse;
import br.dev.ezcoder.ezfinancepro.model.dto.response.UserResponse;
import br.dev.ezcoder.ezfinancepro.model.entity.Role;
import br.dev.ezcoder.ezfinancepro.model.entity.UserModel;
import br.dev.ezcoder.ezfinancepro.model.enums.RoleType;
import br.dev.ezcoder.ezfinancepro.service.RoleService;
import br.dev.ezcoder.ezfinancepro.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;
    private final RoleService roleService;
    private final PasswordEncoder passwordEncoder;

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login (@RequestBody AuthRequest request) {
        return null;
    }

    @PostMapping("/register")
    public ResponseEntity<UserResponse> signUp (@Valid @RequestBody UserSignupRequest request) {
        Set<Role> userRoles = request.roles().stream()
                .map(roleService::findByName)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toSet());

        if (userRoles.size() != request.roles().size()) {
            Set<RoleType> missingRoles = request.roles().stream()
                    .filter(roleType -> roleService.findByName(roleType).isEmpty())
                    .collect(Collectors.toSet());

            throw new RoleNotFoundException(missingRoles);
        }

        UserModel newUser = new UserModel();
        newUser.setEmail(request.email());
        newUser.setPassword(passwordEncoder.encode(request.password()));
        newUser.setFirstName(request.firstName());
        newUser.setLastName(request.lastName());
        newUser.setRoles(userRoles);
        newUser.setCreatedAt(LocalDateTime.now());

        return ResponseEntity.status(HttpStatus.CREATED).body(UserResponse.entityToResponse(userService.registerUser(newUser)));
    }
}
