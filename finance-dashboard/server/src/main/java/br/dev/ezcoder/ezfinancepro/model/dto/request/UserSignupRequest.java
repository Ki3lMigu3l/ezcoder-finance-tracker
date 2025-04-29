package br.dev.ezcoder.ezfinancepro.model.dto.request;

import br.dev.ezcoder.ezfinancepro.model.enums.RoleType;
import jakarta.validation.constraints.*;

import java.util.Set;

public record UserSignupRequest (
        @Email @NotBlank String email,
        @NotBlank @Size(min = 8) String password,
        @NotBlank @Size(min = 2, max = 50) String firstName,
        @NotBlank @Size(min = 2, max = 50) String lastName,
        @NotEmpty Set<RoleType> roles
        ) {
}
