package br.dev.ezcoder.ezfinancepro.model.dto.request;

import br.dev.ezcoder.ezfinancepro.model.enums.FinancialGoalPriority;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;
import java.time.LocalDate;

public record FinancialGoalRequest(
        @NotNull String userId,
        @NotBlank String title,
        String description,
        @Positive BigDecimal targetAmount,
        @Positive BigDecimal currentAmount,
        @NotNull LocalDate targetDate,
        @NotNull FinancialGoalPriority priority
){}
