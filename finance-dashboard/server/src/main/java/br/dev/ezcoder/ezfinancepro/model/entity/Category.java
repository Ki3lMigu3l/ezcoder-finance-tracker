package br.dev.ezcoder.ezfinancepro.model.entity;

import br.dev.ezcoder.ezfinancepro.model.dto.request.CategoryRequest;
import br.dev.ezcoder.ezfinancepro.model.dto.request.TransactionRequest;
import br.dev.ezcoder.ezfinancepro.model.enums.CategoryType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@Document(collection = "categories")
public class Category {

  @Id
  private String id;

  @NotBlank
  private String userId;

  @NotBlank
  private String name;

  private String icon;

  private String colorHex;

  @NotNull
  private CategoryType type;

  private boolean isDefault;

  public Category(CategoryRequest request) {
    this.userId = request.userId();
    this.name = request.name();
    this.icon = request.icon();
    this.colorHex = request.colorHex();
    this.type = request.type();
    this.isDefault = request.isDefault();
  }

  public Category(TransactionRequest request) {
    this.userId = request.userId();
    this.name = request.category().name();
    this.icon = request.category().icon();
    this.colorHex = request.category().colorHex();
    this.type = request.category().type();
    this.isDefault = request.category().isDefault();
  }
}
