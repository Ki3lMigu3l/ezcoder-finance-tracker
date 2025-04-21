package br.dev.ezcoder.ezfinancepro.model.entity;

import br.dev.ezcoder.ezfinancepro.model.dto.request.CategoryRequestDTO;
import br.dev.ezcoder.ezfinancepro.model.enums.CategoryType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
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

    public Category(Category category) {
        this.userId = category.getUserId();
        this.name = category.getName();
        this.icon = category.getIcon();
        this.colorHex = category.getColorHex();
        this.type = category.getType();
        this.isDefault = category.isDefault();
    }

    public Category(CategoryRequestDTO request) {
        this.userId = request.userId();
        this.name = request.name();
        this.icon = request.icon();
        this.colorHex = request.colorHex();
        this.type = request.type();
        this.isDefault = request.isDefault();
    }
}
