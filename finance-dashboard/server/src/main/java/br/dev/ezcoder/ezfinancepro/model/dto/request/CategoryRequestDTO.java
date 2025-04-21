package br.dev.ezcoder.ezfinancepro.model.dto.request;

import br.dev.ezcoder.ezfinancepro.model.enums.CategoryType;
import jakarta.validation.constraints.NotBlank;

public record CategoryRequestDTO(
        @NotBlank String userId,
        @NotBlank String name,
        String icon,
        String colorHex,
        CategoryType type,
        boolean isDefault
) {
}
