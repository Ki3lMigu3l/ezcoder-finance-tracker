package br.dev.ezcoder.ezfinancepro.model.dto.response;

import br.dev.ezcoder.ezfinancepro.model.entity.Category;
import br.dev.ezcoder.ezfinancepro.model.enums.CategoryType;
import jakarta.validation.constraints.NotBlank;

public record CategoryResponse(
        @NotBlank String id,
        @NotBlank String userId,
        @NotBlank String name,
        String icon,
        String colorHex,
        CategoryType type,
        boolean isDefault
) {
    public static CategoryResponse entityToResponse(Category category) {
        return new CategoryResponse(
                category.getId(),
                category.getUserId(),
                category.getName(),
                category.getIcon(),
                category.getColorHex(),
                category.getType(),
                category.isDefault()
        );
    }
}
