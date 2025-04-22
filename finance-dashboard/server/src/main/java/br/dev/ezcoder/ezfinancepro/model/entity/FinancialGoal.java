package br.dev.ezcoder.ezfinancepro.model.entity;

import br.dev.ezcoder.ezfinancepro.model.enums.FinancialGoalPriority;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Document(collection = "financial_goals")
public class FinancialGoal {

    @Id
    private String id;

    @NotBlank
    private String userId;

    @NotBlank
    private String title;

    private String Description;

    @NotNull
    private BigDecimal targetAmount;

    private BigDecimal currentAmount;

    @NotNull
    private LocalDate targetDate;

    @NotNull
    private FinancialGoalPriority priority;
}
