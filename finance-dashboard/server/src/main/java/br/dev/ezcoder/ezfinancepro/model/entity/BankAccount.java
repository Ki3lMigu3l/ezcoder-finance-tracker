package br.dev.ezcoder.ezfinancepro.model.entity;

import br.dev.ezcoder.ezfinancepro.model.enums.AccountType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Document(collection = "bank_accounts")
public class BankAccount {

    @Id
    private String id;

    @NotBlank
    private String userId;

    @NotBlank
    private String bankName;

    @NotBlank
    private AccountType accountType;

    private String lastFourDigits;
    private BigDecimal currentBalance;
    private BigDecimal availableBalance;

    @NotNull
    private LocalDateTime lastSyncedAt;
}
