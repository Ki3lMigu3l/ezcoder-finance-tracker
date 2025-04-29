package br.dev.ezcoder.ezfinancepro.model.entity;

import br.dev.ezcoder.ezfinancepro.model.dto.request.UserCreateRequest;
import br.dev.ezcoder.ezfinancepro.model.dto.request.UserSignupRequest;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@Document(collection = "users")
public class UserModel {

  @Id
  private String id;

  @Email
  @NotBlank(message = "Email is mandatory")
  @Indexed(unique = true)
  private String email;

  @NotBlank(message = "Password is mandatory")
  private String password;

  @NotBlank(message = "Fisrt name is mandatory")
  private String firstName;

  @NotBlank(message = "Last name is mandatory")
  private String lastName;

  private String phone;

  private LocalDate birthDate;

  private String profileImageUrl;

  @CreatedDate
  private LocalDateTime createdAt;

  @LastModifiedDate
  private LocalDateTime updatedAt;

  @DBRef
  private Set<Role> roles;

  public UserModel(UserSignupRequest request) {
    this.email = request.email();
    this.password = request.password();
    this.firstName = request.firstName();
    this.lastName = request.lastName();
  }

  public UserModel(UserCreateRequest request) {
    this.setEmail(request.email());
    this.setPassword(request.password());
    this.setFirstName(request.firstName());
    this.setLastName(request.lastName());
    this.setPhone(request.phone());
    this.setBirthDate(request.birthDate());
    this.setProfileImageUrl(request.profileImageUrl());
    this.setCreatedAt(LocalDateTime.now());
  }
}
