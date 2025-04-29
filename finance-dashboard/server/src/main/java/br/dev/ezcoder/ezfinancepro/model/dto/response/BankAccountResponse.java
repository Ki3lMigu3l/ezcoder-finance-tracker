package br.dev.ezcoder.ezfinancepro.model.dto.response;

import br.dev.ezcoder.ezfinancepro.model.entity.BankAccount;
import br.dev.ezcoder.ezfinancepro.model.enums.AccountType;
import jakarta.validation.constraints.NotBlank;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record BankAccountResponse(
        @NotBlank String id,
        @NotBlank String userId,
        @NotBlank String bankName,
        AccountType accountType,
        String lastFourDigits,
        BigDecimal currentBalance,
        BigDecimal availableBalance,
        LocalDateTime lastSyncedAt
) {
    public static BankAccountResponse entityToResponse(BankAccount bankAccount) {
        return new BankAccountResponse(
                bankAccount.getId(),
                bankAccount.getUserId(),
                bankAccount.getBankName(),
                bankAccount.getAccountType(),
                bankAccount.getLastFourDigits(),
                bankAccount.getCurrentBalance(),
                bankAccount.getAvailableBalance(),
                bankAccount.getLastSyncedAt()
        );
    }
}
