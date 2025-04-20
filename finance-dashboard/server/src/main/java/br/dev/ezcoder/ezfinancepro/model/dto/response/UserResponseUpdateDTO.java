package br.dev.ezcoder.ezfinancepro.model.dto.response;

import br.dev.ezcoder.ezfinancepro.model.entity.User;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record UserResponseUpdateDTO(
        @NotBlank String email,
        @NotBlank String password,
        @NotBlank String firstName,
        @NotBlank String lastName,
        String phone,
        LocalDate birthDate,
        String profileImageUrl,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {

    public static UserResponseUpdateDTO entityToResponse(User user) {
        return new UserResponseUpdateDTO(
                user.getEmail(),
                user.getPassword(),
                user.getFirstName(),
                user.getLastName(),
                user.getPhone(),
                user.getBirthDate(),
                user.getProfileImageUrl(),
                user.getCreatedAt(),
                user.getUpdatedAt()
        );
    }
}
