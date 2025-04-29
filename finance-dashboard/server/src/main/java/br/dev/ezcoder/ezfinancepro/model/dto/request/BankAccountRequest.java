package br.dev.ezcoder.ezfinancepro.model.dto.request;

import br.dev.ezcoder.ezfinancepro.model.enums.AccountType;
import jakarta.validation.constraints.NotBlank;

import java.math.BigDecimal;

public record BankAccountRequest(
        @NotBlank String userId,
        @NotBlank String bankName,
        AccountType accountType,
        String lastFourDigits,
        BigDecimal currentBalance,
        BigDecimal availableBalance
) {}
