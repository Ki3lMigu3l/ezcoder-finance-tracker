package br.dev.ezcoder.ezfinancepro.model.dto.response;

import br.dev.ezcoder.ezfinancepro.model.entity.FinancialGoal;
import br.dev.ezcoder.ezfinancepro.model.enums.FinancialGoalPriority;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;
import java.time.LocalDate;

public record FinancialGoalResponse(
        String id,
        @NotNull String userId,
        @NotBlank String title,
        String description,
        @Positive BigDecimal targetAmount,
        @Positive BigDecimal currentAmount,
        @NotNull LocalDate targetDate,
        @NotNull FinancialGoalPriority priority
) {
    public static FinancialGoalResponse entityToResponse(FinancialGoal goal) {
       return new FinancialGoalResponse(
               goal.getId(),
               goal.getUserId(),
               goal.getTitle(),
               goal.getDescription(),
               goal.getTargetAmount(),
               goal.getCurrentAmount(),
               goal.getTargetDate(),
               goal.getPriority()
       );
    }
}
