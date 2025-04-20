package br.dev.ezcoder.ezfinancepro.model.entity;

import br.dev.ezcoder.ezfinancepro.model.enums.CategoryType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
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

    public Category (String id, String userId, String name, CategoryType type) {
        this.id = id;
        this.userId = userId;
        this.name = name;
        this.type = type;
    }
}
