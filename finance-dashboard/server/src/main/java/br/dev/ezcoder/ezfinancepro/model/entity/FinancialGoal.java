package br.dev.ezcoder.ezfinancepro.model.entity;

import br.dev.ezcoder.ezfinancepro.model.dto.request.FinancialGoalRequest;
import br.dev.ezcoder.ezfinancepro.model.enums.FinancialGoalPriority;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@Document(collection = "financial_goals")
public class FinancialGoal {

  @Id
  private String id;

  @NotNull
  private String userId;

  @NotBlank
  private String title;

  private String description;

  @NotNull
  private BigDecimal targetAmount;

  private BigDecimal currentAmount;

  @NotNull
  private LocalDate targetDate;

  @NotNull
  private FinancialGoalPriority priority;

  public FinancialGoal(FinancialGoalRequest request) {
    this.userId = request.userId();
    this.title = request.title();
    this.description = request.description();
    this.targetAmount = request.targetAmount();
    this.currentAmount = request.currentAmount();
    this.targetDate = request.targetDate();
    this.priority = request.priority();
  }
}
