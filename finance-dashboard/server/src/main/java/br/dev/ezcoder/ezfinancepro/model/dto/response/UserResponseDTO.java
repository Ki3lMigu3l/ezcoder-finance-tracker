package br.dev.ezcoder.ezfinancepro.model.dto.response;

import br.dev.ezcoder.ezfinancepro.model.entity.User;
import jakarta.validation.constraints.NotBlank;
import java.time.LocalDate;
import java.time.LocalDateTime;

public record UserResponseDTO(
  @NotBlank String id,
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
  public static UserResponseDTO entityToResponse(User user) {
    return new UserResponseDTO(
      user.getId(),
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
