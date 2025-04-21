package br.dev.ezcoder.ezfinancepro.model.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record UserUpdateDTO (
        @Size(min = 2, max = 50) String firstName,
        @Size(min = 2, max = 50) String lastName,
        @Email String email,
        @Size(min = 8) String password,
        @Pattern(regexp = "\\d{10,11}") String phone
) {
}
