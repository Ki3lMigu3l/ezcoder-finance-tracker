package br.dev.ezcoder.ezfinancepro.model.entity;

import br.dev.ezcoder.ezfinancepro.model.dto.request.BankAccountRequest;
import br.dev.ezcoder.ezfinancepro.model.enums.AccountType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@Document(collection = "bank_accounts")
public class BankAccount {

  @Id
  private String id;

  @NotBlank
  private String userId;

  @NotBlank
  private String bankName;
  private AccountType accountType;

  private String lastFourDigits;
  private BigDecimal currentBalance;
  private BigDecimal availableBalance;

  private LocalDateTime lastSyncedAt;

  public BankAccount(BankAccountRequest request) {
    this.userId = request.userId();
    this.bankName = request.bankName();
    this.accountType = request.accountType();
    this.lastFourDigits = request.lastFourDigits();
    this.currentBalance = request.currentBalance();
    this.availableBalance = request.availableBalance();
  }
}
