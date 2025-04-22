package br.dev.ezcoder.ezfinancepro.model.dto.request;

import br.dev.ezcoder.ezfinancepro.model.enums.PaymentMethod;
import br.dev.ezcoder.ezfinancepro.model.enums.TransactionType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

import java.math.BigDecimal;
import java.time.LocalDate;

public record TransactionRequestDTO(
        @NotBlank String userId,
        @NotNull @PositiveOrZero BigDecimal amount,
        @NotNull LocalDate date,
        String description,
        String notes,
        @NotNull TransactionType type,
        CategoryRequestDTO category,
        boolean isRecurring,
        boolean isCreditCard,
        String cardInvoiceId,
        PaymentMethod paymentMethod
        ) {
}
